# Spring Boot Batch Job with Restful Api
## Program Flow
The batch job can only be processed by the Admin. First of all, the admin needs to login. Without login, no one can access the site. Once the admin is logged in, he is redirected to a page where he can start the batch job with a click. The batch job takes the student.csv file located inside resources folder as a input and processes it (age to birthdate) and saves into mysql database.
All those saved data are then retrieved and displayed into the admin page. 
During startup the student table is dropped and new table is set up. 
### Security
Spring Security is implemented. Only the user with Admin previledge is authorizes to view the page. 
<!--stackedit_data:
eyJoaXN0b3J5IjpbMjMyMzI1NDcxXX0=
-->