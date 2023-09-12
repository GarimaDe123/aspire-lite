select * from loan;
select * from loan_repayment;

http://localhost:8080/h2-console/login.jsp?jsessionid=712b1835e3031421e7710416bb6ee76a

Extra:
User should login, and by identifying the user, system should allow access to loans based on id

Login can be 1st api with user name and password, and 2nd api with token in header

Enable logging

Write test cases

Swagger API

Postman Collection - https://elements.getpostman.com/redirect?entityId=29665367-b3322c39-d995-421c-a9a0-ddd815c0b48d&entityType=collection



The code flow:
1. There are three pre created users, one admin user - 'aspire_admin' and two customers - 'aspire_user1' and 'aspire_user2'.
2. Only admin user have access to /admin APIs.
3. Firstly, we call the /customer/loan/create API.
    We select Basic Auth, and pass username, password. 
     <img width="949" alt="Screenshot 2023-09-12 084245" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/0212f10b-1f0d-4650-ab73-6331eacc7ae0">


4.

