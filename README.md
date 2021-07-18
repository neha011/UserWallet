Import Project int Spring Tool Suits.
Database Used: mysql/sys
credentials: root/password -- you can add your credentials in application.properties
table name : users,transactions,wallet,roles,user_role
Service tool : Postman
http port default : 8080
**********************************************************************************************************************************************
#For login & authentication - admin sign up is done via code
POST>localhost:8080/oauth/token
Add under Body as form-data: Below mentioned values are key/value
grant_type/password
client_id/live-test
username/admin@mail.com
password/admin@123
client_secret/abcde

This will generate token for admin and if you want to create it for any other user then pass its mailId and password in username/password fields.
*********************************************************************************************************************************************
#For Sign up- creating different users.
POST>localhost:8080/users
{    
    "firstName": "Neha",
    "middleName": "",
    "lastName": "Bansal",
    "dateOfBirth": 01-01-2000,
    "emailId": "neha@mail.com",
    "mobileNumber": 123456 
}
This API is permitted to all.
*****************************************************
#To view all the users
GET> localhost:8080/users
This API is permitted to ADMIN only so its token will be required.
You can add token under Headers.
Key = Authorization , Value= bearer {token genrated above}
*****************************************************
#To view a specific user
GET> localhost:8080/users/{userid}
This API is permitted to USER only so its token will be required.
You can add token under Headers.
Key = Authorization , Value= bearer {token genrated for that specific user.}
*****************************************************
#Add Money to wallet
POST> localhost:8080/wallet
       {
       
        "mobileNumber": 12313,
        "amount": 50
		}
		
Here, you need to provide mobile number to which you want to add money to.
This API is permitted to USER only so its token will be required.
You can add token under Headers.
Key = Authorization , Value= bearer {token genrated for that specific user.}	
*****************************************************
#Update user details
PUT>localhost:8080/users"

{
	"userId":1,
    "firsName" : "Naina"
}
NOTE: updating Mobile Number,emailId & Password is not allowed.
This API is permitted to USER only so its token will be required.
You can add token under Headers.
Key = Authorization , Value= bearer {token genrated for that specific user.}
*****************************************************
#Transfer Money
PUT>localhost:8080/users/transaction
{
        "FromUserId": 1,
        "amount": 30,
        "mobileNumberTo":3333
    }

This API is permitted to USER only so its token will be required.
You can add token under Headers.
Key = Authorization , Value= bearer {token genrated for that specific user.}	
*****************************************************
#To view passbook of a particular user
GET>localhost:8080/users/passbook/{userId}
This API is permitted to USER only so its token will be required.
You can add token under Headers.
Key = Authorization , Value= bearer {token genrated for that specific user.}	
*****************************************************
