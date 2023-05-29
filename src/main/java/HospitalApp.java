import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HospitalApp {

    public static void main(String[] args) {
        String arg1 = args[0];
        System.out.println("Number of doctors: " + arg1);
        String arg2 = args[1];
        System.out.println("Number of patients: " + arg2);

        Duration treatmentTime = Duration.ofMillis(20000);
        int numberOfDoctors = Integer.parseInt(arg1);
        int numberOfPatients = Integer.parseInt(arg2);

        List<Patient> patients = IntStream
                .rangeClosed(1, numberOfPatients)
                .mapToObj(n -> new Patient(String.valueOf(n), treatmentTime))
                .collect(Collectors.toList());

        Hospital hospital = new Hospital(patients);

        List<Doctor> doctors = IntStream
                .rangeClosed(1, numberOfDoctors)
                .mapToObj(n -> new Doctor(String.valueOf(n), hospital))
                .collect(Collectors.toList());

        doctors.forEach(Thread::start);
    }
}
