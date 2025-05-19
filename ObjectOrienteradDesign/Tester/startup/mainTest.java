package Tester.startup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import startup.Main;

public class MainTest {

    @Test
    public void testMainMethodRunsWithoutException() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}