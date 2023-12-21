package com.example.iprwc_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class IprwcBackendApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void idk() {
        assertEquals("5.1.10.RELEASE", SpringVersion.getVersion());
    }

}
