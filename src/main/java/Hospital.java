import java.util.List;
import java.util.Optional;

public class Hospital {

    private final List<Patient> patients;

    public Hospital(List<Patient> patient) {
        this.patients = patient;
    }

    public boolean allCured() {
        return patients.stream().map(Patient::isCured).reduce(true, (a, b) -> a && b);
    }

    public synchronized Optional<Patient> getPatientForTreatment() {
        Optional<Patient> patient = patients
                .stream()
                .filter(p -> !p.isLocked() && !p.isResting() && !p.isCured())
                .findFirst();

        patient.ifPresent(Patient::lock);
        return patient;
    }
}
