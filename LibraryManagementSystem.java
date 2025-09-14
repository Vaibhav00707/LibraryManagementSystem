import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Base class for all library items
abstract class LibraryItem implements Serializable {
    protected String itemNumber;
    protected String name;
    
    public LibraryItem(String itemNumber, String name) {
        this.itemNumber = itemNumber;
        this.name = name;
    }
    
    public abstract void display();
    public abstract void modify();
    
    public String getItemNumber() {
        return itemNumber;
    }
}

// Book class extending LibraryItem
class Book extends LibraryItem {
    private String authorName;
    
    public Book(String bookNumber, String bookName, String authorName) {
        super(bookNumber, bookName);
        this.authorName = authorName;
    }
    
    public void createBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nNEW BOOK ENTRY...");
        System.out.println("\nEnter the Book No. here (max 5 chars): ");
        itemNumber = scanner.nextLine();
        if (itemNumber.length() > 5) {
            itemNumber = itemNumber.substring(0, 5);
        }
        
        System.out.println("Enter the name of book here (max 30 chars): ");
        name = scanner.nextLine();
        if (name.length() > 30) {
            name = name.substring(0, 30);
        }
        
        System.out.println("Enter the author of " + name + " here (max 20 chars): ");
        authorName = scanner.nextLine();
        if (authorName.length() > 20) {
            authorName = authorName.substring(0, 20);
        }
        
        System.out.println("\n\n\nBook is successfully created.....");
    }
    
    @Override
    public void display() {
        System.out.println("\nBook No.: " + itemNumber);
        System.out.println("Book Name: " + name);
        System.out.println("Book Author Name: " + authorName);
    }
    
    @Override
    public void modify() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nBook Number: " + itemNumber);
        
        int ch = 0;
        do {
            System.out.println("1. Modify Book Name.");
            System.out.println("2. Modify Author Name.");
            System.out.println("3. Exit from Modification.");
            System.out.print("Enter your choice here(1-3): ");
            
            try {
                ch = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            
            switch (ch) {
                case 1:
                    System.out.print("\nEnter the Book Name: ");
                    name = scanner.nextLine();
                    if (name.length() > 30) {
                        name = name.substring(0, 30);
                    }
                    break;
                case 2:
                    System.out.print("\nEnter the author name: ");
                    authorName = scanner.nextLine();
                    if (authorName.length() > 20) {
                        authorName = authorName.substring(0, 20);
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Value");
            }
        } while (ch != 3);
    }
    
    public void report() {
        System.out.printf("%-6s %-30s %-30s\n", itemNumber, name, authorName);
    }
}

// Student class
class Student implements Serializable {
    private String admissionNo;
    private String name;
    private String studentBookNumber;
    private int token;
    
    public Student(String admissionNo, String name) {
        this.admissionNo = admissionNo;
        this.name = name;
        this.token = 0;
        this.studentBookNumber = "";
    }
    
    public void createStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nNEW STUDENT ENTRY...");
        System.out.println("\nEnter the admission no. of student here (max 12 chars): ");
        admissionNo = scanner.nextLine();
        if (admissionNo.length() > 12) {
            admissionNo = admissionNo.substring(0, 12);
        }
        
        System.out.println("\nEnter the student name here (max 20 chars): ");
        name = scanner.nextLine();
        if (name.length() > 20) {
            name = name.substring(0, 20);
        }
        
        token = 0;
        studentBookNumber = "";
        System.out.println("\n\nStudent Record Created Successfully....");
    }
    
    public void display() {
        System.out.println("\nAdmission Number: " + admissionNo);
        System.out.println("Student Name: " + name);
        System.out.println("Number of Books issued: " + token);
        if (token == 1) {
            System.out.println("Book Number: " + studentBookNumber);
        }
    }
    
    public void modify() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAdmission No.: " + admissionNo);
        System.out.print("Modify Student Name: ");
        name = scanner.nextLine();
        if (name.length() > 20) {
            name = name.substring(0, 20);
        }
    }
    
    public String getAdmissionNo() {
        return admissionNo;
    }
    
    public String getStudentBookNumber() {
        return studentBookNumber;
    }
    
    public int getToken() {
        return token;
    }
    
    public void createToken(String bookNumber) {
        token = 1;
        studentBookNumber = bookNumber;
    }
    
    public void resetToken() {
        token = 0;
        studentBookNumber = "";
    }
    
    public void report() {
        System.out.printf("%-12s %-20s %-20d\n", admissionNo, name, token);
    }
}

// File management class
class FileManager {
    // Save books to file
    public static void saveBooks(ArrayList<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.dat"))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }
    
    // Load books from file
    @SuppressWarnings("unchecked")
    public static ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<>();
        File file = new File("books.dat");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.dat"))) {
                books = (ArrayList<Book>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading books: " + e.getMessage());
            }
        }
        return books;
    }
    
    // Save students to file
    public static void saveStudents(ArrayList<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
    
    // Load students from file
    @SuppressWarnings("unchecked")
    public static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File("students.dat");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"))) {
                students = (ArrayList<Student>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading students: " + e.getMessage());
            }
        }
        return students;
    }
}

