package com.emegonza.reactive.webflux.app.model.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import reactor.core.publisher.Flux;

import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
public class ProductsList extends ResponseData {

    private List<Product> products;
}
