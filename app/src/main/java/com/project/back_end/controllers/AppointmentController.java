package com.project.back_end.controllers;

import com.project.back_end.models.Appointment;
import com.project.back_end.repo.PatientRepository;
import com.project.back_end.services.AppointmentService;
import com.project.back_end.services.Service;
import com.project.back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

// 1. Set Up the Controller Class:
//    - Annotate the class with `@RestController` to define it as a REST API controller.
//    - Use `@RequestMapping("/appointments")` to set a base path for all appointment-related endpoints.
//    - This centralizes all routes that deal with booking, updating, retrieving, and canceling appointments.


// 2. Autowire Dependencies:
//    - Inject `AppointmentService` for handling the business logic specific to appointments.
//    - Inject the general `Service` class, which provides shared functionality like token validation and appointment checks.
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private Service service;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private TokenService tokenService;
// 3. Define the `getAppointments` Method:
//    - Handles HTTP GET requests to fetch appointments based on date and patient name.
//    - Takes the appointment date, patient name, and token as path variables.
//    - First validates the token for role `"doctor"` using the `Service`.
//    - If the token is valid, returns appointments for the given patient on the specified date.
//    - If the token is invalid or expired, responds with the appropriate message and status code.
    @GetMapping("/{date}/{patientName}/{token}")
    public ResponseEntity<Map<String, Object>> getAppointments(@PathVariable String date, @PathVariable String patientName, @PathVariable String token) {
        Map<String, Object> response = new HashMap<>();
        if (!service.validateToken(token, "doctor").hasBody()) {
            response.put("message", "Invalid or expired token");
            return ResponseEntity.status(401).body(response);
        }

        // Assuming appointmentService.getAppointmentsByDateAndPatient returns a list of appointments
        var appointments = appointmentService.getAppointments (patientName, LocalDate.parse(date), token);
        response.put("appointments", appointments);
        return ResponseEntity.ok(response);
    }

// 4. Define the `bookAppointment` Method:
//    - Handles HTTP POST requests to create a new appointment.
//    - Accepts a validated `Appointment` object in the request body and a token as a path variable.
//    - Validates the token for the `"patient"` role.
//    - Uses service logic to validate the appointment data (e.g., check for doctor availability and time conflicts).
//    - Returns success if booked, or appropriate error messages if the doctor ID is invalid or the slot is already taken.

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, Object>> bookAppointment(@RequestBody Appointment appointment, @PathVariable String token) {
        service.validateToken(token, "patient");
        Map<String, Object> response = new HashMap<>();
        int result = appointmentService.bookAppointment(appointment);
        if (result == 1) {
            response.put("message", "Appointment booked successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", result);
            return ResponseEntity.status(400).body(response);
        }
    }

// 5. Define the `updateAppointment` Method:
//    - Handles HTTP PUT requests to modify an existing appointment.
//    - Accepts a validated `Appointment` object and a token as input.
//    - Validates the token for `"patient"` role.
//    - Delegates the update logic to the `AppointmentService`.
//    - Returns an appropriate success or failure response based on the update result.
    @PutMapping("/{token}")
    public ResponseEntity<Map<String, Object>> updateAppointment(@RequestBody Appointment appointment, @PathVariable String token) {
        service.validateToken(token, "patient");
        Map<String, Object> response = new HashMap<>();
        String result = appointmentService.updateAppointment(appointment);
        if (result.equals("Appointment updated successfully")) {
            response.put("message", result);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", result);
            return ResponseEntity.status(400).body(response);
        }
    }

// 6. Define the `cancelAppointment` Method:
//    - Handles HTTP DELETE requests to cancel a specific appointment.
//    - Accepts the appointment ID and a token as path variables.
//    - Validates the token for `"patient"` role to ensure the user is authorized to cancel the appointment.
//    - Calls `AppointmentService` to handle the cancellation process and returns the result.
    @DeleteMapping("/{id}/{token}")
    public ResponseEntity<Map<String, Object>> cancelAppointment(@PathVariable Long id, @PathVariable String token) {
        service.validateToken(token, "patient");
        Map<String, Object> response = new HashMap<>();
        try {
            appointmentService.cancelAppointment(id, patientRepository.findByEmail(tokenService.extractEmail(token)).getId());
            response.put("message", "Appointment canceled successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }
}
