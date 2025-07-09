package com.lms.api_gateway.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.api_gateway.utils.JsonUtil;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ErrorDecorator extends ServerHttpResponseDecorator {
    HttpStatusCode httpStatusCode;
    private final ObjectMapper objectMapper =new ObjectMapper();
    public ErrorDecorator(ServerHttpResponse delegate, HttpStatusCode statusCode) {
        super(delegate);
        this.httpStatusCode = statusCode;
    }
    DataBufferFactory bufferFactory = getDelegate().bufferFactory();
    HttpStatus httpStatus = HttpStatus.valueOf(httpStatusCode.value());
    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
        return DataBufferUtils.join(fluxBody)
                .publishOn(Schedulers.boundedElastic())
                .flatMap(dataBuffer -> {
                    byte[] content = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(content);
                    DataBufferUtils.release(dataBuffer);

                    String responseBody = new String(content, StandardCharsets.UTF_8);
                    Map<String, Object> mapBody = JsonUtil.toMap(responseBody);
                    APIResponse errorResponse = new APIResponse();
                    errorResponse.setData(null);
                    errorResponse.setError(httpStatus.getReasonPhrase());
                    errorResponse.setMessage(mapBody.getOrDefault("message","Some error occurred").toString());
                    errorResponse.setSuccess(Boolean.FALSE);

                    try {
                        byte[] newContent = objectMapper.writeValueAsBytes(errorResponse);
                        DataBuffer buffer = bufferFactory.wrap(newContent);
                        return super.writeWith(Mono.just(buffer));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return super.writeWith(Flux.error(e));
                    }
                });

    }
}
