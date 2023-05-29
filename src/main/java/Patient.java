import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Patient {

    private final String id;

    private volatile Duration treatmentTime;

    private volatile Instant restTime;

    private volatile boolean locked;

    public Patient(String id, Duration treatmentTime) {
        this.id = id;
        this.treatmentTime = treatmentTime;
        this.restTime = Instant.MIN;
        this.locked = false;
    }

    public Duration getTreatmentTime() {
        return treatmentTime;
    }

    public boolean isLocked() {
        return locked;
    }

    public synchronized void lock() {
        this.locked = true;
    }

    public synchronized void unlock() {
        this.locked = false;
    }

    public synchronized void cure(Duration newTreatmentTime, Duration restTime) {
        System.out.println("Apply medicine. New treatment time: " + newTreatmentTime + " new rest time: " + restTime);
        this.treatmentTime = newTreatmentTime;
        this.restTime = Instant.now().plus(restTime.toMillis(), ChronoUnit.MILLIS);
    }

    public boolean isResting() {
        return Instant.now().compareTo(restTime) < 0;
    }

    public boolean isCured() {
        return treatmentTime.isZero() || treatmentTime.isNegative();
    }
}
