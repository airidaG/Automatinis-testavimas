import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Util extends BaseTest {

    private static final DecimalFormat df = new DecimalFormat("0.00");


    static void generateUserData() {
        testData.setFirstName(faker.name().firstName());
        testData.setLastName(faker.name().lastName());
        testData.setEmail(faker.internet().emailAddress());
        testData.setPassword(faker.internet().password());
    }

    static double calculateDiscount(double fullPrice, int discount) {
        df.setRoundingMode(RoundingMode.UP);
        double howMuchWasDiscounted = fullPrice * ((double) discount / 100);
        String resultPrice = df.format(fullPrice - howMuchWasDiscounted);
        return Double.parseDouble(resultPrice.replace(",", "."));

    }
}
