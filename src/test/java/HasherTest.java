import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import util.Hasher;

public class HasherTest {

    @Test
    void testGenerateHash() {
        String testString = "hello";
        String expectedHash = "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824"; // This is the SHA-256 hash for "hello"

        assertEquals(expectedHash, Hasher.generateHash(testString));
    }

    @Test
    void testGenerateHashEmptyString() {
        String testString = "";
        String expectedHash = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"; // This is the SHA-256 hash for an empty string

        assertEquals(expectedHash, Hasher.generateHash(testString));
    }

    @Test
    void testGenerateHashSameStrings() {
        String testString1 = "sample";
        String testString2 = "sample";

        assertEquals(Hasher.generateHash(testString1), Hasher.generateHash(testString2));
    }

    @Test
    void testGenerateHashDifferentStrings() {
        String testString1 = "sample1";
        String testString2 = "sample2";

        assertNotEquals(Hasher.generateHash(testString1), Hasher.generateHash(testString2));
    }

}
