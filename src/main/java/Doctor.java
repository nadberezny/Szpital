import java.time.Duration;
import java.util.Optional;

public class Doctor extends Thread {

    private final Hospital hospital;

    private final String id;

    public Doctor(String id, Hospital hospital) {
        this.id = id;
        this.hospital = hospital;
    }

    protected void cureAndRelease(Patient patient, Medicine medicine) {
        Duration newTreatmentTime;
        if (patient.getTreatmentTime().compareTo(medicine.getCureAmount()) < 0) {
            newTreatmentTime = Duration.ZERO;
        } else {
            newTreatmentTime = patient.getTreatmentTime().minus(medicine.getCureAmount());
        }

        patient.cure(newTreatmentTime, medicine.getRestTime());
        patient.unlock();
    }

    @Override
    public void run() {
        while (!hospital.allCured()) {
            Optional<Patient> maybePatient = hospital.getPatientForTreatment();
            if (maybePatient.isEmpty()) {
                System.out.println("Doctor " + id +  " No patient to be cured.");
                goForASmoke();
                continue;
            }
            Patient patient = maybePatient.get();
            cureAndRelease(patient, new Medicine(Duration.ofMillis(5000), Duration.ofMillis(10000)));
            goToNextRoom();
        }
        System.out.println("Doctor: " + id + " all cured. Finishing work");
    }

    private void goToNextRoom() {
        System.out.println("Doctor " + id + " proceeding to next room");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void goForASmoke() {
        System.out.println("Doctor " + id + " having a cigarette");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
