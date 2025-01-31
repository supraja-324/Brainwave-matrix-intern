package hospital;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

class Doctor {
    private String name;
    private String specialization;

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }
}

class Patient {
    private String name;
    private int age;
    private String disease;

    public Patient(String name, int age, String disease) {
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDisease() {
        return disease;
    }
}

class Appointment {
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;

    public Appointment(Patient patient, Doctor doctor, LocalDateTime dateTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");
        return "Appointment Details: " +
                "\nPatient: " + patient.getName() +
                "\nAge: " + patient.getAge() +
                "\nDisease: " + patient.getDisease() +
                "\nDoctor: Dr. " + doctor.getName() +
                "\nSpecialization: " + doctor.getSpecialization() +
                "\nDate & Time: " + dateTime.format(formatter);
    }
}

class HospitalManagementSystem {
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public void addPatient(String name, int age, String disease) {
        if (age <= 0) {
            System.out.println("Invalid age. Please enter a valid age.");
            return;
        }
        patients.add(new Patient(name, age, disease));
        System.out.println("Patient " + name + " added successfully.");
    }

    public void addDoctor(String name, String specialization) {
        if (specialization.isEmpty()) {
            System.out.println("Invalid specialization. Please provide a valid specialization.");
            return;
        }
        doctors.add(new Doctor(name, specialization));
        System.out.println("Doctor " + name + " added successfully.");
    }

    public void scheduleAppointment(Patient patient, Doctor doctor, LocalDateTime dateTime) {
        appointments.add(new Appointment(patient, doctor, dateTime));
        System.out.println("Appointment scheduled successfully!");
    }

    public void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
                System.out.println("-------------------------------------------------");
            }
        }
    }

    public void listDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
        } else {
            System.out.println("List of Doctors:");
            for (int i = 0; i < doctors.size(); i++) {
                System.out.println((i + 1) + ". Dr. " + doctors.get(i).getName() + " (" + doctors.get(i).getSpecialization() + ")");
            }
        }
    }

    public void listPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
        } else {
            System.out.println("List of Patients:");
            for (int i = 0; i < patients.size(); i++) {
                System.out.println((i + 1) + ". " + patients.get(i).getName() + " (" + patients.get(i).getDisease() + ")");
            }
        }
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

}
