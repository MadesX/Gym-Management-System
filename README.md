# Gym-Management-System

Java application using GUI for managing a gym  with login system for different user roles: Manager, Trainer, Member. 

# Project Overview

This Java-based Gym Management System is a role-driven desktop application designed to streamline gym operations by providing distinct access for Manager, Trainers, and Members.  
Each user role is secured with unique login credentials and presented with tailored menu options for a smooth, role-appropriate user experience.

# Key Features

* Login and Registration systems.
* Role-Based Access:

    - Member Account:  
        - Register and view training sessions.  
        - View gym's weekly schedule.  
        - View or purchase membership.  
        - View and update details.
    - Trainer Account:
        - View their training sessions.  
        - View gym's weekly schedule.  
        - View and update details.   
    - Manager Account:
        - Remove / Add members and trainers into the system.
        - Cancel member's membership.
        - Create / Cancel training sessions.
        - View gym's weekly schedule.
        - Start equipment maintenance.
        - Purchase new equipment.
        - View all training sessions.
     
* Membership plans & discounts:
    - Daily    (no discount)
    - Monthly  (5% discount)
    - Yearly   (10% discount)
    - Soldier  (25% off)
    - Student  (15% off)
    - Elderly  (20% off)

* Session registration:
    - Group training sessions (up to capacity).
    - Personal one-on-one sessions with a trainer .

* Payment processing: cash and card payments.
* Predefined Credentials: The system comes with predefined usernames and passwords for testing purposes (see below).
* User-Friendly Menus: Each user role has a customized menu with available options tailored to their permissions.
* Data persistence: in-memory storage on .txt file via DataBase class.

# How to Use

**0. Create Database**

To use the system with our database for testing run CreateDatabase.java.  
If you do not wish to use the existing database skip this step for a clean start (Note: in this case you cannot log in and must register first).

**1. Running the Program**

Run Gym.java

    Ensure that Java is installed on your system.
    Compile and run the program using your preferred Java IDE or command-line tool.

**2. Logging In**

Upon launching the program, you will be prompted to register or log in with a username and password. Ensure that you enter both in the correct case.  

**Sample Login Credentials:**  

**Member with membership and training sessions** 
* Username: 123336789  
  Password: password
   
**Member without membership** 
* Username: 123456803  
  Password: password

**Trainer with training sessions** 
* Username: 111111189  
  Password: password
   
**Trainer without training sessions** 
* Username: 113456792  
  Password: password

**Manager:**  
* Username: admin    
Password: admin

** These are just examples from CreateDatabase.java , feel free to pick anything else.

**3. Exploring Menus**

Once logged in, users will be directed to a main menu based on their role.  
Each menu presents options relevant to the user’s role (learn more in key features).

# System Requirements

    Java Development Kit (JDK) 8 or higher
    Uses only standard AWT/Swing (no external libraries)
    Any operating system that supports Java
    
# Notes

    Adjust gym name, membership discounts or session capacities in Constants.java .
    The program is case-sensitive, so ensure usernames and passwords are entered correctly.
    The predefined usernames and passwords are for testing purposes. You can modify these in the
    source code if needed.
    This project was built in Eclipse.

# License
        
    This project is licensed under a custom license:
    You may use, copy, and modify the code for personal or non-profit purposes for free.
        
    If you wish to use the code in any commercial or for-profit product, you must contact the author and
    may be required to pay a fee or share profits.
        
    © 2025 Ron Haba and Matan Sides. 
    All rights reserved.
