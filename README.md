# Availity Homework Assignment
Availity’s Fullstack Homework Assignment

## 1. Tell me about your proudest professional achievement. It can also be a personal or school project.

My proudest professional achievement is the backorder schedule report. It is a project where users can set up their schedule from a Web Page and then they can get their reports by email based on frequency (Daily, Weekly, Monthly). Behind the scene for this project are:
+ Create a table to store when users input such as id, reportId, smReportId, source, frequency, email, and so on.
+ Create 3 different cron job based on frequency (Daily, Weekly, Monthly)
+ Read data from Database based on frequency (Daily, Weekly, Monthly) and call elastic data to build a report for each email
+ Create Excel file for each report and attach it in email
+ Send email to each user based on frequency (Daily, Weekly, Monthly)

## 2. Tell me about something you have read recently that you would recommend and why. (Can be a Github Repo, Article, Blog, Book, etc)

I have read reach js document from https://reactjs.org/docs/getting-started.html. It is a good document, I can follow step by step, then I can build a small project with reach js for the code assignment number 5. It provides more resources which I can read to explore more about reach js. The reason why I would recommend it is because it is a good place to start if you want to learn reach js.

## 3. How would you explain to your grandmother what Availity does?

I would explain to my grandmother about what I know from Availity's website. I will tell her, Availity is an easy place where she can see the latest information about her health information such as Eligibility and benefits, payment information, and Claims Management.

## 4. Coding exercise: You are tasked to write a checker that validates the parentheses of a LISP code.  Write a program (in Java or JavaScript) which takes in a string as an input and returns true if all the parentheses in the string are properly closed and nested.

You can get the solution for this exercise from the project folder by click on availity-parentheses-checker-exercise-four. It is Java Code, so you can run from the ParenthesesChecker class.

## 5. Coding exercise from a frontend perspective: Healthcare providers request to be part of the Availity system.  Using React framework, create a registration user interface so healthcare providers can electronically join Availity.  The following data points should be collected:
+ First and Last Name
+ NPI number
+ Business Address
+ Telephone Number
+ Email address

You can get the solution for this exercise from the project folder by click on availity-user-registration-user-exercise-five. It is reach js code.

## 6. Coding exercise:  Availity receives enrollment files from various benefits management and enrollment solutions (I.e. HR platforms, payroll platforms).  Most of these files are typically in EDI format.  However, there are some files in CSV format.  For the files in CSV format, write a program in a language that seems appropriate to you that will read the content of the file and separate enrollees by insurance company in its own file. Additionally, sort the contents of each file by last and first name (ascending).  Lastly, if there are duplicate User Ids for the same Insurance Company, then only the record with the highest version should be included. The following data points are included in the file:
+ User Id (string)
+ First and Last Name (string)
+ Version (integer)
+ Insurance Company (string)

You can get the solution for this exercise from the project folder by click on availity-csv-file-exercise-six. It is Java Code, I use spring boot for this project.
