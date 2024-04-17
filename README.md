# Car Rental Application

The Car Rental Application is a Java-based application that allows users to rent cars and perform various operations related to car rental management. The application provides functionalities for user registration, login, car rental, car search, user management, and generating reports.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Usage](#usage)

## Features

The Car Rental Application offers the following features:

1. User Registration:
   - Users can create an account by providing their personal details and credentials.
   - The registration process ensures the uniqueness of usernames or email addresses.

2. User Login:
   - Registered users can log in to the application using their credentials.
   - User authentication is performed to validate the entered credentials.
   - Upon successful login, users are granted access to the application's functionalities.

3. Car Rental:
   - The application displays a list of available cars with corresponding details.
   - Users can select a car and proceed to rent it for a specified duration.
   - Rental details, including the user, car, rental start, and end dates, are recorded.

4. Car Search:
   - The application provides a user-friendly interface for convenient car search.
   - Search results are displayed with relevant details to assist users in making informed decisions.

5. User Management (Admin):
   - Admin users have additional privileges to manage the application and users.
   - Admin users can add new users and cars to the system.
   - Admin users can list all existing users, including their details.

6. Report Generation (Admin):
   - Admin users can generate reports for better insights into the car rental operations.
   - Reports can include information such as the list of cars currently on rent.
   - Additional reports can display idle cars that are currently not rented.
   - Admin users can also generate reports showing cars rented by specific users.

## Technologies Used

The Car Rental Application is built using the following technologies and frameworks:

- Java: The core programming language used for application development.
- Jakarta Persistence (JPA): A Java specification for managing persistence in Java applications.
- Hibernate: An object-relational mapping (ORM) framework for Java.
- Lombok: A library that simplifies Java code by reducing boilerplate code.

## Project Structure

The Car Rental Application follows a standard project structure to organize its source code and resources. Here's an overview of the project structure:

- `src/main/java/org.bilgeadam.controller`: Contains the application's controllers and menus.
- `src/main/java/org.bilgeadam.entity`: Contains the entity classes representing the application's data model.
- `src/main/java/org.bilgeadam.repository`: Contains the interfaces for data access and repositories.
- `src/main/java/org.bilgeadam.service`: Contains the services that implement the business logic.
- `src/main/java/org.bilgeadam.utility`: Contains utility classes for input/output handling and other common functionalities.
- `src/main/resources`: Contains configuration files and static resources used by the application.

## Usage

1. Registration:
   - Run the application and select the registration option from the menu.
   - Follow the prompts to provide your personal details and create an account.
   - Upon successful registration, you can proceed to log in.

2. Login:
   - Run the application and select the login option from the menu.
   - Enter your registered username and password.
   - If the credentials are valid, you will be logged in and can access the application's functionalities.

3. Car Rental:
   - Select the car rental option from the menu.
   - Follow the prompts and specify the rental duration and any other required details.
   - Confirm the rental, and you will receive a confirmation message.

4. Car Search:
   - Run the application. Login as admin and select the car search option from the menu.
   - Follow the prompts to search for cars based on brand, model, or year.
   - View the search results and check the details of each car.
   - Use the provided filters and sorting options to refine your search.

5. Admin User Management:
   - If you have admin privileges, you can add new users to the system.
   - Run the application and select the admin user management option from the menu.
   - Follow the prompts to add a new user by providing the required details.
   - The new user will be added to the system.

6. Report Generation:
   - If you have admin privileges, you can generate reports for better insights.
   - Runthe application and select the admin report generation option from the menu.
   - Follow the prompts to select the type of report you want to generate.
   - The report will be displayed on the console, providing information about the requested data.
  
7. Updating User Information:
   - Login to the application using your credentials.
   - Once logged in, select the "Update User Information" option from the menu.
   - Follow the prompts to enter the new information you want to update.
   - Confirm the changes, and your information will be updated in the system.

---

This project was developed for Java programming courses. [Bilge Adam](https://www.bilgeadam.com/).
