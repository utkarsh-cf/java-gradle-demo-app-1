package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }

    @Test
    void greetingIsHelloWorld() {
        App classUnderTest = new App();
        assertEquals("Hello world!", classUnderTest.getGreeting(), "greeting should be 'Hello world!'");
    }
}
