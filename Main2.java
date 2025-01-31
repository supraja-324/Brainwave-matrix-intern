package hospital;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HospitalManagementSystem hospital = new HospitalManagementSystem();

        while (true) {
            System.out.println("------ Hospital Management System ------");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Display Appointments");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choiceInput = scanner.nextLine();
            if (!choiceInput.matches("\\d+")) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }
            int choice = Integer.parseInt(choiceInput);

            switch (choice) {
                case 1:
                    System.out.print("Enter patient name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter patient age: ");
                    String ageInput = scanner.nextLine();

                    if (!ageInput.matches("\\d+")) {
                        System.out.println("Invalid age! Please enter a valid number.");
                        break;
                    }

                    int patientAge = Integer.parseInt(ageInput);

                    System.out.print("Enter disease: ");
                    String disease = scanner.nextLine();
                    hospital.addPatient(patientName, patientAge, disease);
                    break;

                case 2:
                    System.out.print("Enter doctor name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("Enter doctor's specialization: ");
                    String specialization = scanner.nextLine();
                    hospital.addDoctor(doctorName, specialization);
                    break;

                case 3:
                    if (hospital.getPatients().isEmpty() || hospital.getDoctors().isEmpty()) {
                        System.out.println("Cannot schedule an appointment. No patients or doctors available.");
                        break;
                    }

                    hospital.listPatients();
                    System.out.print("Enter patient number: ");
                    String patientInput = scanner.nextLine();

                    if (!patientInput.matches("\\d+")) {
                        System.out.println("Invalid input! Please enter a valid patient number.");
                        break;
                    }

                    long patientIndex;
                    try {
                        patientIndex = Long.parseLong(patientInput) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid patient number! Please enter a smaller number.");
                        break;
                    }

                    if (patientIndex < 0 || patientIndex >= hospital.getPatients().size()) {
                        System.out.println("Invalid patient selection.");
                        break;
                    }

                    hospital.listDoctors();
                    System.out.print("Enter doctor number: ");
                    String doctorInput = scanner.nextLine();

                    if (!doctorInput.matches("\\d+")) {
                        System.out.println("Invalid input! Please enter a valid doctor number.");
                        break;
                    }

                    long doctorIndex;
                    try {
                        doctorIndex = Long.parseLong(doctorInput) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid doctor number! Please enter a smaller number.");
                        break;
                    }

                    if (doctorIndex < 0 || doctorIndex >= hospital.getDoctors().size()) {
                        System.out.println("Invalid doctor selection.");
                        break;
                    }

                    System.out.print("Enter appointment date & time (yyyy-MM-dd HH:mm): ");
                    String dateTimeInput = scanner.nextLine();
                    LocalDateTime dateTime;

                    try {
                        dateTime = LocalDateTime.parse(dateTimeInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    } catch (Exception e) {
                        System.out.println("Invalid date format! Please use 'yyyy-MM-dd HH:mm'.");
                        break;
                    }

                    hospital.scheduleAppointment(hospital.getPatients().get((int) patientIndex), hospital.getDoctors().get((int) doctorIndex), dateTime);
                    break;

                case 4:
                    hospital.displayAppointments();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
