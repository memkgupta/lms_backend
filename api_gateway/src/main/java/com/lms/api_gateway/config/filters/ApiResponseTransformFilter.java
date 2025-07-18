package com.lms.api_gateway.config.filters;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.lms.api_gateway.config.APIResponse;
import com.lms.api_gateway.config.DecoratorImpl;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import org.springframework.http.server.reactive.ServerHttpResponse;

import org.springframework.stereotype.Component;

import java.util.Map;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR;

@Component
public class ApiResponseTransformFilter extends AbstractGatewayFilterFactory<ApiResponseTransformFilter.Config> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ApiResponseTransformFilter() {
        super(Config.class);
    }

    private APIResponse transform(Map<String, Object> object, Config config) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(object);
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpResponse originalResponse = exchange.getResponse();




                       return chain.filter(exchange.mutate().response(new DecoratorImpl(originalResponse)).build());




        }, NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1);
    }

    public static class Config {
        // Future config options
    }
}
