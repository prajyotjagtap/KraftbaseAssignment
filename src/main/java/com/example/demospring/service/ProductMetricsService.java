package com.example.demospring.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class ProductMetricsService {

    private final MeterRegistry meterRegistry;
    private final Counter productRequestCounter;

    public ProductMetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        productRequestCounter = Counter.builder("product_requests_total")
                .tag("product", "product1")
                .register(meterRegistry);
    }

    public void incrementProductRequestCounter(String productId) {
        productRequestCounter.increment();
    }
}

