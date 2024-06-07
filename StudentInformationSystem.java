// Composed By: Neil Jay Lacandazo, Freshman Information Systems Student 
// Date: June 5, 2024

import java.util.Scanner;
import java.util.ArrayList;

class Student {
  private String id;
  private String name;
  private String year;
  private String program;
  private String grade;
  
  Student(String id, String name, String year, String program, String grade) {
    this.id = id; 
    this.name = name;
    this.year = year;
    this.program = program;
    this.grade = grade;
  }
  
  public String getId() {
    return id;
  }
  
  public String getName() {
    return name;
  }
  
  public String getYear() {
    return year;
  }
  
  public String program() {
    return program;
  }
  
  public String getGrade() {
    return grade;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setYear(String year) {
    this.year = year;
  }
  
  public void setProgram(String program) {
    this.program = program;
  }
  
  public void setGrade(String grade) {
    this.grade = grade;
  }
  
  @Override
  public String toString() {
    return "ID: " + id + ", Name: " + name + ", Year: " + year + ", Program: " + program + ", GWA: " + grade;
  }
}

public class StudentInformationSystem {
  private static ArrayList<Student> studentList = new ArrayList<>();
  private static Scanner input = new Scanner(System.in);
  
  private static final String freshman = "Freshman";
  private static final String sophomore = "Sophomore";
  private static final String junior = "Junior";
  private static final String senior = "Senior";
  
  private static final String infoSystem = "BSIS";
  private static final String math = "BSMath";
  private static final String comm = "ABComm";
  
    public static void main(String[] args) {
      boolean exitProgram = false;
      
        while(!exitProgram) {
          int choice = displayMenu();
          System.out.println();
          
          switch(choice) {
            case 1: 
              addStudent();
              break;
            
            case 2: 
              removeStudent();
              break;
              
            case 3: 
              updateStudent();
              break;
            
            case 4: 
              viewAllStudents();
              break;
              
            case 5: 
              System.out.println("Exiting…");
              exitProgram = true;
              break;
              
            default:
              System.out.println("Invalid Input, Please Try Again.");
          }
        }
    }
    
    private static int displayMenu() {
      System.out.println("Student Information System");
      System.out.println("1. Add Student");
      System.out.println("2. Remove Student");
      System.out.println("3. Update Student");
      System.out.println("4. View All Students");
      System.out.println("5. Exit");
      System.out.print("Enter Choice: ");
      return input.nextInt();
    }
    
    private static void addStudent() {
      System.out.print("Student ID: ");
      String id = input.next();
      
      System.out.print("Student Name: ");
      String name = input.next();
      
      System.out.print("Student Year: ");
      String year = input.next();
      if(!isValidYear(year)) {
        System.out.print("Invalid Year Entered. Please Input Freshman, Sophomore, Junior, or Senior.\n");
        return;
      }
      
      System.out.print("Student Program: ");
      String program = input.next();
      if(!isValidProgram(program)) {
        System.out.print("Invalid Program Entered. Please Input BSIS, BSMath, or ABComm.\n");
        return;
      }
      
      System.out.print("Student Grade: ");
      String grade = input.next();
      
      Student student = new Student(id, name, year, program, grade);
      studentList.add(student);
      System.out.println("Student Added Successfully.\n");
    }
    
    private static void removeStudent() {
      System.out.print("Enter Student ID to update: ");
      String id = input.next();
      for(Student student : studentList) {
        if(student.getId().equals(id)) {
          studentList.remove(student);
          System.out.println("Student Removed Successfully.\n");
          return;
        }
      }
      System.out.println("Student ID Not Found.");
    }
    
    private static void updateStudent() {
      System.out.print("Enter Student ID to update: ");
      String id = input.next();
      for(Student student : studentList) {
        if(student.getId().equals(id)) {
          System.out.print("Enter new Student Name: ");
          String name = input.next();
          
          System.out.print("Enter new Student Year: ");
          String year = input.next();
          if(!isValidYear(year)) {
            System.out.print("Invalid Year Entered. Please Input Freshman, Sophomore, Junior, or Senior.\n");
            return;
          }
           
          System.out.print("Enter new Student Program: ");
          String program = input.next();
          if(!isValidProgram(program)) {
            System.out.print("Invalid Program Entered. Please Input BSIS, BSMath, or ABComm.\n");
          return;
          }
          
          System.out.print("Enter new Student Grade: ");
          String grade = input.next();
          
          student.setName(name);
          student.setYear(year);
          student.setProgram(program);
          student.setGrade(grade);
          System.out.println("Student Updated Successfully.\n");
          return;
        }
      }
      System.out.println("Student ID Not Found.");
    }
    
    private static void viewAllStudents() {
      for(Student student : studentList) {
        System.out.println(student);
      }
      System.out.println();
    }
    
    private static boolean isValidYear(String year) {
      return freshman.equals(year) || sophomore.equals(year) || junior.equals(year) || senior.equals(year);
    }
    
    private static boolean isValidProgram(String program) {
      return infoSystem.equals(program) || math.equals(program) || comm.equals(program);
    }
}