// Main library management class
public class LibraryManagementSystem {
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private Scanner scanner;
    
    public LibraryManagementSystem() {
        books = FileManager.loadBooks();
        students = FileManager.loadStudents();
        scanner = new Scanner(System.in);
    }
    
    public void writeBook() {
        char ch;
        do {
            Book book = new Book("", "", "");
            book.createBook();
            books.add(book);
            FileManager.saveBooks(books);
            
            System.out.print("\n\nDo you want to add more data (y/n): ");
            ch = scanner.nextLine().charAt(0);
        } while (ch == 'y' || ch == 'Y');
    }
    
    public void writeStudent() {
        char ch;
        do {
            Student student = new Student("", "");
            student.createStudent();
            students.add(student);
            FileManager.saveStudents(students);
            
            System.out.print("\n\nDo you want to add more data (y/n): ");
            ch = scanner.nextLine().charAt(0);
        } while (ch == 'y' || ch == 'Y');
    }
    
    public void displayBook(String bookNumber) {
        System.out.println("\nBOOK DETAILS");
        boolean found = false;
        
        for (Book book : books) {
            if (book.getItemNumber().equals(bookNumber)) {
                book.display();
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("\n\nBook does not exist...");
        }
    }
    
    public void displayStudent(String admissionNo) {
        System.out.println("\nSTUDENT DETAILS");
        boolean found = false;
        
        for (Student student : students) {
            if (student.getAdmissionNo().equals(admissionNo)) {
                student.display();
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("\n\nStudent does not exist...");
        }
    }
    
    public void modifyBook() {
        System.out.println("\n\nMODIFY BOOK RECORD...");
        System.out.print("\n\nEnter the book Number here: ");
        String bookNumber = scanner.nextLine();
        
        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getItemNumber().equals(bookNumber)) {
                book.display();
                System.out.println("\nEnter the new details of book: ");
                book.modify();
                books.set(i, book);
                FileManager.saveBooks(books);
                System.out.println("\n\n\tRecord Updated Successfully..");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("\n\nBook not found...");
        }
        
        scanner.nextLine(); // Wait for user input
    }
    
    public void modifyStudent() {
        System.out.println("\n\nMODIFY STUDENT RECORD...");
        System.out.print("\n\nEnter the student Admission Number here: ");
        String admissionNo = scanner.nextLine();
        
        boolean found = false;
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getAdmissionNo().equals(admissionNo)) {
                student.display();
                System.out.println("\nEnter the new details of student: ");
                student.modify();
                students.set(i, student);
                FileManager.saveStudents(students);
                System.out.println("\n\n\tRecord Updated Successfully..");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("\n\nRecord not found...");
        }
        
        scanner.nextLine(); // Wait for user input
    }
    
