package com.emegonza.reactive.webflux.app.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StopWatch;

import java.io.IOException;

public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomClientHttpRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        ClientHttpResponse response = execution.execute(request, body);
        stopwatch.stop();
        LOGGER.info(String.format("Method = %s", request.getMethod()));
        LOGGER.info(String.format("Uri = %s", request.getURI()));
        LOGGER.info(String.format("Response time = %s", stopwatch.getLastTaskTimeMillis()));
        LOGGER.info(String.format("Response code = %s", response.getStatusCode()));
        return response;
    }
}
