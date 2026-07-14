## MySQL Database Design
Tables needed: patients, doctors, appointments, admin, clinic_locations, payments
### Patients table
    id INT primary key,
    name VARCHAR(100),
    email VARCHAR(100) not null unique, 
    password VARCHAR(100) not null
### doctors table
    id INT primary key,
    name VARCHAR(100),
    email VARHAR(100) not null unique,
    password VARCHAR(100) not null
### Appointments table
    id: INT primary key Auto increment,
    d_id: int, foregin key doctor(id),
    p_id: int, foreign key doctor(id),
    appointment_time: datetime, not null,
    status: int(0=scheduled, 1= completed, 2=Cancelled)
### Admin table
    id: int, primary key, auto increment,
    name: varchar(100),
    email: varchar(100) unique not null,
    password: varchar(100)
### doctorAvailability table

## MongoDB Collection Design
### Collection: Prescriptions
```json
{
    "_id": "ObjectId('64abc')",
    "patientName": "John Smith",
    "appointmentId": 51,
    "medication": "Paraetamol",
    "dosage": "500mg",
    "doctorNotes": "Take 1 tablet every 6 hours.",
  "refillCount": 2,
  "pharmacy": {
    "name": "Walgreens SF",
    "location": "Market Street"
  }
}