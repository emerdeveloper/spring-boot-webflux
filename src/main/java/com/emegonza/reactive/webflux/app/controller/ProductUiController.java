package com.emegonza.reactive.webflux.app.controller;

import com.emegonza.reactive.webflux.app.model.product.ProductEntity;
import com.emegonza.reactive.webflux.app.transaction.ProductTransactionUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import reactor.core.publisher.Mono;

//@SessionAttributes("product")
@Controller
public class ProductUiController implements ProductUi {

    @Autowired
    private ProductTransactionUi productTransaction;

    @Override
    public String productList(Model model) {
        return productTransaction.productList(model);
    }

    @Override
    public String productListDataDriver(Model model) {
        return productTransaction.productListDataDriver(model);
    }

    @Override
    public String productListChunked(Model model) {
        return productTransaction.productListChunked(model);
    }

    @Override
    public String productListFull(Model model) {
        return productTransaction.productListFull(model);
    }

    @Override
    public Mono<String> productFormSave(Model model) { //(Model model, SessionStatus status)
        //status.setComplete(); -> Finish session or destroy the data in session
        return productTransaction.productFormSave(model);
    }

    @Override
    public Mono<String> productOperationSave(ProductEntity product) {
        return productTransaction.productOperationSave(product);
    }

    @Override
    public Mono<String> productFormEdit(String id, Model model) {
        return productTransaction.productFormEdit(id, model);
    }
}
