Section 1: Architecture Summary
This Spring Boot application uses both MVC and REST controllers. Thymeleaf templates are used for the Admin and Doctor dashboards, while REST APIs serve all other modules. The application interacts with two databases—MySQL (for patient, doctor, appointment, and admin data) and MongoDB (for prescriptions). All controllers route requests through a common service layer, which in turn delegates to the appropriate repositories. MySQL uses JPA entities while MongoDB uses document models.
Section 2: Numbered flow of data and control
1. User accesses AdminDashboard or Appointment pages
2. The action is routed to appropriate Thymeleaf or REST controllers
3. Controllers call the service layer
4. service layer communicates with Repository layer to perform data access operations. MySQL for structured data and MongoDB for document-based records.
5. Each repository interfaces directly with the underlying database engine.
6. Once data is retrieved from the database, it is mapped into Java model classes that the application can work with. Also knwon as model binding. 
7. The bound models are used in response layers.
This marks the end of request-response cycle, delivering either a full web page or structured API data, depending on the consumer.