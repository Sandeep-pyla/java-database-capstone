Section 1: Architecture Summary
we will develop it in three layers presentation, application and data layers.

	in presentation we will use html and rest apis

	in application we will use services, controller and business logic layers.

	in data tier we use MySQL for structured and MongoDB for unstructured data.
	
	dashboards[Admin, Doctor]   --uses -> thymeleaf controllers --calls-> service layer
	REST modules[Appointments, Patient Dashboard, PatientRecord] --Uses JSON API->REST controllers --calls-> service layer.
	
    Service layer --uses-> MySQL repositories--Acesses->MYSQL database --> MySQL models <--JPA Entity--MySQL Models[Patient, doctor, appointment, Admin]
	Service layer --uses-> MongoDB repository --Accesses-> MongoDB database ->MongoDB Model <-Document--MongoDB Models[Prescritpion]
	
Section 2: Numbered flow of data and control
1. User accesses AdminDashboard or Appointment pages
2. The action is routed to appropriate Thymeleaf or REST controllers
3. Controllers call the service layer
4. service layer communicates with Repository layer to perform data access operations. MySQL for structured data and MongoDB for document-based records.
5. Each repository interfaces directly with the underlying database engine.
6. Once data is retrieved from the database, it is mapped into Java model classes that the application can work with. Also knwon as model binding. 
7. The bound models are used in response layers.
This marks the end of request-response cycle, delivering either a full web page or structured API data, depending on the consumer.