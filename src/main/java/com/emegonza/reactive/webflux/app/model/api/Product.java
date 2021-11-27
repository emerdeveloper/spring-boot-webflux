package com.emegonza.reactive.webflux.app.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Product extends ResponseData {

    private String id;

    private String name;
    private double price;
    private Date createAt;
}
