
Postman Collection - https://elements.getpostman.com/redirect?entityId=29665367-b3322c39-d995-421c-a9a0-ddd815c0b48d&entityType=collection

The API flow:
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
5. Admin users can now approve the loan and set the status as 'APPROVED'. The admin approves 
       The request and response:
        <img width="958" alt="4" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/93d50d40-983d-4b8f-b1eb-02b34f910b10">
        
        After approving 2 loans, the updated DB:
        <img width="1275" alt="5" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/5554e4aa-77ef-430d-a3b5-05745e193866">

6. Authenticated users can view their loans. The loans are retrieved based on the user, hence only their own loans are visible.
        The request and response:
        <img width="958" alt="6" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/00314786-ce2b-40ca-b620-19817a0ebab2">

7. Customers can pay back the loans based on a weekly payback schedule. 
