import java.util.Random;

public class Util {

    public static String generateRandomNumber() {
        Random random = new Random();
        int num = random.nextInt(20) + 1;
        return String.valueOf(num);
    }
}
