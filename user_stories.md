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

**Priority:** High
**Story Points:** 3
**Notes:**
- Doctor should be present before deleting

**Title: 5**
_As a Admin, I want to run a stored procedure in MySQL CLI, so that I can get the number of appointments per month and track usage statistics._

**Acceptance Criteria:**
1. able to run a stored procedure
2. stored procedure has to give output
3. metrics in output are as expected

**Priority:** High
**Story Points:** 4
**Notes:**
- Data need to be present in the database.