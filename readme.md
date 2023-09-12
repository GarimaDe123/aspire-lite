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
        <img width="958" alt="4" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/1f5768d9-e14a-4fbf-b2e4-49459ab090e8">

   After approving 2 loans, updated DB-
   <img width="1270" alt="11" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/b6e7a12f-3d4a-4ec1-97ed-74d8c30eeeb3">


6. Authenticated users can view their loans. The loans are retrieved based on the user, hence only their own loans are visible.
        The request and response:
        <img width="958" alt="6" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/00314786-ce2b-40ca-b620-19817a0ebab2">

7. Customers can pay back the loans based on a weekly payback schedule. The payment status will change to 'PAID' for all the repayments as     soon as payment is successful. Once all the repayments are done, the loan will also move to the 'PAID' state.
    The payment can only be made for approved loans.
    The current state of DB:
    <img width="1270" alt="7" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/5b5a2da2-5780-47fc-ab72-51dc47fd772d">

    Now, we will pay the first repayment of loan_id:1.
    Payment is only allowed for authenticated users, for their own loans. The user is not allowed to pay for someone else's loan.
    
    The request and response:
    <img width="953" alt="8" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/cc7ce3ff-e3c3-4dfc-b371-0ee3ddf96913">

    The repayment state is updated to 'PAID'
    <img width="1240" alt="9" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/7a556074-2000-45d8-b585-89575fae597f">

    Similarly, make the remaining payments. When the last repayment is completed, the Loan status is changed to 'PAID'.
    <img width="1274" alt="10" src="https://github.com/GarimaDe123/aspire-lite/assets/144749043/e4eded75-8d5f-4b06-bd06-8a3104d4506b">

8. Postman Collection - https://elements.getpostman.com/redirect?entityId=29665367-b3322c39-d995-421c-a9a0-ddd815c0b48d&entityType=collection


