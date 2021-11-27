package com.emegonza.reactive.webflux.app.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class ResponseData {

    @JsonProperty("header")
    @Schema(name = "header")
    private Header header;
}
