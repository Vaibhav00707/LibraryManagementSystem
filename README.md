# 📚 Library Management System

A comprehensive **Java-based Library Management System** that manages books and student records with features like file persistence, inheritance, and polymorphism.

---

## 🚀 Features

### 📚 Core Functionality
- **Book Management**: Add, view, modify, and delete book records  
- **Student Management**: Add, view, modify, and delete student records  
- **Book Issuing**: Issue books to students with validation  
- **Book Return**: Return books with fine calculation for overdue items  
- **Data Persistence**: Automatic saving and loading of data using serialization  

### 🎯 Object-Oriented Programming Features
- **Inheritance**: `Book` class extends `LibraryItem` abstract class  
- **Polymorphism**: Dynamic method dispatch for `display()` and `modify()` methods  
- **Abstraction**: `LibraryItem` abstract class with common properties and methods  
- **Encapsulation**: Proper data hiding with private fields and public methods  

### 💾 File Management
- **Serialization**: Uses Java Object Serialization for data persistence  
- **Automatic Saving**: Data is automatically saved after modifications  
- **Automatic Loading**: Data is loaded when the program starts  
- **File Handling**: Separate files for books (`books.dat`) and students (`students.dat`)  

---

## 📦 Class Structure

### Main Classes
- **LibraryItem (Abstract Class)**  
  - Base class for all library items  
  - Common properties: `itemNumber`, `name`  
  - Abstract methods: `display()`, `modify()`  

- **Book (Extends LibraryItem)**  
  - Represents book entities  
  - Additional property: `authorName`  
  - Methods: `createBook()`, `report()`, `display()`, `modify()`  

- **Student**  
  - Represents student entities  
  - Properties: `admissionNo`, `name`, `studentBookNumber`, `token`  
  - Methods: `createStudent()`, `display()`, `modify()`, `report()`  

- **FileManager**  
  - Handles file operations  
  - Methods for saving/loading books and students  

- **LibraryManagementSystem**  
  - Main application class  
  - Contains menu systems and user interaction  

---

## ⚙️ How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher  
- Any Java-compatible IDE or terminal  

### Compilation and Execution
```bash
javac LibraryManagementSystem.java
java LibraryManagementSystem

📋 Menu Options
Main Menu

Book Issue: Issue a book to a student

Book Deposit: Return a book and calculate fines

Administrator Menu: Access admin functions

Exit: Exit the program

Administrator Menu

Create Student Record

Display All Student Records

Display Specific Student Record

Delete Student Record

Modify Student Record

Create Book Record

Display All Book Records

Display Specific Book Record

Delete Book Record

Modify Book Record

Back to Main Menu

📊 Data Structure
Book Data

Book Number: Max 5 characters

Book Name: Max 30 characters

Author Name: Max 20 characters

Student Data

Admission Number: Max 12 characters

Student Name: Max 20 characters

Token: Tracks issued books (0 or 1)

Book Number: Stores issued book number

⚠️ Validation Features

Input Length Validation: Ensures data doesn't exceed field limits

Duplicate Prevention: Prevents multiple book issues to same student

Existence Checks: Validates book/student existence before operations

Fine Calculation: Automatic fine calculation for overdue books (>15 days)

💡 Technical Details
File Format

Uses Java Object Serialization for data storage

Binary format for efficient storage

Automatic version handling

Error Handling

Comprehensive exception handling for file operations

Input validation for user entries

Graceful error recovery

Memory Management

Uses ArrayList for efficient data storage

Automatic garbage collection

Efficient searching algorithms

🔧 Customization

You can modify:

Field Lengths in classes

Fine Calculation logic in bookDeposit() method

File Locations in FileManager class

UI Enhancements for better user experience

📝 Usage Examples
Adding a New Book

Go to Administrator Menu → Create Book Record

Enter:

Book Number: B001

Book Name: Java Programming

Author Name: James Gosling

Issuing a Book

Select Book Issue from Main Menu

Enter:

Student Admission Number

Book Number

System validates and issues the book

🛠️ Troubleshooting
Common Issues

File Permission Errors: Ensure write permissions in execution directory

Serialization Errors: Delete corrupted .dat files to start fresh

Input Errors: Follow character limits for inputs

Data Recovery

Back up .dat files regularly

Program auto-creates files if missing

📄 License

This project is for educational purposes. Feel free to modify and use it as needed.

🤝 Contributing

Fork the repository

Create a feature branch

Make your changes

Submit a pull request

📞 Support

Check Java documentation for file handling and serialization

Review OOP concepts for better understanding


---

If you want, I can also **generate the actual `README.md` file** and give you a direct download link.  
Do you want me to create that file for you?