    public void deleteStudent() {
        System.out.println("\n\n\n\tDELETE STUDENT DATA ");
        System.out.print("\n\nEnter the admission no. of student here: ");
        String admissionNo = scanner.nextLine();
        
        boolean found = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getAdmissionNo().equals(admissionNo)) {
                students.remove(i);
                FileManager.saveStudents(students);
                found = true;
                break;
            }
        }
        
        if (found) {
            System.out.println("\n\n\tRecord Deleted Successfully..");
        } else {
            System.out.println("\n\n\tRecord not Found ..");
        }
        
        scanner.nextLine(); // Wait for user input
    }
    
    public void deleteBook() {
        System.out.println("\n\n\n\tDELETE BOOK DATA ");
        System.out.print("\n\nEnter the book no. of book here: ");
        String bookNumber = scanner.nextLine();
        
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getItemNumber().equals(bookNumber)) {
                books.remove(i);
                FileManager.saveBooks(books);
                break;
            }
        }
        
        System.out.println("\n\n\tRecord Deleted Successfully..");
        scanner.nextLine(); // Wait for user input
    }
    
    public void displayAllBooks() {
        System.out.println("\n\n\t\tBook List\n\n");
        System.out.println("==================================================================================");
        System.out.printf("%-6s %-30s %-30s\n", "BookNo", "Book Name", "Author");
        System.out.println("==================================================================================");
        
        for (Book book : books) {
            book.report();
        }
        
        scanner.nextLine(); // Wait for user input
    }
    
    public void displayAllStudents() {
        System.out.println("\n\n\t\tStudent List\n\n");
        System.out.println("==================================================================");
        System.out.printf("%-12s %-20s %-20s\n", "AdmissionNo", "Student Name", "Book Issued");
        System.out.println("==================================================================");
        
        for (Student student : students) {
            student.report();
        }
        
        scanner.nextLine(); // Wait for user input
    }
    
    public void bookIssue() {
        System.out.println("\n\n BOOK ISSUE...");
        System.out.print("\n\n\tEnter the student's admission number here: ");
        String admissionNo = scanner.nextLine();
        
        boolean studentFound = false;
        boolean bookFound = false;
        
        for (Student student : students) {
            if (student.getAdmissionNo().equals(admissionNo)) {
                studentFound = true;
                if (student.getToken() == 0) {
                    System.out.print("\n\n\tEnter the book number here: ");
                    String bookNumber = scanner.nextLine();
                    
                    for (Book book : books) {
                        if (book.getItemNumber().equals(bookNumber)) {
                            bookFound = true;
                            book.display();
                            student.createToken(bookNumber);
                            FileManager.saveStudents(students);
                            System.out.println("\n\n\t Book Issued Successfully..\n\n");
                            break;
                        }
                    }
                    
                    if (!bookFound) {
                        System.out.println("Book does not exist");
                    }
                } else {
                    System.out.println("You have not returned the last book");
                }
                break;
            }
        }
        
        if (!studentFound) {
            System.out.println("Student record does not exist...");
        }
        
        scanner.nextLine(); // Wait for user input
    }
    
    public void bookDeposit() {
        System.out.println("\n\n BOOK DEPOSIT...");
        System.out.print("\n\n\tEnter the student's admission number here: ");
        String admissionNo = scanner.nextLine();
        
        boolean studentFound = false;
        boolean bookFound = false;
        
        for (Student student : students) {
            if (student.getAdmissionNo().equals(admissionNo)) {
                studentFound = true;
                if (student.getToken() == 1) {
                    String bookNumber = student.getStudentBookNumber();
                    
                    for (Book book : books) {
                        if (book.getItemNumber().equals(bookNumber)) {
                            bookFound = true;
                            book.display();
                            System.out.print("\n\n Book deposited in no. of days: ");
                            int days = Integer.parseInt(scanner.nextLine());
                            
                            if (days > 15) {
                                int fine = (days - 15) * 1;
                                System.out.println("\n\n Fine is: " + fine);
                            }
                            
                            student.resetToken();
                            FileManager.saveStudents(students);
                            System.out.println("\n\n\t Book deposited successfully..");
                            break;
                        }
                    }
                    
                    if (!bookFound) {
                        System.out.println("Book does not exist");
                    }
                } else {
                    System.out.println("No book is issued.. please check!!");
                }
                break;
            }
        }
        
        if (!studentFound) {
            System.out.println("Student record does not exist...");
        }
        
        scanner.nextLine(); // Wait for user input
    }
    
    public void intro() {
        System.out.println("\n\n\n\t Library Management Project");
        System.out.println("\n\n\n\t MAHAKAL INSTITUTE OF TECHNOLOGY UJJAIN");
        scanner.nextLine(); // Wait for user input
    }
    
    public void adminMenu() {
        int ch2;
        do {
            System.out.println("\n\n\n\tADMINISTRATOR MENU");
            System.out.println("\n\n\t1. Create Student Record");
            System.out.println("\n\n\t2. Display all Student Record");
            System.out.println("\n\n\t3. Display Specific Student Record");
            System.out.println("\n\n\t4. Delete Student Record");
            System.out.println("\n\n\t5. Modify Student Record");
            System.out.println("\n\n\t6. Create Book Record");
            System.out.println("\n\n\t7. Display all Book Record");
            System.out.println("\n\n\t8. Display Specific Book Record");
            System.out.println("\n\n\t9. Delete Book Record");
            System.out.println("\n\n\t10. Modify Book Record");
            System.out.println("\n\n\t11. Back to Main Menu");
            System.out.print("\n\n\tPlease Enter Your Choice (1-11): ");
            
            try {
                ch2 = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                ch2 = 0;
                continue;
            }
            
            switch (ch2) {
                case 1:
                    writeStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    System.out.print("\n\n\tPlease Enter the Admission Number: ");
                    String admissionNo = scanner.nextLine();
                    displayStudent(admissionNo);
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    modifyStudent();
                    break;
                case 6:
                    writeBook();
                    break;
                case 7:
                    displayAllBooks();
                    break;
                case 8:
                    System.out.print("\n\n\tPlease Enter the Book No.: ");
                    String bookNo = scanner.nextLine();
                    displayBook(bookNo);
                    break;
                case 9:
                    deleteBook();
                    break;
                case 10:
                    modifyBook();
                    break;
                case 11:
                    return;
                default:
                    System.out.println("Invalid choice!"); // Replaced \a with error message
            }
        } while (true);
    }
    
    public void run() {
        intro();
        char ch;
        do {
            System.out.println("\n\n\n\tMAIN MENU");
            System.out.println("\n\n\t1. Book Issue");
            System.out.println("\n\n\t2. Book Deposit");
            System.out.println("\n\n\t3. Administrator Menu");
            System.out.println("\n\n\t4. Exit");
            System.out.print("\n\n\tPlease Select Your Option (1-4): ");
            
            ch = scanner.nextLine().charAt(0);
            
            switch (ch) {
                case '1':
                    bookIssue();
                    break;
                case '2':
                    bookDeposit();
                    break;
                case '3':
                    adminMenu();
                    break;
                case '4':
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!"); // Replaced \a with error message
            }
        } while (ch != '4');
    }
    
    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();
        system.run();
    }
}