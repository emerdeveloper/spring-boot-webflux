package com.emegonza.reactive.webflux.app.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Header {

    @NotNull
    @NotEmpty
    @JsonProperty("id")
    //@Pattern(regexp = RegexConstants.HEADER_ID_REGEX, message = RegexConstants.FAIL_MESSAGE)
    //@Length(max = RegexConstants.HEADER_ID_MAX_SIZE)
    @Schema(name = "id", example = "ade12-qwe124-3456ecv", required = true)
    private String id;

    @NotNull
    @NotEmpty
    @JsonProperty("type")
    //@Pattern(regexp = RegexConstants.HEADER_TYPE_REGEX, message = RegexConstants.FAIL_MESSAGE)
    //@Length(max = RegexConstants.HEADER_TYPE_MAX_SIZE)
    @Schema(name = "type", example = "transactions", description = "transactions", required = true)
    private String type;
}
