# Attendance Tracker

## By Ossayed Khawaja

**The what and why of this application**
* This application will track attendance of students in various classes, in the context of a martial arts academy
* The target audience of this application would be instructors attempting to streamline attendance and gain more information regarding their students
* This project is of particular interest to me because I was an instructor for two years in highschool and the most annoying part of my job was marking off attendance cards and cleaning them at the end of the month

## User Stories
* As a user i want to be able to make a new student and add it to the academys members
* As a user i want to be able to be able to check in and have attendance recorded in a list of attendance
* As a user I want to be able to select a student and view details about them
* As a user I want to be able to remove a student from the list of students in my academy
* As a user I want to be able to save the students in my academy for later
* As a user I want to be able to load my saved students in my academy

## Instructions for grader
* you can generate the first required event by running guimain and  clicking "add student" and putting a name and an age for the student and clicking submit, be sure to input name and age in their respective fields 
* you can generate the second required event by clicking "attend" and putting in the students name and a number representing the day, again be sure to the put them in their respective fields
* you can generate a third event by clicking "remove" and putting in the students name
* you can see the students added to the academy by clicking "list students", be sure that there are students in the academy before clicking the button
* you can trigger the audio component by clicking any button
* click "save" to save the students in the academy
* click "load" to load the students in the academy

## Phase 4 Task 2
* I chose to test and design a class that was robust,the class in question is the members class
* The addNewMember, removeMember, and attendMember methods all throw checked execptions 
* These checked exceptions are caught in the gui class, As a result the ui is more robust
* I also added the exceptionstests class that tests all of the added exceptions
* Now if a student is added that is identical to an already existing student the copy is not added and the user is notified via an error window 
* If in the add student option a character is inputted in the age field the code no longer crashes and the user is notified 
* If in the remove student option if there no student by that name the user is notified
* In the attend class option if a number outside of the range of days is inputted the code no longer crashes and the user is notified 

## Phase 4 Task 3
* Problem 1: The tags "Name", "Age" "Belt" "Attendance" in the writer class are used in three different locations. The writer class,reader class and in the liststudents method in the Members class
* This is an example of coupling as in the event I decide to change the tags used for the student data stored I would have to change it in three different locations 
* I fixed this issue by creating the "Global" class and making four static string variables which are used in writer,reader and members
* Problem 2: The "members" class has the "attendmember" method that modifies the attendance of an individual student 
* This is an example of poor cohesion because the members classes purpose explicit purpose as mentioned in the class comment is "... add/remove students,gets students, list students" and not the individual details of each student, this is delegated to the student class whos explicit purpose is to "...  manage fields name,age,belt,attendance"
* This means that the members class is doing things unrelated to its original purpose so the class has poor cohesion
* I fixed this issue by using the "findMember" method in "Members" and using the attend method in "Students" class to modify attendance throughout my code, this way every method is fufilling its own purpose and not anothers
