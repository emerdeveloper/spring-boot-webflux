package com.emegonza.reactive.webflux.app.controller;

import com.emegonza.reactive.webflux.app.model.product.ProductEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

public interface ProductUi {

    @Operation(
            description =  "Show a list of products in html format",
            summary = "show a html with a list of products",
            tags = {"get-html-products",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful")
    })
    @GetMapping(value = { "${ui.product.list.path}", "/"})
    String productList(Model model);

    @Operation(
            description =  "Show a list of products in html format",
            summary = "show list of product with async way implements ReactiveDataDriverContextVariable",
            tags = {"get-html-products",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful")
    })
    @GetMapping(value = "${ui.product.list.data.driver.path}")
    String productListDataDriver(Model model);

    @Operation(
            description =  "Show a list of products in html format",
            summary = "Show list of products repeated by 5000 with async way implements limit max-chunk-size=1024",
            tags = {"get-html-products",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful")
    })
    @GetMapping(value = "${ui.product.list.chunked.path}")
    String productListChunked(Model model);

    @Operation(
            description =  "Show a list of products in html format",
            summary = "Show list of products repeated by 5000",
            tags = {"get-html-products",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful")
    })
    @GetMapping(value = "${ui.product.list.full.path}")
    String productListFull(Model model);

    @Operation(
            description =  "Show a form to create a new product",
            summary = "This is a form to create a new product",
            tags = {"get-html-products",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful")
    })
    @GetMapping(value = "${ui.product.form.save.path}")
    Mono<String> productFormSave(Model model);

    @Operation(
            description =  "Operation to save product",
            summary = "This is a operation to save product, here is redirected when you submit save form",
            tags = {"get-operation-product",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful")
    })
    @PostMapping(value = "${ui.product.operation.save.path}")
    Mono<String> productOperationSave(ProductEntity product);

    @Operation(
            description =  "Show a form to edit a exists product",
            summary = "This is a form to edit a exists product",
            tags = {"get-html-products",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful")
    })
    @GetMapping(value = "${ui.product.operation.edit.path}")
    Mono<String> productFormEdit(@PathVariable String id, Model model);
}
