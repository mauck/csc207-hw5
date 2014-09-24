package edu.grinnell.csc207.mauckchi.utils;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class UI
{
  /** Read a given prompt from the console */
  public static String readString(PrintWriter pw, BufferedReader br,
                                  String prompt)
    throws Exception
  {
    // check prompt is present
    if (!prompt.equals(""))
      {
        // ask for next input
        pw.print(prompt);
        pw.flush();
      } // if there is a prompt
    // get the prompt
    String response = br.readLine();
    return response;
  } // readString(PrintWriter, BufferedReader, String)

  /** User input is requested from the console and calculator values printed*/
  public static void UserInterface()
    throws Exception
  {
    // create new pen to print to console, new calculator to evaluate expressions
    // and bufferedreader to read from console (with the accompanying inputstreamreader
    PrintWriter pen = new PrintWriter(System.out, true);
    Calculator calc = new Calculator();
    java.io.BufferedReader br;
    java.io.InputStreamReader istream;
    istream = new java.io.InputStreamReader(System.in);
    br = new java.io.BufferedReader(istream);
    // Introduce calculator UI
    pen.println("Welcome to the Calculator! Your Options are:");
    pen.println("A. Type the letter E to exit");
    pen.println("B. Input an expression of the format");
    pen.println("\t value operation value etc. (separated by spaces)");
    pen.println("\t Operations include: +, -, *, / and ^");
    pen.println("C. Set a storage value (one of 8) with");
    pen.println("\t ri = (your expression)");
    pen.println("\t i can be between 0 and 7");
    // read first line of input
    String expression =
        readString(pen, br, "Please enter an expression to be evaluated: ");
    // check for exit command
    while (expression.compareTo("E") != 0)
      {
        // check proper formatting, throwing necessary exceptions
        if (formatChecker(expression, pen))
          {
            // print the evaluated expression
            pen.println(calc.evaluate(expression));
            // ask for and read the next line of input
            expression = readString(pen, br,
                           "Please enter an expression to be evaluated: ");
          }
      } // while not exiting
    pen.println("Thank you and Have a Nice Day!");
    return;
  } // UserInterface

  /** Checks naive format of given expression, printing assignation values*/
  public static boolean formatChecker(String str, PrintWriter pen)
    throws Exception
  {
    int length = str.length();
    // loop through length of string, checking each character
    for (int i = 0; i < length; i++)
      {
        char c = str.charAt(i);
        // check char options
        switch (c)
          {
            case 'r':
              // check storage request format
              if (checkR(str.substring(i)))
                {
                  // check for proper asignation format
                  if (i == 0 && str.length() > 2 && str.charAt(3) == '=')
                    {
                      char charNum = str.charAt(1);
                      // prepare printout
                      pen.print("r" + charNum + ": ");
                    } // if assigning 
                } // if storage request formatted
              break; // if r
            case '-': case '/':
              // check next value is neither space nor digit
              // for neg uses and fractions
              if (str.charAt(i + 1) != ' ' && !Character.isDigit(str.charAt(i + 1)))
                {
                  throw new Exception("Space formatting error");
                } // if not neg or fraction
              break; // if - or /
            case '+':
            case '*':
            case '^':
            case '=':
              // check next value is not a space
              if (str.charAt(i + 1) != ' ')
                {
                  throw new Exception("Space formatting error");
                }
              break; // if +, *, ^, =
            case ' ':
              break; // if space do nothing and move on
            default:
              if (!Character.isDigit(c))
                {
                  throw new Exception("Unacceptable character found");
                } // if digit and not r (already caught at top)
          } // switch
      } // for loop
    return true;
  } // formatChecker(string, PrintWriter)

  /** Checks proper r storage formatting */
  public static boolean checkR(String str)
    throws Exception
  {
    // checks for valid index
    if (str.charAt(1) != '8' && str.charAt(1) != '9'
        && (str.length() < 3 || str.charAt(2) == ' '))
      {
        return true;
      } // if index is valid
    else
      throw new Exception("Improper storage reference formatting");
  } // checkR(String)

  /** Main call to user interface */
  public static void main(String[] args)
    throws Exception
  {
    UserInterface();
  } // main
} // UI class
