class Student {
    private String id;
    private String name;
    private Year year;
    private Program program;
    private double grade;
    
    public Student(String id, String name, Year year, Program program, double grade) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.program = program;
        this.grade = grade;
    }

    // Getter and setter methods
    public String getId() {
        return id; 
    }
    
    public String getName() {
        return name; 
    }
    
    public Year getYear() { 
      return year; 
    }
    
    public Program getProgram() { 
      return program; 
    }
    
    public double getGrade() { 
      return grade;
    }

    public void setName(String name) { 
      this.name = name; 
    }
    public void setYear(Year year) { 
      this.year = year; 
    }
    
    public void setProgram(Program program) { 
      this.program = program; 
    }
    
    public void setGrade(double grade) { 
      this.grade = grade; 
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Year: " + year + ", Program: " + program + ", GWA: " + grade;
    }

    // Enum for year
    public enum Year {
        FRESHMAN, SOPHOMORE, JUNIOR, SENIOR
    }

    // Enum for program
    public enum Program {
        BS_INFORMATION_SYSTEMS, BS_MATH, AB_COMMUNICATION
    }
      }
