import java.time.Duration;

public class Medicine {

    public static void main(String[] args) {
        Duration d1 = Duration.ofDays(1);
        Duration d2 = Duration.ofDays(2);

        System.out.println(d1.compareTo(d2));
        System.out.println(d1.minus(d2));
    }

    private final Duration cureAmount;

    private final Duration restTime;

    public Medicine(Duration cureAmount, Duration restTime) {
        this.cureAmount = cureAmount;
        this.restTime = restTime;
    }

    public Duration getCureAmount() {
        return cureAmount;
    }

    public Duration getRestTime() {
        return restTime;
    }
}
