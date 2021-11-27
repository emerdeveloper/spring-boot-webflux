package com.emegonza.reactive.webflux.app.transaction;

import com.emegonza.reactive.webflux.app.model.dao.ProductDao;
import com.emegonza.reactive.webflux.app.model.product.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class ProductBaseTransaction {

    @Autowired
    public ProductDao productDao;

    public Flux<ProductEntity> findAllProducts() {
        return productDao.findAll();
    }

    public Mono<ProductEntity> findProductById(String id) {
        return productDao.findById(id);
    }

    public Mono<ProductEntity> saveProduct(ProductEntity productEntity) {
        return productDao.save(productEntity);
    }

    public Mono<Void> deleteProduct(ProductEntity productEntity) {
        return productDao.delete(productEntity);
    }
}
