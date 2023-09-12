
Postman Collection - https://elements.getpostman.com/redirect?entityId=29665367-b3322c39-d995-421c-a9a0-ddd815c0b48d&entityType=collection

The code flow:
1. There are three pre-created users, one admin user - 'aspire_admin' and two customers - 'aspire_user1' and 'aspire_user2'.
2. Only admin users have access to /admin APIs.And customers have access to /customer APIs.
3. An in-memory h2 DB is used.
4. Firstly, call the /customer/loan/create API.
    We select Basic Auth and give a username and password.
     <img width="949" alt="Screenshot 2023-09-12 084245" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/0212f10b-1f0d-4650-ab73-6331eacc7ae0">
    The request and response:
    <img width="953" alt="2" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/4ba94c7b-09bb-42b2-ba08-da5238724f26">
    We similarly create a couple of more loans for another user.
    Finally, the DB tables will have this data:
    There are two users, the first user has 1 loan, and the second user has 2 loans.
   <img width="1280" alt="3" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/869e9a40-e4e1-49be-9c77-4289afff1f50">
5.

