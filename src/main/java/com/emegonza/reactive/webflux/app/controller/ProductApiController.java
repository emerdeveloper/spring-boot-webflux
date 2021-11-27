package com.emegonza.reactive.webflux.app.controller;

import com.emegonza.reactive.webflux.app.model.api.Product;
import com.emegonza.reactive.webflux.app.model.api.ProductsList;
import com.emegonza.reactive.webflux.app.model.api.Response;
import com.emegonza.reactive.webflux.app.transaction.ProductTransactionApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${api.product.base.path}")
public class ProductApiController implements ProductApi {

    @Autowired
    private ProductTransactionApi productTransaction;

    @Override
    public Flux<Product> getProductList() {
        return productTransaction.getProductList();
    }

    @Override
    public Mono<Response<Product>> getProductById(String id) {
        return productTransaction.getProductById(id);
    }
}
