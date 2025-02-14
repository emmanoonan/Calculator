package project1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Calculator {
	// static b/c going to be used throughout the entire program
	private static double num1 = 0.0;
	private static double num2 = 0.0;
	private static String operation = "";
	
	// method to read console input
	public static boolean readInput(String userInput, Scanner scnr) {
		boolean valid = true;
		
		// Splits userInput by spaces. Now, the format should be: operation num1 num2
		String[] calcParts = userInput.split(" ");
		// Initializing the first word in userInput as the operation
		operation = calcParts[0];
		
		// This is the same thing as equals except it is case insensitive
		// Breaks out of this method if the user wants to exit
		if (operation.equalsIgnoreCase("EXIT")) {
			valid = false;
			return valid;
		}
		
		if (operation.equalsIgnoreCase("sqrt")) {
			// You only need 1 number for finding the square root
			if (calcParts.length > 2) {
				System.out.println("Error: Invalid command format");
				valid = false;
				return valid;
			}
			// You need a number and operation for sqrt
			if (calcParts.length < 2) {
				System.out.println("Error: Invalid command format");
				valid = false;
				return valid;
			// If there is a square root operation, it tries to read the num1 input.
			}
			try {
				num1 = Double.parseDouble(calcParts[1]);
			// If user inputs something other than a number for calcParts[1]
			} catch (NumberFormatException e) {
				System.out.println("Must enter a number for num1!");
				valid = false;
				return valid;
			}
		}
		// If any other operation has less than 3 parts, it is input incorrectly	
		else {
			if (calcParts.length < 3) {
				System.out.println("Error: Invalid command format");
				valid = false;
				return valid;
			}
			try {
				// Learned ab in office hours - parse turns string into double
				num1 = Double.parseDouble(calcParts[1]);
			} catch (NumberFormatException e) {
				System.out.println("Must enter a number for num1!");
				valid = false;
				return valid;
			}
			try {
				num2 = Double.parseDouble(calcParts[2]);
			} catch (NumberFormatException e) {
				System.out.println("Must enter a number for num2!");
				valid = false;
				return valid;
			}
		}
		return valid;
	}
	
	public static void processInput() {
		// In case the user typed in all caps or auto-capitalized the first letter
		switch (operation.toLowerCase()) {
			case "add":
				System.out.println("Result: " + (num1 + num2));
				break;
			case "subtract":
				System.out.println("Result: " + (num1 - num2));
				break;
			case "divide":
				// For users who try to divide by 0
				if (num2 == 0) {
					System.out.println("Cannot divide by 0. Please input again.");
				}
				else {
					System.out.println("Result: " + (num1 / num2));
				}
				break;
			case "multiply":
				System.out.println("Result: " + (num1 * num2));
				break;
			case "pow":
				// I orignally made a conditional statement to deal with negative exponents,
				// but the computer automatically does this properly
				System.out.println("Result: " + (Math.pow(num1, num2)));
				break;
			case "sqrt":
				// For users who try to find the sqrt of a negative number
				if (num1 < 0) {
					System.out.println("Error: Cannot computer square root of a negative number.");
				}
				else {
					System.out.println("Result: " + (Math.sqrt(num1)));
				}
				break;
			// nth root: num1 is base number, num2 is root
			case "root":
				// For users who try to find the nth root of a negative number
				if (num1 < 0) {
					System.out.println("Error: Cannot computer nth root of a negative number.");
				}
				if (num2 < 0) {
					System.out.println("Error: Cannot compute negative nth root of a number");
				}
				// deal w negative roots
				else {
					System.out.println("Result: " + (Math.pow(num1, 1/num2)));
				}
				break;
			default:
				// This means the user has entered an operation that is not on the list
				System.out.println("Error: Invalid Operation.");
				break;
		}
	}
	
	// For users who insert a txt file instead of a command directly into the console
	public static void readFromFile(String fileName) {
		try {
			// Initializing new file & file scanner
			File file = new File(fileName);
			Scanner fileScanner = new Scanner(file);
			
			boolean valid = true;
			// While the file still has contents left in it
			while (fileScanner.hasNextLine()) { 
				// File equivalent of userInput
				String fileInput = fileScanner.nextLine();
				// If the command is valid, it will run
				valid = readInput(fileInput, fileScanner);
				// If readInput had an issue
				if (!valid) {
					// If user put the word "EXIT" in txt file, it will say goodbye & break
					if (operation.equalsIgnoreCase("EXIT")) {
						System.out.println("Thank you for using the Text-Based Calculator!");
						fileScanner.close();
						break;
					}
					
					// If the input is not valid for reasons other than the user inputting "EXIT"
					// Not stopping when running into issues with file, instead saying that there
					// is an issue and attempting the other lines in the file
					while (!valid & !operation.equalsIgnoreCase("EXIT")) {
						// Prompting for new input
						System.out.println("Enter a new command: ");
						fileInput = fileScanner.nextLine();
						// Send new fileInput to readInput() and store valid
						valid = readInput(fileInput, fileScanner);
					}
					
				}
				// Prints out the line that is being processed (this is needed b/c as userInput
				// it is typed and therefore directly printed on the screen)
				System.out.println("Processing Line: " + fileInput);
				processInput();	
			}
		// In case the user inputs an invalid .txt file
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
		}
	}
	
	public static void main (String[] args) {
		// Display welcome message & directions for use
		System.out.println("Welcome to the Text-Based Calculator!");
		// I was going to delete this to get rid of extra stuff but I'm proud of it
		/*System.out.println("Here are the operations that you can do: add, subtract, multiply, divide, find the power of (pow), and find the root of (root), square root (sqrt)");
		System.out.println("Example input: add 5 2");
		System.out.println("Program will return 7.0");
		System.out.println("Another example input: pow 3 2");
		System.out.println("Program will return 9.0");
		System.out.println("Another example input: root 256 4");
		System.out.println("Program will return 4.0"); */
		//System.out.println("You will be continually prompted for input UNTIL you type 'EXIT'");
		System.out.println("Type 'exit' to quit the program.");
		Scanner scnr = new Scanner(System.in);
			
			while (true) {
				System.out.println("Enter command: ");
				// Strips any excess spacing in the beginning and end of a string
				String userInput = scnr.nextLine().strip();
				// if user enters a txt file, the readFromFile method will be used
				if (userInput.endsWith(".txt")) {
					readFromFile(userInput);
				// If not a txt file, will proceed to readInput
				} else {
					boolean valid = true;	
				valid = readInput(userInput, scnr);
				if (!valid) {
					// If user input the word "exit", will prompt w/ exit message and break
					if (operation.equalsIgnoreCase("EXIT")) {
						System.out.println("Thank you for using the Text-Based Calculator!");
						break;
					}
					// If user's input was invalid for reasons other than entering "EXIT"
					while (!valid & !operation.equalsIgnoreCase("EXIT")) {
						// Prompting for new input
						System.out.println("Enter a new command: ");
						userInput = scnr.nextLine();
						// Send new userInput to readInput() and store valid
						valid = readInput(userInput, scnr);
					}
					
				}
				processInput();	
			}
		}
		scnr.close();
	}
}
