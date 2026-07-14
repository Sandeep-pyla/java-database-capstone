# User Story Template

**Title:**
_As a [user role], I want [feature/goal], so that [reason]._

**Acceptance Criteria:**
1. [Criteria 1]
2. [Criteria 2]
3. [Criteria 3]

**Priority:** [High/Medium/Low]
**Story Points:** [Estimated Effort in Points]
**Notes:**
- [Additional information or edge cases]

## Admin User Stories
**Title: 1**
_As a Admin, I want to Log into the portal with my username and password, so that I can manage the platform securely._

**Acceptance Criteria:**
1. Able to acceess the login page
2. Able to login with correct username and password
3. Able to make changes in platform.

**Priority:** High
**Story Points:** 3
**Notes:**
- [Additional information or edge cases]

**Title: 2**
_As a Admin, I want to Log Out of the portal, so that the system access is protected._

**Acceptance Criteria:**
1. able to log out from the portal
2. should not be able to make changes after logging out
3. username and password should not be visible after logging out

**Priority:** High
**Story Points:** 3
**Notes:**
- User has to be already logged in in order to log out

**Title: 3**
_As a admin, I want to add doctors to the platform, so that patients can book appointments with them._

**Acceptance Criteria:**
1. Success message after adding doctor
2. Added doctor to be visible in portal for booking
3. Doctor has necessary permissions to update his availability

**Priority:** Medium
**Story Points:** 4
**Notes:**
- need doctors details in order to add him

**Title: 4**
_As a admin, I want to delete doctors profile, so that non-practice doctors are removed._

**Acceptance Criteria:**
1. able to delete the doctor
2. doctor's data should not be shown on platform after deletion
3. the deleted doctor should not have permission to doctor portal

**Priority:** Medium
**Story Points:** 3
**Notes:**
- Doctor should be present before deleting

**Title: 5**
_As a Admin, I want to run a stored procedure in MySQL CLI, so that I can get the number of appointments per month and track usage statistics._

**Acceptance Criteria:**
1. able to run a stored procedure
2. stored procedure has to give output
3. metrics in output are as expected

**Priority:** Low
**Story Points:** 4
**Notes:**
- Data need to be present in the database.

## Patient User Stories

**Title: 1**
_As a Patient, I want to view list of doctors without logging in, so that I can explore optios before resgistering._

**Acceptance Criteria:**
1. Able to view doctors list
2. not logged in
3. not registered

**Priority:** High
**Story Points:** 3
**Notes:**
- Doctors database should be updated in order to display them for viewing

**Title: 2**
_As a Patient, I want to Sign up using my email and password, so that I can book appointments._

**Acceptance Criteria:**
1. Able to signup 
2. email and password in correct format
3. Has access to book appointments

**Priority:** High
**Story Points:** 4
**Notes:**
- [Additional information or edge cases]

**Title: 3**
_As a Patient, I want to log into the portal, so that I can manage my bookings._

**Acceptance Criteria:**
1. Able to login to the portal
2. Able to view bookings
3. Able to update the booking information

**Priority:** Medium
**Story Points:** 4
**Notes:**
- Have an registered account

**Title: 4**
_As a Patient, I want log out of the portal, so that I can secure my account._

**Acceptance Criteria:**
1. Able to log out of portal
2. Session terminated after log out
3. Login info is protected

**Priority:** Medium
**Story Points:** 3
**Notes:**
- Have to be logged in, in order to log out.

**Title: 5**
_As a Patient, I want to log in and book hour long appointments, so that I can consult with the doctor._

**Acceptance Criteria:**
1. Able to login with his credentials
2. Able to view the available doctors
3. Able to book hour long appointments

**Priority:** Low
**Story Points:** 3
**Notes:**
- Should be a registered patient

## Doctor User Stories

**Title: 1**
_As a Doctor, I want to log into the portal, so that I can manage my appointments._

**Acceptance Criteria:**
1. Able to log into the portal
2. Able to view appointments
3. Able to modify appointments

**Priority:** High
**Story Points:** 3
**Notes:**
- Doctor should already be registered

**Title: 2**
_As a Doctor, I want to logout from portal, so that I can protect my data._

**Acceptance Criteria:**
1. Able to log out of portal.
2. Session terminated after log out.
3. Log in info is protected.

**Priority:** High
**Story Points:** 3
**Notes:**
- [Additional information or edge cases]

**Title: 3**
_As a Doctor, I want to view my appointment calendar, so that I can stay organized._

**Acceptance Criteria:**
1. Able to view appointment calendar.
2. Able to view only his appointment calendar.
3. Has logged in to view

**Priority:** Medium
**Story Points:** 3
**Notes:**
- [Additional information or edge cases]

**Title: 4**
_As a Doctor, I want Mark my unavailability, so that I can inform patients only the available slots._

**Acceptance Criteria:**
1. Able to view calendar
2. Able to mark the unavailable time
3. able to view only his calendar

**Priority:** Medium
**Story Points:** 3
**Notes:**
- [Additional information or edge cases]

**Title: 5**
_As a Doctor, I want to update my profile with Specialization and contact information, so that patients have up-to-date information._

**Acceptance Criteria:**
1. Able to login
2. Able to view existing profile
3. Able to update profile with correct data

**Priority:** High
**Story Points:** 2
**Notes:**
- [Additional information or edge cases]
