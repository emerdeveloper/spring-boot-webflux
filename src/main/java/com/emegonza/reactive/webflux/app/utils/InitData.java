package com.emegonza.reactive.webflux.app.utils;

import com.emegonza.reactive.webflux.app.configuration.Config;
import com.emegonza.reactive.webflux.app.model.dao.ProductDao;
import com.emegonza.reactive.webflux.app.model.product.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.logging.Logger;

@Component
public class InitData {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Value("${db.collection.product}")
    private String productCollectionName;

    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());

    public void configInitData() {
        mongoTemplate.dropCollection(productCollectionName)
                .doOnSuccess((unused) -> LOGGER.info("Finished Drop collection"))
        .subscribe();

        Flux.just(ProductEntity.builder().name("TV Panasonic Pantalla LCD").price(65646.97).build(),
                ProductEntity.builder().name("Sony Camara HD Digital").price(45646.97).build(),
                ProductEntity.builder().name("Apple iPod").price(25646.97).build(),
                ProductEntity.builder().name("Sony Notebook").price(35646.97).build(),
                ProductEntity.builder().name("Apple MackBook Pro 13-inch").price(75646.97).build(),
                ProductEntity.builder().name("Apple MackBook Pro 15-inch").price(95646.97).build(),
                ProductEntity.builder().name("TV Sony OLED 4K Ultra HD").price(67646.97).build(),
                ProductEntity.builder().name("Apple iPhone 13 Pro Max").price(46646.97).build(),
                ProductEntity.builder().name("Cámara Canon EOS R6 con lente RF24-105mm F4-7.1 IS STM").price(55646.97).build())
        .flatMap(product -> {
            product.setCreateAt(new Date());
            return productDao.save(product);
        })
        .subscribe(product -> LOGGER.info("Insert Data::: ".concat(product.getName())));
    }
}
