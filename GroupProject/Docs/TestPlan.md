# Test Plan


**Author**: TEAM 094

## 1 Testing Strategy

### 1.1 Overall strategy
Our testing strategy encompasses unit, integration, system, and regression testing, utilizing both automated and manual approaches. Automated tests, focusing on unit and integration levels, will be written and maintained alongside code development. Developers will be responsible for adding or updating these tests as they modify the code. Manual system tests will be carried out based on the use case diagram, ensuring functionality and correctness. Team members not involved in the code section being tested will perform these manual tests. Regression testing will involve re-running unit and integration tests to ensure recent changes do not negatively impact existing functionalities. This will be conducted by specific team members as detailed in our plan.

### 1.2 Test Selection
Our test selection strategy incorporates both white-box and black-box techniques. Unit and integration tests will employ white-box testing methods, including code coverage analysis (MC/DC), to ensure thorough examination of code paths. System testing will utilize black-box testing methods, specifically the Category Partition Method, based on inputs and expected outputs derived from the use case model. Regression testing will re-use these tests to confirm system stability after code changes.

### 1.3 Adequacy Criterion
We will assess the quality of our test cases through coverage criteria. For unit and integration testing, we will target MC/DC coverage, ensuring multiple conditions and decision paths are evaluated. System and regression tests will be considered adequate if they comprehensively cover the use case model, ensuring all functionalities are tested against their expected behaviors.

### 1.4 Bug Tracking
Bug tracking and enhancement requests will be managed via GitHub Issues. Each issue will record details such as the time reported, severity, current status (e.g., reported, assigned, in progress, closed, reopened), specific details of the bug or request, and the identities of the reporter and assignee. This structured approach will help ensure all issues are properly documented and addressed.

### 1.5 Technology
We plan to use JUnit for both regression and system testing. This choice will allow us to automate the testing process efficiently and integrate it seamlessly with our development workflow. Additionally, we will leverage the Issues tracking feature in GitHub to monitor and manage bugs and enhancement requests systematically.

## 2 Test Cases

| #  | Purpose                                    | Steps                                                         | Expected Result                                              | Actual Result | Pass/Fail | Additional Info |
|----|--------------------------------------------|---------------------------------------------------------------|--------------------------------------------------------------|---------------|-----------|-----------------|
| 1  | Validate saving a new user job entry       | 1. Navigate to job details entry form<br />2. Populate all required fields<br />3. Click Save<br />4. Reopen job details form | The newly entered job details should be correctly displayed in the form fields. |               |           |                 |
| 2  | Verify job details editing functionality   | 1. Follow steps in test case 1<br />2. Modify the existing job details<br />3. Click Save<br />4. Reopen job details form | The updated job details should be correctly displayed in the form fields. |               |           |                 |
| 3  | Test job details form cancellation         | 1. Navigate to job details entry form<br />2. Populate all required fields<br />3. Click Cancel<br />4. Reopen job details form | The form should display the original values, discarding any changes. |               |           |                 |
| 4  | Ensure job offer saving functionality      | 1. Navigate to job offer entry form<br />2. Populate all required fields<br />3. Click Save<br />4. Return to main menu<br />5. Access compare job offers feature | The newly saved job offer should be listed among the job offers available for comparison. |               |           |                 |
| 5  | Verify job offer deletion functionality    | 1. Follow steps in test case 4 to save a job offer<br />2. Select the saved job offer<br />3. Click Delete | The selected job offer should be removed from the list of job offers. |               |           |                 |
| 6  | Validate multiple job offer entries        | 1. Navigate to job offer entry form<br />2. Populate all required fields<br />3. Click Save<br />4. Click to enter another offer | The form should clear and be ready for new job offer entry. |               |           |                 |
| 7  | Test job offer comparison functionality    | 1. Enter at least two job offers<br />2. Select compare job offers<br />3. Choose jobs to compare<br />4. Click compare offer | The selected job offers should be displayed side by side in a comparison table. |               |           |                 |
| 8  | Verify adjustment of comparison settings   | 1. Navigate to settings<br />2. Access adjust comparison settings<br />3. Modify a comparison value<br />4. Return to settings screen | The updated comparison settings should be correctly reflected. |               |           |                 |
| 9  | Ensure visibility of comparison table      | 1. Enter multiple job offers using steps from test case 4<br />2. Access compare job offers feature | All entered job offers should be visible and ranked by score, with the current job clearly marked. |               |           |                 |
| 10 | Test detailed job comparison display       | 1. Follow steps from test case 8<br />2. Select two jobs and click view comparison | The selected jobs should be displayed side by side in a detailed comparison table. |               |           |                 |
| 11 | Validate job score calculation accuracy    | Create unit tests with predefined inputs and expected outputs to test score calculation accuracy. | The unit tests should produce the expected score outputs given the predefined inputs. |               |           |                 |
| 12 | Ensure database saving and retrieval       | Create integration tests to save and retrieve job data from the database. | The integration tests should successfully save and retrieve the correct job data. |               |           |                 |
