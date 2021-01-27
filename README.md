# Attendance Tracker

## By Ossayed Khawaja

**The what and why of this application**
* This application will track attendance of students in various classes, in the context of a martial arts academy
* The target audience of this application would be instructors attempting to streamline attendance and gain more information regarding their students
* This project is of particular interest to me because I was an instructor for two years in highschool and the most annoying part of my job was marking off attendance cards and cleaning them at the end of the month



## Added Features
* I chose to test and design a class that was robust,the class in question is the members class
* The addNewMember, removeMember, and attendMember methods all throw checked execptions 
* These checked exceptions are caught in the gui class, As a result the ui is more robust
* I also added the exceptionstests class that tests all of the added exceptions
* Now if a student is added that is identical to an already existing student the copy is not added and the user is notified via an error window 
* If in the add student option a character is inputted in the age field the code no longer crashes and the user is notified 
* If in the remove student option if there no student by that name the user is notified
* In the attend class option if a number outside of the range of days is inputted the code no longer crashes and the user is notified 

## Fixed Bugs
* Problem 1: The tags "Name", "Age" "Belt" "Attendance" in the writer class are used in three different locations. The writer class,reader class and in the liststudents method in the Members class
* This is an example of coupling as in the event I decide to change the tags used for the student data stored I would have to change it in three different locations 
* I fixed this issue by creating the "Global" class and making four static string variables which are used in writer,reader and members
* Problem 2: The "members" class has the "attendmember" method that modifies the attendance of an individual student 
* This is an example of poor cohesion because the members classes purpose explicit purpose as mentioned in the class comment is "... add/remove students,gets students, list students" and not the individual details of each student, this is delegated to the student class whos explicit purpose is to "...  manage fields name,age,belt,attendance"
* This means that the members class is doing things unrelated to its original purpose so the class has poor cohesion
* I fixed this issue by using the "findMember" method in "Members" and using the attend method in "Students" class to modify attendance throughout my code, this way every method is fufilling its own purpose and not anothers
