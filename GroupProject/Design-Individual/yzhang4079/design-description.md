1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).?

Explain:
To fulfill the requirement, I created a class called main menu and it has four functions to go to interfaces of job details, job offers, comparison settings, or compare job offers. If no jobs are entered, I plan to raise error and make a hint to user.

2. When choosing to enter current job details, a user will:
(a) Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consists of:
Title
Company
Location (entered as city and state)
Cost of living in the location (expressed as an index)
Yearly salary 
Yearly bonus 
Training and Development Fund
Leave Time
Telework Days per Week
(b) Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

Explain:
To fulfill the requirement, I made an interface called Current job. It has Enter(), Edit(), Save(), and Cancel() operations to make sure the interface can enter, edit, and save the job details. The interface is connected to the class Current job for job details. Since Yearly salary, Yearly bonus , and Training and Development Fund are measured in money, I also created a class called money. In the Current job class, I make types of these variables be money. Also, Leave Time's type is supposed to be a date, So I created a class called date. The exist operation can help users go back to the main menu.

3. When choosing to enter job offers, a user will:
(a) Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
(b) Be able to either save the job offer details or cancel.
   ? Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

Explain:
For the first part of requirement 3, I made the same interface just as requirement 2 called Job offer. Instead, the Job Offer interface has two more functions continue() and compare(). continue() is supposed to be a UI button for users to click when the user wants to add more job offers after entering one. Compare() will call the compare class which can compare current job and job offer. The exist operation can help users go back to the main menu.


4. When adjusting the comparison settings, the user can assign integer weights to:
Yearly salary
Yearly bonus
Training and Development Fund
Leave Time
Telework Days per Week
If no weights are assigned, all factors are considered equal.
NOTE: These factors should be integer-based from 0  to 9 (highest interest)

Explain:
I created a class called Comparison Setting. In the class, all required weights are attributes of the class. All the weights should be integers from 0 to 9. If no weights entered, all weights will be set to 1.

5. When choosing to compare job offers, a user will:
(a) Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
(b) Select two jobs to compare and trigger the comparison.
? Be shown a table comparing the two jobs, displaying, for each job:
Title
Company
Location 
Yearly salary adjusted for cost of living
Yearly bonus adjusted for cost of living
TDF = Training and Development Fund
LT = Leave Time 
RWT = Telework Days per Week 
      (d) Be offered to perform another comparison or go back to the main menu.

Explain:
To fulfill the requirement, the main menu class has operation compare job offers to call the Compare class(). The Compare Class will receive weights set by Comparison setting and calculate the score of each job using computescores operations. After that, rankjoboffers will sort the joblist by using the score and make it becomes ranklist. Like other interfaces like Current job, the compare class also has two operations continue() and exist() to make new compare and exist to the main menu,

6. When ranking jobs, a job��s score is computed as the weighted average of:
AYS + AYB + TDF + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8))

where:
AYS = Yearly Salary Adjusted for cost of living
AYB = Yearly Bonus Adjusted for cost of living
TDF = Training and Development Fund  ($0 to $18000 inclusive annually)
LT = Leave Time  (0-100 whole number days inclusive)
RWT = Telework Days per Week 

NOTE: The rationale for the RWT subformula is:
-??value of an employee hour = (AYS / 260) / 8
-??commute hours per year (assuming a 1-hour/day commute):
                              1 * (260 - 52 * RWT)
-       therefore travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8
For example, if the weights are 2 for the yearly salary, 2 for the yearly bonus, 2 for the Training and Development Fund, and 1 for all other factors, the score would be computed as:
2/8 * AYS + 2/8 * AYB + 2/8 * TDF + 1/8 * (LT * AYS / 260) - 1/8 * ((260 - 52 * RWT) * (AYS / 260) / 8)))

Explain:
Since the computescore is a operation under the compare class, I do not write details for the operation in the UML. Basically, the compare class will receive weights from the comparison setting. Then the computescore function will compute the weighted average and use the formula above to calculate the score of the job offer.

7. The user interface must be intuitive and responsive.

Explain:
Since the requirement is fully UI-designed, I do not show it in my UML. Instead, when designing the UI, I will add hints for each process the user or the input is not valid.

8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

Explain:
Because of the requirement, I did not make any class called device or database. Thus, all the information will not be uploaded online and the device can only run the app locally and cannot connect to other devices.
