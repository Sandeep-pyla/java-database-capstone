package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.repo.AppointmentRepository;
import com.project.back_end.repo.DoctorRepository;
import com.project.back_end.repo.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentService {
// 1. **Add @Service Annotation**:
//    - To indicate that this class is a service layer class for handling business logic.
//    - The `@Service` annotation should be added before the class declaration to mark it as a Spring service component.
//    - Instruction: Add `@Service` above the class definition.
        private AppointmentRepository appointmentRepository;
        private com.project.back_end.services.Service service;
        private TokenService tokenService;
        private PatientRepository patientRepository;
        private DoctorRepository doctorRepository;
// 2. **Constructor Injection for Dependencies**:
//    - The `AppointmentService` class requires several dependencies like `AppointmentRepository`, `Service`, `TokenService`, `PatientRepository`, and `DoctorRepository`.
//    - These dependencies should be injected through the constructor.
//    - Instruction: Ensure constructor injection is used for proper dependency management in Spring.
        AppointmentService(AppointmentRepository appointmentRepository, com.project.back_end.services.Service service, TokenService tokenService, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.service = service;
        this.tokenService = tokenService;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }
// 3. **Add @Transactional Annotation for Methods that Modify Database**:
//    - The methods that modify or update the database should be annotated with `@Transactional` to ensure atomicity and consistency of the operations.
//    - Instruction: Add the `@Transactional` annotation above methods that interact with the database, especially those modifying data.

// 4. **Book Appointment Method**:
//    - Responsible for saving the new appointment to the database.
//    - If the save operation fails, it returns `0`; otherwise, it returns `1`.
//    - Instruction: Ensure that the method handles any exceptions and returns an appropriate result code.
        @Transactional
        public int bookAppointment(Appointment appointment) {
            try {
                appointmentRepository.save(appointment);
                return 1;
            } catch (Exception e) {
                return 0;
            }
        }
// 5. **Update Appointment Method**:
//    - This method is used to update an existing appointment based on its ID.
//    - It validates whether the patient ID matches, checks if the appointment is available for updating, and ensures that the doctor is available at the specified time.
//    - If the update is successful, it saves the appointment; otherwise, it returns an appropriate error message.
//    - Instruction: Ensure proper validation and error handling is included for appointment updates.
        @Transactional
        public String updateAppointment(Appointment appointment) {
            Appointment existingAppointment = appointmentRepository.findById(appointment.getId()).orElse(null);
            if (existingAppointment == null) {
                return "Appointment not found";
            }
            if (!existingAppointment.getPatient().getId().equals(appointment.getPatient().getId())) {
                return "Patient ID does not match";
            }
            if (service.validateAppointment(appointment) == -1) {
                return "Doctor is not available at the specified time";
            }
            appointmentRepository.save(appointment);
            return "Appointment updated successfully";
        }
// 6. **Cancel Appointment Method**:
//    - This method cancels an appointment by deleting it from the database.
//    - It ensures the patient who owns the appointment is trying to cancel it and handles possible errors.
//    - Instruction: Make sure that the method checks for the patient ID match before deleting the appointment.
    @Transactional
    public void cancelAppointment(Long appointmentId, Long patientId) {
            Appointment existingAppointment = appointmentRepository.findById(appointmentId).orElse(null);
            if (existingAppointment == null) {
                throw new RuntimeException("Appointment not found");
            }
            if (!existingAppointment.getPatient().getId().equals(patientId)) {
                throw new RuntimeException("Patient ID does not match");
            }
            appointmentRepository.delete(existingAppointment);
        }
// 7. **Get Appointments Method**:
//    - This method retrieves a list of appointments for a specific doctor on a particular day, optionally filtered by the patient's name.
//    - It uses `@Transactional` to ensure that database operations are consistent and handled in a single transaction.
//    - Instruction: Ensure the correct us
//    e of transaction boundaries, especially when querying the database for appointments.
        @Transactional
        public Map<String, Object> getAppointments(String pname, LocalDate date, String token){
            Long doctorId = tokenService.validateToken(token, "doctor") ? doctorRepository.findByEmail(tokenService.extractEmail(token)).getId() : null;
            if (doctorId == null) {
                throw new RuntimeException("Invalid token");
            }
            List<Appointment> appointments;
            if (pname == null || pname.isEmpty()) {
                appointments = appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(doctorId, date.atStartOfDay(), date.plusDays(1).atStartOfDay());
            } else {
                appointments = appointmentRepository.findByDoctorIdAndPatient_NameContainingIgnoreCaseAndAppointmentTimeBetween(doctorId, pname, date.atStartOfDay(), date.plusDays(1).atStartOfDay());
            }
            return Map.of("appointments", appointments);
        }
// 8. **Change Status Method**:
//    - This method updates the status of an appointment by changing its value in the database.
//    - It should be annotated with `@Transactional` to ensure the operation is executed in a single transaction.
//    - Instruction: Add `@Transactional` before this method to ensure atomicity when updating appointment status.
        @Transactional
        public void changeStatus(Long appointmentId, int status) {
            Appointment existingAppointment = appointmentRepository.findById(appointmentId).orElse(null);
            if (existingAppointment == null) {
                throw new RuntimeException("Appointment not found");
            }
            existingAppointment.setStatus(status);
            appointmentRepository.save(existingAppointment);
        }
}
