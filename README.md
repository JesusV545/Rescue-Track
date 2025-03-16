# Rescue-Track 

## Overview  
The **Rescue** is a **Java-based inventory management application** designed to track rescued animals, including **dogs and monkeys**. This system allows users to **add, update, and reserve animals**, ensuring efficient record-keeping and easy retrieval. It utilizes **object-oriented programming (OOP) principles** to maintain a modular and scalable design.  

## Features  
- **Animal Intake & Management:** Add new **dogs and monkeys** with detailed attributes.  
- **Reservation System:** Reserve available animals for adoption or service.  
- **Data Validation:** Ensures correct species, training status, and location entries.  
- **OOP Principles:** Implements **inheritance, encapsulation, and polymorphism** for maintainability.  
- **Menu-Driven Interface:** Provides an interactive **CLI-based system** for user interaction.  

## Files and Their Purpose  

### `Driver.java`  
- Serves as the **entry point** of the program.  
- Manages **user interaction** through a **menu-driven interface**.  
- Handles **animal intake, reservations, and data retrieval**.  

### `RescueAnimal.java`  
- Defines the **base class** for all rescued animals.  
- Stores attributes such as **name, age, weight, training status, and location**.  
- Provides **getter and setter methods** for data access.  

### `Dog.java`  
- Extends `RescueAnimal` to create a **dog-specific subclass**.  
- Includes an additional attribute: `breed`.  
- Implements methods for setting and retrieving breed information.  

### `Monkey.java`  
- Extends `RescueAnimal` to manage **monkey-specific attributes**.  
- Introduces **tail length, body length, height, and species** attributes.  
- Ensures species validation with a predefined set of monkey types.  

## Concepts Used  

### **Fundamental Concepts**  
1. **Variables & Data Types:**  
   - `String` for names, species, and status attributes.  
   - `double` for physical measurements (e.g., tail length, height).  
   - `boolean` for tracking reservations.  

2. **Input/Output Handling:**  
   - `Scanner` for reading user input.  
   - `System.out.println` for displaying information.  

3. **Conditional Statements & Loops:**  
   - `if` statements for **input validation** and **error handling**.  
   - `while` and `do-while` loops for **continuous user interaction**.  

4. **Methods & Encapsulation:**  
   - Uses **getter and setter methods** for controlled data access.  
   - Encapsulates behavior within **individual class files**.  

### **Intermediate Concepts**  
5. **Object-Oriented Programming (OOP):**  
   - **Encapsulation:** Keeps data private and exposes only necessary methods.  
   - **Inheritance:** `Dog` and `Monkey` classes inherit from `RescueAnimal`.  
   - **Polymorphism:** Common methods work across different animal types.  

6. **Data Structures:**  
   - Utilizes **ArrayLists** to store and manage animal records dynamically.  

### **Advanced Concepts**  
7. **Data Validation & Exception Handling:**  
   - Ensures valid input for species and numerical values.  
   - Prevents duplicate entries for animals.  

8. **Code Maintainability & Scalability:**  
   - Modular design allows **easy expansion** for new animal types.  
   - Well-structured **method definitions** improve **code readability**.  

## Reflection  

### **Project Summary**  
This project provides an organized system for **tracking rescued animals**, making it easier to manage **animal records, reservations, and availability**. By applying **OOP principles**, the system is **modular, extendable, and easy to maintain**.  

### **Strengths in Implementation**  
- **Strong data validation** ensures reliable user input.  
- **Encapsulated class structure** improves reusability and maintainability.  
- **User-friendly CLI design** simplifies interaction.  

### **Areas for Improvement**  
- **Database Integration:** Storing data in a database instead of in-memory lists.  
- **Graphical Interface:** Adding a GUI for enhanced usability.  
- **Logging System:** Implementing logs for tracking reservations and updates.  

### **Challenges Faced & Solutions**  
- **Handling invalid input entries** was a challenge, but implementing structured validation methods helped resolve it.  
- **Ensuring scalability** for future animal types required an **extendable class hierarchy**.  

### **Transferable Skills**  
- Experience in **OOP design and implementation**.  
- Understanding of **data structures for dynamic storage**.  
- Knowledge of **input validation and error handling**.  

## How to Compile and Run  
Ensure you have Java installed. Then, follow these steps:  

1. **Clone the Repository:**  
   ```bash
   git clone https://github.com/yourusername/Rescue-Track.git
   cd RescueTrack

   javac *.java

   java Driver


