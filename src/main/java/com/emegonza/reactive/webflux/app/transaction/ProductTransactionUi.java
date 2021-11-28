package com.emegonza.reactive.webflux.app.transaction;

import com.emegonza.reactive.webflux.app.model.product.ProductEntity;
import com.emegonza.reactive.webflux.app.utils.Utilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.logging.Logger;

@Service
public class ProductTransactionUi extends ProductBaseTransaction {

    private static final Logger LOGGER = Logger.getLogger(ProductTransactionUi.class.getName());

    @Value("${file.template.list.product}")
    private String templateListFile;
    @Value("${file.template.list.chunked.product}")
    private String templateListChunkedFile;
    @Value("${file.template.save.product}")
    private String templateSaveFile;

    @Value("${ui.product.list.path}")
    private String listProductPath;


    public String productList(Model model) {
        model.addAttribute("products", findAllProducts());
        model.addAttribute("tittle", "Listar");
        return templateListFile;
    }

    public String productListDataDriver(Model model) {
        Flux<ProductEntity> products = findAllProducts()
                .map(Utilities::ProductEntityWithUpperCaseName)
                .delayElements(Duration.ofSeconds(1));
        products.subscribe(product -> LOGGER.info(product.getName()));
        model.addAttribute("products", new ReactiveDataDriverContextVariable(products, 1));
        model.addAttribute("tittle", "Listar");
        return templateListFile;
    }

    public String productListFull(Model model) {
        Flux<ProductEntity> products = findAllProducts()
                .map(Utilities::ProductEntityWithUpperCaseName)
                .repeat(5000);

        model.addAttribute("products", products);
        model.addAttribute("tittle", "Listar");
        return templateListFile;
    }

    public String productListChunked(Model model) {
        Flux<ProductEntity> products = findAllProducts()
                .map(Utilities::ProductEntityWithUpperCaseName)
                .repeat(5000);

        model.addAttribute("products", products);
        model.addAttribute("tittle", "Listar");
        return templateListChunkedFile;
    }

    public Mono<String> productFormSave(Model model) {
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("tittle", "Guardar producto");
        model.addAttribute("button", "Crear");
        return Mono.just(templateSaveFile);
    }

    public Mono<String> productOperationSave(ProductEntity product) {
        return saveProduct(product)
                .doOnNext(productEntity ->
                        LOGGER.info("Producto Guardado "
                                .concat(productEntity.getName()
                                        .concat(" - ")
                                        .concat(productEntity.getId()))))
                .thenReturn("redirect:".concat(listProductPath));
    }

    public Mono<String> productFormEdit(String id, Model model) {
        return findProductById(id)
                .doOnNext(productEntity -> {
                    LOGGER.info("Producto Guardado "
                            .concat(productEntity.getName()
                                    .concat(" - ")
                                    .concat(productEntity.getId())));
                    model.addAttribute("product", productEntity);
                    model.addAttribute("tittle", "Editar producto");
                    model.addAttribute("button", "Editar");
                })
                .switchIfEmpty(Mono.error(new InterruptedException("No existe el prodiucto")))
                .thenReturn(templateSaveFile)
                .onErrorResume(ex -> Mono.just("redirect:/list?error=No+existe+el+producto"));
    }
}
