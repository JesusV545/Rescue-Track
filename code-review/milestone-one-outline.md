# CS 499 Milestone One Code Review

## Introduction
Hello, my name is Jesus Vazquez. For my CS capstone project I selected the Rescue-Track application as my primary artifact. Rescue-Track is a Java-based animal rescue management system that lets users to intake rescue animals, reserve animals for service, and view information about animals currently stored in the system. This artifact demonstrates object-oriented programming concepts and provides opportunities for improvements related to software design, algorithms and data structures, and database functionality. In this code review, I will discuss the current functionality of the application, analyze areas for improvement, and explain the enhancements I plan to implement throughout the capstone course.

## Category One: Software Design and Engineering

### Existing Functionality
The Driver class serves as the entry point of the application. The program maintains separate ArrayLists for dogs and monkeys and provides a menu-driven interface that allows users to add animals, reserve animals, and generate reports. The application also initializes sample data for testing purposes and continues running until the user chooses to exit.

### Code Analysis
One strength of the current design is its use of inheritance. The `RescueAnimal` class serves as a reusable base class, while the `Dog` and `Monkey` classes extend its functionality by inheriting from it and adding specific attributes, such as breed or species. Additionally, encapsulation is achieved through the use of private variables and public getter/setter methods.

Another strength lies in the logical separation of functionality into methods such as `intakeNewDog`, `intakeNewMonkey`, `reserveAnimal`, and `printAnimals`. This improves code readability and facilitates application maintenance.

However, there are also some weaknesses.

Currently, the `Driver` class handles almost all tasks—including business logic, user interaction, input validation, and report generation. As a result, the class has become bloated and overly centralized; this not only violates the principle of "separation of concerns" but also complicates future maintenance.

Furthermore, the frequent use of the String data type for fields such as age, weight, and acquisition date presents an issue. This limits input validation and increases the risk of invalid or incorrect data entering the system.

The scope of input validation is also limited. While duplicate checks are performed for animal names and monkey species, much of the other user input is accepted without validation. No validation mechanisms are implemented for numeric ranges, date formats, or unexpected input.

### Planned Enhancements
To improve the quality of software design and development, we plan to strengthen input validation across the entire application. We will validate user input before the system processes it to minimize errors and enhance reliability.

We will also refactor parts of the Driver class to decouple business logic from the user interface. This approach will improve maintainability and facilitate future application expansion.

Furthermore, we will implement advanced exception handling and defensive programming techniques to bolster stability and security. These improvements will reduce the risk associated with invalid or unexpected inputs and ensure a design that adheres to secure software development principles.

### Skills Demonstrated
This project demonstrates object-oriented programming, inheritance, encapsulation, method design, modular programming, and software architecture. It also showcases the ability to design and maintain Java applications composed of multiple classes.

### Course Outcomes
This improvement reinforces the learning objectives of the course on the development and evaluation of computer solutions. Furthermore, it demonstrates the ability to apply software engineering principles to improve maintainability, security, and overall software quality.

## Category Two: Algorithms and Data Structures

### Existing Functionality
The Rescue-Track application currently uses ArrayList collections to store animal data; one list holds data for dogs, while the other holds data for monkeys. These collections support functions such as adding new animals, searching for existing animals, making reservations, and generating reports.

To identify animals and check their availability, the application performs an iterative search, examining records sequentially until a match is found.

### Code Analysis
An ArrayList is suitable for small-scale applications and demonstrates an understanding of dynamic data structures. It offers excellent flexibility, as it does not have a fixed size and allows for the addition of an unlimited number of data records.

However, the current implementation relies entirely on sequential search. When making reservations for animals or checking for duplicate names, the program must process every element in the collection one by one until a match is found. Since these operations have linear time complexity, performance degrades as the number of records increases.

Furthermore, because the application manages separate collections for dogs and monkeys, there is redundant logic for searching and report generation.

### Planned Enhancements
To improve efficiency, we plan to implement a hash map data structure to accelerate animal searches. This will reduce search times and improve scalability as the system grows.

Furthermore, we are optimizing the search functionality by adopting more efficient search methods and eliminating redundant logic.

These improvements demonstrate a deep understanding of algorithm selection and data structure optimization.

### Skills Demonstrated
This artifact demonstrates knowledge of ArrayLists, iteration, searching algorithms, object storage, and collection management within Java applications.

### Course Outcomes
These enhancements support the course outcome related to evaluating and selecting appropriate algorithms and data structures. They also demonstrate the ability to improve efficiency and performance based on software requirements

## Category Three: Databases

### Existing Functionality
The current Rescue-Track application does not use a database. All animal information is stored in memory using ArrayLists and is lost when the program terminates.

Data persistence is not currently available, meaning users must re-enter information each time the application is restarted.

### Code Analysis
While the current approach is sufficient to demonstrate basic application functions and object-oriented programming concepts, it does not reflect how information is stored and managed in real-world operating systems.

As there is no database, the application lacks features such as persistent storage, structured queries, data integrity checks, and long-term record management.

Furthermore, since all information is kept in memory during execution, the scalability of the current design is limited.

### Planned Enhancements
To address the limitations, I plan to integrate an SQLite database into the application.

Animal records will be stored in database tables rather than in temporary memory structures, ensuring that information persists across multiple application sessions.

CRUD operations, enabling users to create, read, update, and delete records will be integrated via database interactions.

Using parameterized queries will make sure secure database access and reduce the risk of SQL injection vulnerabilities.

These improvements will transform the application from a simple in-memory system into a more practical, data-driven application.

### Skills Demonstrated
This enhancement demonstrates database design, data persistence, SQL integration, CRUD operations, and secure database development practices

### Course Outcomes
These improvements support the course's learning objectives—namely, the development of computing solutions that integrate various technologies. They also demonstrate the ability to evaluate and implement database solutions with enhanced functionality and scalability.

## Conclusion
All in all, the Rescue-Track application provides a robust foundation for showcasing software engineering, data structures, and database components. This code review identifies the strengths and areas with potential improvements, like validation, scalability, and data persistence. The planned changes will better maintainability, efficiency, and security while showing the proficiency and skills developed throughout the CS program. 