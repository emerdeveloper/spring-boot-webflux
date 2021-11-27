package com.emegonza.reactive.webflux.app.transaction;

import com.emegonza.reactive.webflux.app.model.api.Header;
import com.emegonza.reactive.webflux.app.model.api.Product;
import com.emegonza.reactive.webflux.app.model.api.Response;
import com.emegonza.reactive.webflux.app.model.product.ProductEntity;
import com.emegonza.reactive.webflux.app.utils.Utilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ProductTransactionApi extends ProductBaseTransaction {

    private static final Logger LOGGER = Logger.getLogger(ProductTransactionApi.class.getName());

    @Value("${api.product.transaction.type}")
    private String transactionType;

    public Flux<Product> getProductList() {
        return findAllProducts()
                .map(productEntity -> entityToResponse(productEntity, false));
    }

    public Mono<Response<Product>> getProductById(String id) {
        return findProductById(id)
                .map(productEntity -> entityToResponse(productEntity, true))
                .map(Utilities::buildResponse);
    }

    private Product entityToResponse(ProductEntity productEntity, boolean isWithHeader) {
        return Product.builder()
                .header(isWithHeader ? buildHeader() : null)
                .id(productEntity.getId())
                .name(productEntity.getName())
                .createAt(productEntity.getCreateAt())
                .price(productEntity.getPrice())
                .build();
    }

    private Header buildHeader() {
        return Header.builder().id(UUID.randomUUID().toString())
                .type(transactionType).build();
    }

}
