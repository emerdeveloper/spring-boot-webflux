package com.emegonza.reactive.webflux.app.utils;

import com.emegonza.reactive.webflux.app.model.api.Response;
import com.emegonza.reactive.webflux.app.model.product.ProductEntity;

import java.util.Collections;

public class Utilities {

    public static <T> Response<T> buildResponse(T responseData) {
        return Response.<T>builder()
                .data(Collections.singletonList(responseData))
                .build();
    }

    public static ProductEntity ProductEntityWithUpperCaseName(ProductEntity productEntity) {
        productEntity.setName(productEntity.getName().toLowerCase());
        return productEntity;
    }
}
