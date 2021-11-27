package com.emegonza.reactive.webflux.app.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@Document(value = "products")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    private String id;

    private String name;
    private double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
}
