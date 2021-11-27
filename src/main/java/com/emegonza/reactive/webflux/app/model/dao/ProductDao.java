package com.emegonza.reactive.webflux.app.model.dao;

import com.emegonza.reactive.webflux.app.model.product.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductDao extends ReactiveMongoRepository<ProductEntity, String> {
}
