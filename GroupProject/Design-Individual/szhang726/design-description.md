# Design Description

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet1 ).

* I designed App class as the entry point of this whole program and a displayMainMenu() operation in the App to show the main menu. In addition, I designed a MainMenu class to manages user interactions with the main functionalities, which contains attibutes as currentJob, jobOffers, comparisonSettings and operations as enterOrEditCurrentJobDetail(), enterJobOffers()adjustComparisonSettings(), compareJobOffers().

2. When choosing to enter current job details, a user will be shown a user interface to enter (if it is the first time) or edit all the details of their current job and be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

* I designed a class JobRecord to represent the current job details the user might want to compare against job offers. I encapsulate attributes related to job details and provide a method to edit these details, promoting encapsulation and data integrity.

3. When choosing to enter job offers, a user will be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job. User will be able to either save the job offer details or cancel and (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

* I designed JobEnterMenu class to record down the entered details and designed operations to satisfy the system need.

4. When adjusting the comparison settings, the user can assign integer weights.

* I designed ComparisonSetting class to hold the user-defined settings for comparing job offers. This class encapsulate weights to different factors and provides a method to adjust these settings, allowing for flexible comparison criteria.

5. When choosing to compare job offers, a user will be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated. Select two jobs to compare and trigger the comparison.Be shown a table comparing the two jobs, displaying, for each job. Be offered to perform another comparison or go back to the main menu.

* In MainMenu, I designed jobOffers as a list of JobRecord. MainMenu interacts with JobOfferComparator and I delegates the comparison logic to the  JobOfferComparator class , keeping the MainMenufocused on user interaction management.

6. When ranking jobs, a job’s score is computed as the weighted average.

* This part of logic will be implemented in JobOfferComparator. 
