package com.emegonza.reactive.webflux.app.controller;

import com.emegonza.reactive.webflux.app.model.api.Product;
import com.emegonza.reactive.webflux.app.model.api.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductApi {

    @Operation(
            description =  "Show a list of products",
            summary = "show a Json with a list of products",
            tags = {"api products",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Flux<Product> getProductList();

    @Operation(
            description =  "Show a product with id",
            summary = "get a product searching with product id",
            tags = { "api products",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
    })
    @GetMapping(value = "${api.product.by.id.path}", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<Response<Product>> getProductById(@PathVariable String id);

}
