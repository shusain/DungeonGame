/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package DungeonGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DungeonTest {
    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;
    
    private ByteArrayInputStream testIn;
    private static ByteArrayOutputStream testOut;
    
    @BeforeAll
    public static void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
    
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    private String getOutput() {
        return testOut.toString();
    }

    @AfterAll
    public static void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test public void theDungeonAsksYourNameAndGreetsYou() {
        final String testString = "Slim Shady\n";
        provideInput(testString);
        Dungeon classUnderTest = new Dungeon();
        assertEquals(String.format("Please enter your name:\nWelcome to the Dungeon Slim Shady !\n"), getOutput());
        assertNotNull(classUnderTest.player, "Player should have been made by dungeon");
        assertEquals(classUnderTest.player.getName(), "Slim Shady");
    }
}
