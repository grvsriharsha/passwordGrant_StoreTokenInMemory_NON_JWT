# Password__inmemory_oauth

First run the sql files in sql folder.

The whle project is about to haveing 2 apis,get and post. 
There are 2 users one is admin,other is user.So the Roles are created for the users.

Now we configure the websecurity adapter so that
 1. Only Admins can use post
 2. Both Admins and users can use GET
 
 User Logs in with email and pasword

With UserRoles being configured,we want to enable to use Authorization,Resource servers so that user logins in the front end(react) with credentials ,the front end hits backend to genrate the token and uses that token further to communicate with backend instaed of frontend sending the credentilas of the user to the backends everytime
