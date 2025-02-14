# Calculator Program

## Description
This Java-based calculator program allows users to perform basic arithmetic operations and some advanced mathematical functions. Users can interact with the program either by entering commands directly through the console or by using a `.txt` file with pre-written commands. The program will continue to accept inputs until the word `EXIT` is provided.

## Features
- **Basic operations:** Add, subtract, multiply, divide
- **Advanced operations:** Find the power of (pow), find the root of (root), square root (sqrt)
- **Multiple input methods:** Console or `.txt` file
- **Continuous input:** Enter commands continuously until `EXIT` is provided
- **Error handling:** Displays error messages for invalid commands and prompts for correct input

## Usage
1. **Console Input:**
    - Start the program, and a welcome message will prompt you to enter commands.
    - Enter a command directly in the console:
        ```java
        // Sample commands:
        System.out.println("add 5 2");  // Output: 7.0
        System.out.println("pow 3 2");  // Output: 9.0
        System.out.println("root 256 4");  // Output: 4.0
        ```
    - The program will print the result in the format: `Result: [calculated result]`.
    - For invalid commands, an error message will appear, and the program will prompt you to input another command.

2. **File Input:**
    - Provide a `.txt` file with commands.
    - The program reads the entire file, processes each line, and prints results line by line.
    - For invalid lines, the program outputs `Invalid Command` but continues to process subsequent lines in the file.

3. **Exit Command:**
    - The program will continue to accept input until you input the word `EXIT`.

## Example
Here's a simple example of how the program works:
```java
// Console input example
Input: add 5 2
Output: Result: 7.0

Input: pow 3 2
Output: Result: 9.0

Input: root 256 4
Output: Result: 4.0

// File input example (commands.txt)
File contents:
add 5 2
pow 3 2
root 256 4

Program Output:
Result: 7.0
Result: 9.0
Result: 4.0
