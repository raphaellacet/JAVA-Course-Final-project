/*
COP 3330 - Object Oriented Programming
Project 3
Raphael Fidelis Lacet 
*/

//package projects;

import java.util.regex.Pattern;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project3 { // University Personal Management Program

	public static void main(String[] args) {

		Project3 m = new Project3();

		m.menu(); // initialize menu

	}

	private boolean validateID(String userID) throws idException {
		
		String pattern = "^[A-z]{2}[0-9]{4}$";

        boolean matches = Pattern.matches(pattern, userID);

        if (!matches) {

            throw new idException("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");

        } else {

            return true;
        }	
}

	// UpperCase First Letter of Each Word
	public static String upperCaseFirstLetter(String string) {
		String word[] = string.split("\\s");
		String upperCaseFirstLetter = "";

		for (String w : word) {
			String firstLetter = w.substring(0, 1);
			String remainingLetters = w.substring(1);
			upperCaseFirstLetter += firstLetter.toUpperCase() + remainingLetters + " ";
		}
		return upperCaseFirstLetter.trim();
	}

	// Student Method
	public void student(Person[] arrayPerson) {

		String studentFullName, studentId;
		double gpa;
		int numCreditHours;

		Scanner scnr = new Scanner(System.in);

		// Student
		try {
		System.out.println("\nEnter student info:");
		System.out.print("\tName of Student: ");
		studentFullName = upperCaseFirstLetter(scnr.nextLine().toLowerCase());
		
		System.out.print("\tID: ");
		studentId = scnr.nextLine();
		validateID(studentId);
		
		System.out.print("\tGpa: ");
		gpa = scnr.nextDouble();

		System.out.print("\tCredit hours: ");
		numCreditHours = scnr.nextInt();
		System.out.println("\nThanks!\n");
		System.out.println("Student added!");

		Student student = new Student(gpa, numCreditHours);

		student.setFullName(studentFullName);
		student.setId(studentId);

		saveOnArray(arrayPerson, student);
		} catch(InputMismatchException expt) {
			System.out.println("Invalid gpa OR credit hours format. Must be numerical");
		} catch(idException expt) {
			System.out.println(expt.getMessage());
		} catch(Throwable expt) {
			System.out.println("an exception occurred");
		}
	}

	public static void saveOnArray(Person[] arrayPerson, Person object) {

		for (int i = 0; i < 100; i++) {
			if (arrayPerson[i] == null) {
				arrayPerson[i] = object;
				return;
			}
		}
		System.out.println("Array is Full!");
	}

	// Faculty Method
	public void faculty(Person[] arrayPerson) {

		String facultyFullName, facultyId, facultyDepartment, rank;

		Scanner scnr = new Scanner(System.in);
		// Faculty info
		try {
		System.out.println("\nEnter faculty info:\n");
		System.out.print("\tName of the faculty: ");
		facultyFullName = upperCaseFirstLetter(scnr.nextLine().toLowerCase());

		System.out.print("\tID: ");
		facultyId = scnr.nextLine();
		validateID(facultyId);

		// Faculty Rank
		do {
			System.out.print("\tRank: ");
			rank = upperCaseFirstLetter(scnr.nextLine().toLowerCase());

			if (!rank.equals("Professor") && !rank.equals("Adjunt")) {
				System.out.println("\t\t " + rank + " is invalid \n");
			}

		} while (!rank.equals("Professor") && !rank.equals("Adjunt"));

		// Faculty Department
		do {
			System.out.print("\tDepartment: ");
			facultyDepartment = upperCaseFirstLetter(scnr.nextLine().toLowerCase());

			if (!facultyDepartment.equals("Mathematics") && !facultyDepartment.equals("Engineering")
					&& !facultyDepartment.equals("English")) {
				System.out.println("\t\t " + facultyDepartment + " is invalid \n");
			}

		} while (!facultyDepartment.equals("Mathematics") && !facultyDepartment.equals("Engineering")
				&& !facultyDepartment.equals("English"));

		System.out.println("\n");
		System.out.println("\tFaculty added!\n");

		Faculty faculty = new Faculty(rank);

		faculty.setDepartment(facultyDepartment);
		faculty.setFullName(facultyFullName);
		faculty.setId(facultyId);

		saveOnArray(arrayPerson, faculty);
		} catch(idException expt) {
			System.out.println(expt.getMessage());
		}
	}

	// Staff Method
	public void staff(Person[] arrayPerson) {

		String staffFullName, staffId, staffDepartment, status;

		Scanner scnr = new Scanner(System.in);

		// Staff Info
		try {
		System.out.print("\nName of the staff member: ");
		staffFullName = upperCaseFirstLetter(scnr.nextLine().toLowerCase());

		System.out.print("Enter the id: ");
		staffId = scnr.nextLine();
		validateID(staffId);

		// Staff Department
		do {
			System.out.print("Department: ");
			staffDepartment = upperCaseFirstLetter(scnr.nextLine().toLowerCase());

			if (!staffDepartment.equals("Mathematics") && !staffDepartment.equals("Engineering")
					&& !staffDepartment.equals("English")) {
				System.out.println("\t\t " + staffDepartment + " is invalid \n");
			}

		} while (!staffDepartment.equals("Mathematics") && !staffDepartment.equals("Engineering")
				&& !staffDepartment.equals("English"));

		// Staff Status
		do {
			System.out.print("Status, Enter P for Part Time or Enter F for Full Time: ");
			status = scnr.nextLine().toLowerCase();

			if (status.equals("p")) {
				System.out.println("Part Time \n");
				status = "Part Time";
			} else if (status.equals("f")) {
				System.out.println("Full Time \n");
				status = "Full Time";
			} else {
				System.out.println("\t\t " + status + " is invalid \n");
			}

		} while ((!status.equals("Part Time") && !status.equals("Full Time")));

		System.out.println("\nStaff member added!\n");

		Staff staff = new Staff(status);

		staff.setFullName(staffFullName);
		staff.setDepartment(staffDepartment);
		staff.setId(staffId);

		saveOnArray(arrayPerson, staff);
		} catch(idException expt) {
			System.out.println(expt.getMessage());
		}
	}

	public static Student searchStudent(Person[] arrayPerson, String id) {

		for (int i = 0; i < 100; i++) {
			if (arrayPerson[i] != null && arrayPerson[i].getClass().getSimpleName().equals("Student")
				&& arrayPerson[i].getId().equals(id)) {
				return (Student) arrayPerson[i];
			}
		}
		return null;
	}

	public static Staff searchStaff(Person[] arrayPerson, String id) {

		for (int i = 0; i < 100; i++) {
			if (arrayPerson[i] != null && arrayPerson[i].getClass().getSimpleName().equals("Staff")
				&& arrayPerson[i].getId().equals(id)) {
				return (Staff) arrayPerson[i];
			}
		}
		return null;
	}

	public static Faculty searchFaculty(Person[] arrayPerson, String id) {

		for (int i = 0; i < 100; i++) {
			if (arrayPerson[i] != null && arrayPerson[i].getClass().getSimpleName().equals("Faculty")
				&& arrayPerson[i].getId().equals(id)) {
				return (Faculty) arrayPerson[i];
			}
		}
		return null;
	}

	// Main Menu Method
	public void menu() {

		Person[] arrayPerson = new Person[100];

		Scanner scnr = new Scanner(System.in);

		int option;
		String selection;

		do {
			System.out.println("\t\t\t\tWelcome to my Personal Management Program\n\n");
			System.out.println("Choose one of the options:\n");
			System.out.println("1-	Enter the information of a faculty");
			System.out.println("2-	Enter the information of a student");
			System.out.println("3-	Print tuition invoice for a student");
			System.out.println("4-	Print faculty information");
			System.out.println("5-	Enter the information of the staff member");
			System.out.println("6-	Print the information of the staff member");
			System.out.println("7-	Exit Program\n");
			System.out.print("\tEnter your selection: ");
			option = scnr.nextInt();

			switch (option) {

			case 1:
				faculty(arrayPerson);
				break;

			case 2:
				student(arrayPerson);
				break;

			case 3:
				Student student = new Student();
				System.out.print("\tEnter the student's id: ");
				selection = scnr.nextLine();
				selection = scnr.nextLine();
				student = (Student) searchStudent(arrayPerson, selection);
				if (student == null) {
					System.out.println("\nNo student matched!\n");
				} else {
					student.tuitionInvoice();
				}
				break;

			case 4:
				Faculty faculty = new Faculty();
				System.out.print("\tEnter the faculty's id: ");
				selection = scnr.nextLine();
				selection = scnr.nextLine();
				faculty = (Faculty) searchFaculty(arrayPerson, selection);
				if (faculty == null) {
					System.out.println("\nNo faculty matched!\n");
				} else {
					faculty.print();
				}
				break;

			case 5:
				staff(arrayPerson);
				break;

			case 6:
				Staff staff = new Staff();
				System.out.print("\tEnter the staff's id: ");
				selection = scnr.nextLine();
				selection = scnr.nextLine();
				staff = (Staff) searchStaff(arrayPerson, selection);
				if (staff == null) {
					System.out.println("\nNo staff member matched!\n");
				} else {
					staff.print();
				}
				break;

			case 7:
				String choice;
				System.out.println("Would you like to create the report? (Y/N): ");
				scnr.nextLine();
				choice = scnr.nextLine().toLowerCase();
				if(choice.equals("y")) {
					saveOnFile(arrayPerson);
					System.out.println("Report created!");
				} else {
					System.out.print("Goodbye!");
				}
				break;

			default:
				System.out.println("\nInvalid entry- please try again\n\n");
				break;
			}
		} while (option != 7);
	}

	public static void saveOnFile(Person[] arrayPerson) {
		
		Calendar calendar = Calendar.getInstance();  
		
		FileOutputStream fileStream = null;
	    PrintWriter outFS = null;
		
		try {
			int counter;
			fileStream = new FileOutputStream("C:\\Users\\rapha\\eclipse-workspace\\projects\\src\\report.dat");
			outFS = new PrintWriter(fileStream);
			outFS.println("\t\tReport created on " + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR));  
			outFS.println("\t\t***************************"); 
			
		    	outFS.println("Faculty Members");
		    	outFS.println("-------------------------");
		    	counter = 1;
			for(int i = 0; i < 100; i++) {
				if (arrayPerson[i] != null && arrayPerson[i].getClass().getSimpleName().equals("Faculty")) {			
				Faculty faculty = (Faculty)arrayPerson[i];
				outFS.println(counter + ". " + faculty.fullName);
			    outFS.println("ID: " + faculty.id);
			    outFS.println(faculty.rank + ", " + faculty.department);
			    outFS.println("\n");
			    counter++;
				}
			}
		    	outFS.println("Staff Members");
		    	outFS.println("-------------------------");
		    	counter = 1;
			for(int i = 0; i < 100; i++) {
				if (arrayPerson[i] != null && arrayPerson[i].getClass().getSimpleName().equals("Staff")) {
				Staff staff = (Staff)arrayPerson[i];
			    outFS.println(counter + ". " + staff.fullName);
			    outFS.println("ID: " + staff.id);
			    outFS.println(staff.department + ", " + staff.status);
			    outFS.println("\n");
			    counter++;
				}
			}
				outFS.println("Students");
				outFS.println("-------------------------");
				counter = 1;
			for(int i = 0; i < 100; i++) {
				if (arrayPerson[i] != null && arrayPerson[i].getClass().getSimpleName().equals("Student")) {
				Student student = (Student)arrayPerson[i];
			    outFS.println(counter + ". " + student.fullName);
			    outFS.println("ID: " + student.id);
			    outFS.println("Gpa: " + student.gpa);
			    outFS.println("Credit hours: " + student.numCreditHours);
			    outFS.println("\n");
			    counter++;
				}
			}
			
			
		} catch(FileNotFoundException e) {
			System.out.println("File does not exist");
		} finally {
		    outFS.close();
		}
	    
	}
	
	class idException extends Exception {  
		 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		idException(String s) {  
			 super(s);  
		 }  
	} 

	abstract class Person {

		public abstract void print();

		protected String fullName;
		protected String id;

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}

	class Student extends Person {

		private double gpa;
		private int numCreditHours;

		public Student(double gpa, int numCreditHours) {

			this.gpa = gpa;
			this.numCreditHours = numCreditHours;

		}

		public Student() {
			super();
		}

		public double getGpa() {
			return gpa;
		}

		public void setGpa(double gpa) {
			this.gpa = gpa;
		}

		public int getNumCreditHours() {
			return numCreditHours;
		}

		public void setNumCreditHours(int numCreditHours) {
			this.numCreditHours = numCreditHours;
		}

		@Override
		public void print() {
			System.out.println("---------------------------------------------------------------------------");
			System.out.println(fullName + "\t" + id);
			System.out.println(numCreditHours + ",\t" + gpa);
			System.out.println("---------------------------------------------------------------------------");
		}

		// Tuition Invoice Method
		public void tuitionInvoice() {

			DecimalFormat formatter = new DecimalFormat("#0.00");

			double totalFees = 52.0;
			double totalPayment;
			double discountApplied = 0;

			if (gpa >= 3.85) {
				totalPayment = ((numCreditHours * 236.45) + totalFees) * 0.85;
				discountApplied = ((numCreditHours * 236.45) + totalFees) * 0.15;
			} else {
				totalPayment = (numCreditHours * 236.45) + totalFees;
			}

			System.out.println("\nHere is the tuition invoice for " + fullName + ":");
			System.out.println("---------------------------------------------------------------------------");
			System.out.println(fullName + "\t\t\t\t" + id);
			System.out.println("Credit Hours: " + numCreditHours + " ($236.45/credit hour)");
			System.out.println("Fees: $" + formatter.format(totalFees) + "\n");
			System.out.printf("Total payment (after discount): $" + formatter.format(totalPayment) + "\t  ($"
					+ formatter.format(discountApplied) + " discount applied)");
			System.out.println("\n---------------------------------------------------------------------------");
		}
	}
	
	

	class Faculty extends Employee {

		private String rank;

		public Faculty(String rank) {
			super();
			this.rank = rank;
		}

		public Faculty() {
			super();
		}

		public String getRank() {
			return rank;
		}

		public void setRank(String rank) {
			this.rank = rank;
		}

		@Override
		public void print() {
			System.out.println("---------------------------------------------------------------------------");
			System.out.println(fullName + "\t" + id);
			System.out.println(department + ", " + rank);
			System.out.println("---------------------------------------------------------------------------");
		}

	}

	abstract class Employee extends Person {

		public String department;

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

	}

	class Staff extends Employee {

		private String status;

		public Staff(String status) {
			super();
			this.status = status;
		}

		public Staff() {
			super();
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		@Override
		public void print() {
			System.out.println("---------------------------------------------------------------------------");
			System.out.println(fullName + "\t" + id);
			System.out.print(department + ", ");
			System.out.println(status);
			System.out.println("---------------------------------------------------------------------------");
		}
	}
} // end of program
