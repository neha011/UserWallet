Import Project int Spring Tool Suits.
Database Used: mysql/sys
credentials: root/password -- you can add your credentials in application.properties
table name : users,transactions,wallet
Service tool : Postman
http port default : 8080
*****************************
#For Sign up
POST>localhost:8080/users
{    
    "firstName": "Neha",
    "middleName": "",
    "lastName": "Bansal",
    "dateOfBirth": 01-01-2000,
    "emailId": "neha@mail.com",
    "mobileNumber": 123456 
}

*****************************************************
#To Sign In
POST>localhost:8080/users/sign-in
{
    "mobileNumber":123456,
    "password":"123neha"
}
*****************************************************
#To view all the users
GET> localhost:8080/users
*****************************************************
#To view a specific user
GET> localhost:8080/users/{userid}
*****************************************************
#To view passbook of a particular user
GET>localhost:8080/users/passbook/{userId}
*****************************************************
#Add Money to wallet
POST> localhost:8080/wallet
       {
       
        "mobileNumber": 12313,
        "amount": 50
		}
		
*****************************************************
#Update user details
PUT>localhost:8080/users"

{
	"userId":1,
    "firsName" : "Naina"
}
NOTE: updating Mobile Number & Password is not allowed.
*****************************************************
#Transfer Money
PUT>localhost:8080/users/transaction
{
        "FromUserId": 1,
        "amount": 30,
        "mobileNumberTo":3333
    }

*****************************************************
