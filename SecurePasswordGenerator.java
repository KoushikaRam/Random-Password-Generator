import java.security.SecureRandom;

public class SecurePasswordGenerator {

    private static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC_CHARACTERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "@!#%&+_*[]{}()$^";
    
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String createPassword(int length, boolean includeUpper, boolean includeLower, boolean includeDigits, boolean includeSpecial) {
        StringBuilder charPool = new StringBuilder();

        if (includeUpper) charPool.append(UPPER_CASE_LETTERS);
        if (includeLower) charPool.append(LOWER_CASE_LETTERS);
        if (includeDigits) charPool.append(NUMERIC_CHARACTERS);
        if (includeSpecial) charPool.append(SPECIAL_CHARACTERS);

        if (charPool.length() == 0) throw new IllegalArgumentException("No character sets selected");

        StringBuilder passwordBuilder = new StringBuilder();

        if (includeUpper) passwordBuilder.append(UPPER_CASE_LETTERS.charAt(SECURE_RANDOM.nextInt(UPPER_CASE_LETTERS.length())));
        if (includeLower) passwordBuilder.append(LOWER_CASE_LETTERS.charAt(SECURE_RANDOM.nextInt(LOWER_CASE_LETTERS.length())));
        if (includeDigits) passwordBuilder.append(NUMERIC_CHARACTERS.charAt(SECURE_RANDOM.nextInt(NUMERIC_CHARACTERS.length())));
        if (includeSpecial) passwordBuilder.append(SPECIAL_CHARACTERS.charAt(SECURE_RANDOM.nextInt(SPECIAL_CHARACTERS.length())));

        for (int i = passwordBuilder.length(); i < length; i++) {
            passwordBuilder.append(charPool.charAt(SECURE_RANDOM.nextInt(charPool.length())));
        }

        return scrambleString(passwordBuilder.toString());
    }

    private static String scrambleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = SECURE_RANDOM.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }

    public static void main(String[] args) {
        String password = createPassword(12, true, true, true, true);
        System.out.println("Generated Password for you : " + password);
    }
}
