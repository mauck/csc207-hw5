package edu.grinnell.csc207.mauckchi.utils;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class UI
{
  public static String readString(PrintWriter pw, BufferedReader br,
                                  String prompt)
    throws Exception
  {
    if (!prompt.equals(""))
      {
        pw.print(prompt);
        pw.flush();
      } // if there is a prompt
    String response = br.readLine();
    return response;
  }

  public static void UserInterface()
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    Calculator calc = new Calculator();
    java.io.BufferedReader br;
    java.io.InputStreamReader istream;
    istream = new java.io.InputStreamReader(System.in);
    br = new java.io.BufferedReader(istream);
    pen.println("Welcome to the Calculator! Your Options are:");
    pen.println("A. Type the letter E to exit");
    pen.println("B. Input an expression of the format");
    pen.println("\t value operation value etc. (separated by spaces)");
    pen.println("\t Operations include: +, -, *, / and ^");
    pen.println("C. Set a storage value (one of 8) with");
    pen.println("\t ri = (your expression)");
    pen.println("\t i can be between 0 and 7");
    String expression =
        readString(pen, br, "Please enter an expression to be evaluated: ");
    while (expression.compareTo("E") != 0)
      {
        if (expression.charAt(0) == 'r' && expression.charAt(3) == '=')
          {
            char charNum = expression.charAt(1);
            pen.print("r" + charNum + ": ");
          }
        pen.println(calc.evaluate(expression));
        expression =
            readString(pen, br, "Please enter an expression to be evaluated: ");
      }
    pen.println("Thank you and Have a Nice Day!");
    return;
  }

  public static void main(String[] args)
    throws Exception
  {
    UserInterface();
  }
}
