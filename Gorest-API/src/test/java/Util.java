import java.util.Random;

public class Util {

    static String getRandomEmail(int howLong) {

        Random random = new Random();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomEmail = new StringBuilder();
        for (int i = 0; i < howLong; i++) {
            int index = random.nextInt(letters.length());
            randomEmail.append(letters.charAt(index));
        }
        return randomEmail + "@test.com";
    }
}
