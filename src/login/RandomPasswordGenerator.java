package login;

import java.util.Random;

public class RandomPasswordGenerator {
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "@#$%^&+=";

    public static String generateRandomPassword() {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        String[] characterGroups = {LOWER_CASE, UPPER_CASE, DIGITS, SPECIAL_CHARACTERS};
        StringBuilder passwordBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            String characterGroup = characterGroups[random.nextInt(characterGroups.length)];
            char character = characterGroup.charAt(random.nextInt(characterGroup.length()));
            passwordBuilder.append(character);
        }

        String password = passwordBuilder.toString();
        if (!password.matches(regex)) {
            return generateRandomPassword();
        }

        return password;
    }

    public static void main(String[] args) {
        String password = generateRandomPassword();
        System.out.println("Mật khẩu ngẫu nhiên: " + password);
    }
}
