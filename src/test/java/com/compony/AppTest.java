package com.compony;

import cl.company.App;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

@SpringBootTest(classes = App.class)
class AppTest {

    @Test
    void contextLoads() {}

    @Test
    void testMainMethod() {
        // Simula la ejecución del método main
        String[] args = {};
        SpringApplication mockSpringApplication = mock(SpringApplication.class);
        App.main(args);

        // Si el método `SpringApplication.run` no lanza excepciones, la prueba será exitosa
    }
}