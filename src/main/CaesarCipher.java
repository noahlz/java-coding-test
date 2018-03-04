/**
 * A Caesar cipher is a simple encryption method whereby each letter of the
 * alphabet is shifted by a fixed amount.  It is named for Julius Caesar, who
 * used it with a left shift of three to protect messages to his military.  In
 * that case, each occurrence of the letter 'A' would be substitued with the
 * letter 'D', and 'B' with 'E', and 'C' with 'F', and so on.  The end of the
 * alphabet wraps around to the beginning, so that 'Z' becomes 'C', etc.
 */
public class CaesarCipher {

    /**
     * Creates a new cipher.
     * @param shiftAmount the amount to shift the cipher alphabet.
     * @throws IllegalArgumentException for invalid shift values.
     */
    public CaesarCipher(int shiftAmount) {
    }

    /**
     * Encodes a string by shifting letters by the specified amount.  Letter
     * case is preserved.  All non-letter characters pass through unchanged.
     */
    public String encode(String sourceText) {
        return null;
    }

}
