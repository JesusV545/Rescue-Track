# Rescue-Track

## Project Overview

Rescue-Track is a Java-based console application for managing dogs and monkeys that are being trained for rescue service. The application allows users to intake animals, reserve an available animal, and display animal records.

The original version was completed on March 16, 2025. This enhanced version was developed for the CS 499 Software Design and Engineering milestone.

## Application Features

- Intake new dogs and monkeys
- Prevent duplicate animal names
- Validate monkey species
- Validate gender and training status
- Validate ages, weights, physical measurements, and dates
- Reserve available in-service animals
- Display dogs, monkeys, or all available animals
- Recover safely from incorrect console input

## Enhanced Architecture

### `Driver.java`

Provides the console interface and controls menu navigation. It delegates validation and animal-management responsibilities to separate classes.

### `AnimalService.java`

Manages the dog and monkey collections. It also handles duplicate detection, availability checks, and reservation rules.

### `InputValidator.java`

Provides reusable validation methods for required text, integers, decimal numbers, dates, and controlled options.

### `RescueAnimal.java`

Defines the shared fields and behavior for rescue animals. Its constructor validates shared information and prevents invalid model states.

### `Dog.java`

Extends `RescueAnimal` and adds breed information.

### `Monkey.java`

Extends `RescueAnimal` and adds species and physical measurement information.

## Software Design Improvements

The enhanced version includes the following improvements:

- Separation of user-interface and business-logic responsibilities
- Reusable input-validation methods
- Appropriate data types for ages, weights, measurements, and dates
- Constructor-level model validation
- Defensive exception handling
- Read-only access to stored collections
- Reduced duplicate logic
- Improved naming, comments, and documentation

These changes make the application more maintainable, reliable, secure, and easier to extend.

## Requirements

- Java 8 or newer
- A terminal, command prompt, or Java-compatible IDE

## Compile and Run

From the project directory, run:

```bash
javac *.java
java Driver
```

## Menu Options

```text
[1] Intake a new dog
[2] Intake a new monkey
[3] Reserve an animal
[4] Display all dogs
[5] Display all monkeys
[6] Display available in-service animals
[q] Quit
```

## Input Requirements

- Dates must use the `YYYY-MM-DD` format.
- Ages must be whole numbers that are zero or greater.
- Weights and measurements must be greater than zero.
- Monkey species must be one of the supported species shown by the application.
- Gender and training status must match one of the displayed options.

## Course Outcome Alignment

This enhancement supports the following CS 499 outcomes:

- **Outcome Two:** Professional-quality technical communication through organized code, comments, and documentation.
- **Outcome Four:** Application of software engineering techniques and tools to create a maintainable solution.
- **Outcome Five:** Defensive programming, input validation, and prevention of invalid application states.