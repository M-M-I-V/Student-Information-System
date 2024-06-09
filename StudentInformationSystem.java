// Composed by: Neil Jay Lacandazo, Freshman Information Systems Student 
// Date: June 5, 2024
// Last updated: June 9, 2024

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class StudentInformationSystem {
    private static final ArrayList<Student> studentList = new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(StudentInformationSystem.class.getName());
    private static final String FILE_NAME = "student_list.csv";
    
    public static void main(String[] args) {
        loadFromFile();
        boolean exitProgram = false;
        
        while (!exitProgram) {
            int choice = displayMenu();
            System.out.println();
            
            switch (choice) {
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
                  searchStudent();
                break;
                
                case 6: 
                  logger.info("Exiting..."); 
                  saveToFile();
                  exitProgram = true; 
                break;
                
                default: 
                  logger.warning("Invalid input, please try again.");
            }
        }
        input.close();
    }

    // Method to display program menu
    private static int displayMenu() {
        System.out.println("Student Information System");
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Update Student");
        System.out.println("4. View All Students");
        System.out.println("5. Search Student");
        System.out.println("6. Exit");
        System.out.print("Enter Choice: ");
        while (!input.hasNextInt()) {
            System.out.print("Invalid input, please enter a number: ");
            input.next();
        }
        return input.nextInt();
    }
    
    // Method for adding students
    private static void addStudent() {
        input.nextLine();  // Consume newline left-over
        System.out.print("Student ID: ");
        String id = input.nextLine();
        
        System.out.print("Student Name: ");
        String name = input.nextLine();
        
        Student.Year year = promptForYear();
        if (year == null) return;
        
        Student.Program program = promptForProgram();
        if (program == null) return;
        
        double grade = promptForGrade();
        if (grade == -1) return;
        
        Student student = new Student(id, name, year, program, grade);
        studentList.add(student);
        logger.info("Student added successfully.");
        System.out.println();
    }
    
    // Method for removing students
    private static void removeStudent() {
        input.nextLine();  // Consume newline left-over
        System.out.print("Enter Student ID to remove: ");
        String id = input.nextLine();
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId().equals(id)) {
                iterator.remove();
                logger.info("Student removed successfully.");
                System.out.println();
                return;
            }
        }
        logger.warning("Student ID not found.");
        System.out.println();
    }
    
    // Method for updating students
    private static void updateStudent() {
        input.nextLine();  // Consume newline left-over
        System.out.print("Enter Student ID to update: ");
        String id = input.nextLine();
        for(Student student : studentList) {
            if(student.getId().equals(id)) {
                System.out.print("Enter new Student Name: ");
                String name = input.nextLine();
                
                Student.Year year = promptForYear();
                if (year == null) return;
                
                Student.Program program = promptForProgram();
                if (program == null) return;
                
                double grade = promptForGrade();
                if (grade == -1) return;
                
                student.setName(name);
                student.setYear(year);
                student.setProgram(program);
                student.setGrade(grade);
                logger.info("Student updated successfully.");
                System.out.println();
                return;
            }
        }
        logger.warning("Student ID not found.");
        System.out.println();
    }
    
    // Method for viewing students
    private static void viewAllStudents() {
        for (Student student : studentList) {
            System.out.println(student);
        }
        System.out.println();
    }
    
    // Method for searching students
    private static void searchStudent() {
      input.nextLine();
      System.out.print("Enter Student Name to search (e.g., Juan Dela Cruz): ");
      String name = input.nextLine();
      for(Student student : studentList) {
        if(student.getName().equals(name)) {
          logger.info("Student found!\n" + student);
          System.out.println();
          return;
        }
      }
      logger.warning("Student name not found.");
      System.out.println();
    }
    
    // Method to save the data to student_list.csv
    private static void saveToFile() {
      try(FileWriter writer = new FileWriter(FILE_NAME)) {
        for(Student student : studentList) {
          writer.write(student.getId() + ", " + student.getName() + ", " + student.getYear() + ", " + student.getProgram() + ", " + student.getGrade() + "\n");
        } 
        logger.info("Data saved to " + FILE_NAME);
      } catch(IOException e) {
        logger.warning("An error occurred while saving data");
        e.printStackTrace();
      }
    }
    
    // Method to load the data from student_list.csv
    private static void loadFromFile() {
      try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
        String line; 
        while((line = reader.readLine()) != null) {
          String[] parts = line.split(", ");
          if(parts.length == 5) {
            String id = parts[0];
            String name = parts[1];
            Student.Year year = Student.Year.valueOf(parts[2]);
            Student.Program program = Student.Program.valueOf(parts[3]);
            double grade = Double.parseDouble(parts[4]);
            studentList.add(new Student(id, name, year, program, grade));
          }
        }
        logger.info("Data loaded from " + FILE_NAME);
      } catch(IOException e) {
        logger.warning("An error occured while loading data");
        e.printStackTrace();
      }
    }

    // Helper method for prompting user to input student year
    private static Student.Year promptForYear() {
        System.out.print("Student Year (FRESHMAN, SOPHOMORE, JUNIOR, SENIOR): ");
        try {
            return Student.Year.valueOf(input.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warning("Invalid year entered. Please input FRESHMAN, SOPHOMORE, JUNIOR, or SENIOR.");
            return null;
        }
    }
    
    // Helper method for prompting user to input student program
    private static Student.Program promptForProgram() {
        System.out.print("Student Program (BS_INFORMATION_SYSTEMS, BS_MATH, AB_COMMUNICATION): ");
        try {
            return Student.Program.valueOf(input.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warning("Invalid program entered. Please input BS_INFORMATION_SYSTEMS, BS_MATH, or AB_COMMUNICATION.");
            return null;
        }
    }
    
    // Helper method for prompting user to input student grade
    private static double promptForGrade() {
        System.out.print("Student Grade (1.00 to 5.00): ");
        try {
            double grade = Double.parseDouble(input.nextLine());
            if (grade >= 1.00 && grade <= 5.00) {
                return grade;
            } else {
                logger.warning("Invalid grade entered. Please input between 1.00 to 5.00.");
                return -1;
            }
        } catch (NumberFormatException e) {
            logger.warning("Invalid grade entered. Please input a valid number between 1.00 to 5.00.");
            return -1;
        }
    }
  }
