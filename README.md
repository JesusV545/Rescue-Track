# Rescue-Track

Rescue-Track is a Java-based animal rescue management application developed to manage dogs and monkeys trained for rescue service. The program allows users to intake animals, reserve available animals, display animal records, search and filter records, and preserve information between sessions using an SQLite database.

This repository contains the enhanced version of an artifact originally created for the SNHU CS 210 course and later improved for the CS 499 Computer Science Capstone. The enhancements demonstrate skills in software design and engineering, algorithms and data structures, database development, input validation, and secure coding practices.

## Features

* Intake new dogs and monkeys
* Validate user input before processing or storing information
* Prevent duplicate animal records
* Reserve eligible animals by type and service country
* Find animals by name
* Filter animals by type, training status, reservation status, or service country
* Sort search results by name, age, or training status
* Store animal records in an SQLite database
* Load saved records when the application starts
* Preserve reservation changes between program sessions
* Display dogs, monkeys, or all available animals
* Handle invalid input and database errors without unexpectedly terminating

## Capstone Enhancements

### Software Design and Engineering

The original application placed most of its responsibilities inside the `Driver` class. The enhanced version separates those responsibilities into specialized components:

* `AnimalService` manages animal intake, searches, filtering, sorting, and reservations.
* `InputValidator` provides reusable validation methods.
* `RescueAnimal` defines shared animal data and behavior.
* `Dog` and `Monkey` provide animal-specific fields and validation.
* `Driver` manages the menu and user interaction.

This separation improves readability, maintainability, reuse, and testability. Defensive exception handling and stronger validation also reduce the likelihood of invalid data or unexpected program failures.

### Algorithms and Data Structures

The application now supports more efficient animal management and record retrieval. A `HashMap` provides direct name-based lookup, while collection-processing algorithms support filtering and sorting.

Users can:

* Search for an animal by name
* Filter records using multiple criteria
* Sort records by name, age, or training status
* View organized search results

These enhancements make the application more useful as the number of animal records increases.

### Database Integration

The final enhancement replaces session-only storage with SQLite persistence. The database layer is divided into two primary classes:

* `DatabaseManager` creates and manages the SQLite connection and database table.
* `AnimalRepository` performs database operations for animal records.

The application can save and retrieve both dog and monkey records, update reservation information, and restore saved data when restarted. Prepared statements are used for database operations, helping separate SQL commands from user-provided values.

## Technologies

* Java
* Object-oriented programming
* Java Collections Framework
* SQLite
* JDBC
* SQL
* Git and GitHub
* Visual Studio Code

## Project Structure

```text
Rescue-Track/
├── AnimalRepository.java
├── AnimalRepositoryTest.java
├── AnimalService.java
├── DatabaseConnectionTest.java
├── DatabaseManager.java
├── Dog.java
├── Driver.java
├── InputValidator.java
├── Monkey.java
├── RescueAnimal.java
├── code-review/
│   └── milestone-one-outline.md
├── lib/
│   └── sqlite-jdbc-3.53.2.1.jar
├── .gitignore
└── README.md
```

## Requirements

To compile and run the application, you need:

* Java Development Kit (JDK) 8 or later
* The included SQLite JDBC driver
* A terminal opened in the repository’s root directory

## Compile and Run

### Windows

From Git Bash, PowerShell, or Command Prompt, compile the application using:

```bash
javac -cp ".;lib/sqlite-jdbc-3.53.2.1.jar" *.java
```

Run the application using:

```bash
java -cp ".;lib/sqlite-jdbc-3.53.2.1.jar" Driver
```

### macOS or Linux

Compile the application using:

```bash
javac -cp ".:lib/sqlite-jdbc-3.53.2.1.jar" *.java
```

Run the application using:

```bash
java -cp ".:lib/sqlite-jdbc-3.53.2.1.jar" Driver
```

The program creates or connects to its SQLite database when it starts. Animal records added through the application remain available during later sessions.

## Testing

The repository includes test programs for the database connection and repository operations.

On Windows:

```bash
java -cp ".;lib/sqlite-jdbc-3.53.2.1.jar" DatabaseConnectionTest
java -cp ".;lib/sqlite-jdbc-3.53.2.1.jar" AnimalRepositoryTest
```

On macOS or Linux, replace the semicolon in the classpath with a colon.

## Secure Development Practices

The enhanced application incorporates several secure-development practices:

* Centralized validation of menu selections and animal data
* Duplicate-record prevention
* Defensive exception handling
* Prepared SQL statements
* Restricted valid values for fields such as gender and training status
* Separation of user-interface, business-logic, and database responsibilities
* Encapsulation of animal data through private fields and controlled methods

These practices improve data integrity, reliability, and maintainability.

## CS 499 Course Outcomes

This artifact demonstrates the ability to:

1. Apply collaborative and professional software-development practices through source control, documentation, and organized project enhancements.
2. Communicate technical ideas through code documentation, an ePortfolio, enhancement narratives, and a recorded code review.
3. Design and evaluate computing solutions using object-oriented design, reusable services, collections, searching, filtering, and sorting.
4. Apply established computer science practices using Java, algorithms, data structures, SQL, JDBC, and database persistence.
5. Develop a security-focused mindset through input validation, prepared statements, exception handling, encapsulation, and data-integrity controls.

## Project Background

The original Rescue-Track project was created as a console application for managing rescue animals. Its initial version relied on in-memory lists, included limited validation, and concentrated most application logic in one class.

For the CS 499 Capstone, the artifact was progressively enhanced in three areas:

1. Software design and engineering
2. Algorithms and data structures
3. Databases

Together, these enhancements transformed the original classroom project into a more organized, persistent, secure, and functional application.


