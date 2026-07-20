/*
Import the overlay function for booking appointments from loggedPatient.js

  Import the deleteDoctor API function to remove doctors (admin role) from docotrServices.js

  Import function to fetch patient details (used during booking) from patientServices.js

  Function to create and return a DOM element for a single doctor card
    Create the main container for the doctor card
    Retrieve the current user role from localStorage
    Create a div to hold doctor information
    Create and set the doctor’s name
    Create and set the doctor's specialization
    Create and set the doctor's email
    Create and list available appointment times
    Append all info elements to the doctor info container
    Create a container for card action buttons
    === ADMIN ROLE ACTIONS ===
      Create a delete button
      Add click handler for delete button
     Get the admin token from localStorage
        Call API to delete the doctor
        Show result and remove card if successful
      Add delete button to actions container
   
    === PATIENT (NOT LOGGED-IN) ROLE ACTIONS ===
      Create a book now button
      Alert patient to log in before booking
      Add button to actions container
  
    === LOGGED-IN PATIENT ROLE ACTIONS === 
      Create a book now button
      Handle booking logic for logged-in patient   
        Redirect if token not available
        Fetch patient data with token
        Show booking overlay UI with doctor and patient info
      Add button to actions container
   
  Append doctor info and action buttons to the car
  Return the complete doctor card element
*/
import {showBookingOverlay} from "../loggedPatient";
import {deleteDoctor} from "../services/doctorServices";
import {fetchPatientDetails} from "../services/patientServices";

export function createDoctorCard(doctor) {
    const card = document.createElement('div');
    card.className = 'doctor-card';

    const userRole = localStorage.getItem('userRole');

    const doctorInfo = document.createElement('div');
    doctorInfo.className = 'doctor-info';

    const name = document.createElement('h3');
    name.textContent = doctor.name;

    const specialization = document.createElement('p');
    specialization.textContent = `Specialization: ${doctor.specialization}`;

    const email = document.createElement('p');
    email.textContent = `Email: ${doctor.email}`;

    const availableTime = document.getElementById('availableTime');
    availableTime.textContent = `Available Time: ${doctor.availableTime}`;

    doctorInfo.appendChild(name);
    doctorInfo.appendChild(specialization);
    doctorInfo.appendChild(email);
    doctorInfo.appendChild(availableTime);

    const actions = document.createElement('div');
    actions.className = 'card-actions';

    if (userRole === 'admin') {
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', async () => {
            const token = localStorage.getItem('token');
            if (!token) {
                alert('Admin token not found. Please log in again.');
                return;
            }
            try {
                const response = await deleteDoctor(doctor.id, token);
                if (response.success) {
                    alert('Doctor deleted successfully.');
                    card.remove();
                } else {
                    alert('Failed to delete doctor: ' + response.message);
                }
            } catch (error) {
                console.error('Error deleting doctor:', error);
                alert('An error occurred while deleting the doctor.');
            }
        });
        actions.appendChild(deleteButton);
    } else if (userRole === "patient") {
        const bookNow = document.createElement("button");
        bookNow.textContent = "Book Now";
        bookNow.addEventListener("click", () => {
            alert("Patient needs to login first.");
        });
        actions.appendChild(bookNow);
    } else if (userRole === "loggedPatient") {
        const bookNow = document.createElement("button");
        bookNow.textContent = "Book Now";
        bookNow.addEventListener("click", async (e) => {
            const token = localStorage.getItem("token");
            const patientData = await fetchPatientDetails(token);
            showBookingOverlay(e, doctor, patientData);
        });
        actions.appendChild(bookNow);
    }

    card.appendChild(doctorInfo);
    card.appendChild(actions);

    return card;
}