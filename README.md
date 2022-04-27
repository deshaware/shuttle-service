# Shuttle Services
Shuttle Services Backend for Transit and Parking Facilities - Syracuse University

## Problem Statement

Students who live off-campus at Syracuse University frequently face the 
challenge of 'how can I go back to my apartment this late?' after late-night on-campus shifts or 
pounding out homework in the library. Uber is not a cheap option. The university shuttle 
service is offered, however there is no time limit on how long you must wait. It’s a big problem 
to worry about! 
Also, the shuttle operations are manual. The administrator directs the driver over a radio to 
pick up and drop off students, which is a lot for a driver to handle. Students may also miss the 
pickup because there is no indication whether the shuttle is close to the pickup location

## Solution 

My goal is to solve this problem with a system that will allow students to pre-register 
a shuttle, so that they can plan their travel accordingly. This system will facilitate parking and 
transit service admin to reduce 90% of their daily operations activity. It will also reduce load on 
the driver to coordinate with admin over the radio. Also, the system will assign some pre-
credits to students to avoid misuse of the system. 

## System Architecture

![Backend Architecture](ShuttleService-Backend.pdf)

## Use Case:

We have 3 main actors in the system 

### Admin: 
 - Admin can add/modify shuttle 
 - Admin can Add/Modify which includes cancel trip 
 - Admin can Add/Modify actors like Students and Drivers 
 - Login functionality is available to admin 
 - To avoid misuse of system, admin can remove students or driver if violation 

### Driver: 
 - Driver has the ability to view their trip 
 - They can start/end/cancel a trip 
 - One Shuttle will have single driver but multiple trips 
 - Driver can login/logout from the application 
 - Driver’s important job is to pick/drop student, which can be achieved with one time password 

### Student/User: 
 - Student can view upcoming trips and register for one 
 - Student can inform driver during the pickup 
 - Student can cancel the trip if not needed 

### System: 
 - To send out notification 
