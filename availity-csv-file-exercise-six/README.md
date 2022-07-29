# Coding exercise 6:
Availity receives enrollment files from various benefits management and enrollment solutions (I.e. HR platforms, payroll platforms).  Most of these files are typically in EDI format.  However, there are some files in CSV format.  For the files in CSV format, write a program in a language that seems appropriate to you that will read the content of the file and separate enrollees by insurance company in its own file. Additionally, sort the contents of each file by last and first name (ascending).  Lastly, if there are duplicate User Ids for the same Insurance Company, then only the record with the highest version should be included. The following data points are included in the file:
+ User Id (string)
+ First and Last Name (string)
+ Version (integer)
+ Insurance Company (string)

## Run
- Go to terminal and type:
mvn spring-boot:run
- http://localhost:8088/
- You will see two pages after click on "Go To CSV Demo" button:
-- Original Contents just display all contents from CSV file.
-- Custom Contents will display modify contents such as sort by first and last name (ascending), and remove duplicate User Ids for the same Insurance Company.
- You also can upload your own CSV file with the same format.
- You also can download CSV file for each Insurance Company.


