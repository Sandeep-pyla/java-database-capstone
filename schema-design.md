## MySQL Database Design
Tables needed: patients, doctors, appointments, admin, clinic_locations, payments
### Admin table
    id: int, primary key, auto increment,
    name: varchar(100),
    password: varchar(100)

### Appointments table
    id: INT primary key Auto increment,
    d_id: int, foreign key doctor(id),
    p_id: int, foreign key patient(id),
    appointment_time: LocalDateTime, not null,
    status: int(0=scheduled, 1= completed)

### doctors table
    id INT primary key,
    name VARCHAR(100),
    speciality: VARCHAR(100),
    email VARHAR(100) not null unique,
    password VARCHAR(100) not null,
    phone: VARCHAR(10), not null, only 10 digits long,
    availability: List<String>, not null

### Patients table
    id INT primary key,
    name VARCHAR(100),
    email VARCHAR(100) not null unique, 
    password VARCHAR(100) not null,
    phone: VARCHAR(10) not null only 10 digits long,
    address: VARCHAR(255) not null.

## MongoDB Collection Design
### Collection: Prescriptions
    id: private String, primary key,
    patientName: String, not null,  
    appointmentId: int, not null, foreign key to appointments collection,
    medication: String, not null,
    dosage: String, not null,
    doctorNotes: String, not null,
    refillCount: int, not null,
    pharmacy: {
        name: String, not null,
        location: String, not null
    }
    
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