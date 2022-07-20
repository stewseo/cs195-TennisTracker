package com.example.database.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class TestRedisBackedCacheSimpleContainerSetup {
    @Container
    public GenericContainer<?> redis = new GenericContainer<>("redis:5.0.3-alpine")
            .withExposedPorts(6379);

    private RedisBackedCache underTest;

    @BeforeEach
    public void setUp() {
        String address = redis.getHost();
        Integer port = redis.getFirstMappedPort();

        underTest = new RedisBackedCache(address, port);
    }

    @Test
    void testSimplePutAndGet() {
        underTest.put("test", "example");

        String retrieved = underTest.get("test");
        assertEquals("example", retrieved);
    }
}