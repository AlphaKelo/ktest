package BobsCircus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CryptoManagerTestStudent {

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testIsStringInBounds() {
        assertTrue(CryptoManager.isStringInBounds("HAPPY_RAMADAN"));
        assertTrue(CryptoManager.isStringInBounds("SOUR_PATCH_2021"));
        assertFalse(CryptoManager.isStringInBounds("pizza"));
        assertFalse(CryptoManager.isStringInBounds("NINJAGO{")); 
    }

    @Test
    void testVigenereEncryption() {
        String encrypted = CryptoManager.vigenereEncryption("NINJA", "KEY");
        assertEquals(5, encrypted.length()); 
    }

    @Test
    void testVigenereDecryption() {
        String encrypted = CryptoManager.vigenereEncryption("NINJA", "KEY");
        String decrypted = CryptoManager.vigenereDecryption(encrypted, "KEY");
        assertEquals("NINJA", decrypted);
    }

    @Test
    void testPlayfairEncryption() {
        String encrypted = CryptoManager.playfairEncryption("JAZZ", "KEY");
        assertEquals(4, encrypted.length());
    }

    @Test
    void testPlayfairDecryption() { 
        String encrypted = CryptoManager.playfairEncryption("JAZZ", "KEY");
        String decrypted = CryptoManager.playfairDecryption(encrypted, "KEY");
        assertEquals("JAZZ", decrypted); 
    }

    @Test
    void testCaesarEncryption() { 
        String encrypted = CryptoManager.caesarEncryption("BILLY", 5);
        assertEquals(5, encrypted.length()); 
    }

    @Test
    void testCaesarDecryption() { 
        String encrypted = CryptoManager.caesarEncryption("BILLY", 5);
        String decrypted = CryptoManager.caesarDecryption(encrypted, 5);
        assertEquals("BILLY", decrypted); 
    }
}
