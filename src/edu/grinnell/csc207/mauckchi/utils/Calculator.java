// REMEMBER TO MAKE A PROPER THROWS EXCEPTION!!!!!
// HERE AND IN UI CLASS!!!!!!!!!!!!!!!!!!!!!!!!!!!
package edu.grinnell.csc207.mauckchi.utils;

import java.io.PrintWriter;

public class Calculator
{
  
  String[] storage = new String[8];
  
  public Calculator() {}
  
  public Fraction evaluate(String expression)
      throws Exception
  {
    //System.out.println(expression);
    if (expression.charAt(0) == 'r' &&
        expression.charAt(3) == '=')
      {
      char charNum = expression.charAt(1);
      int num = Character.getNumericValue(charNum);
      Fraction result = eval1(expression.substring(5));
      this.storage[num] = result.toString();
      //System.out.println("found r " + num);
      return result;
      }
    else
      {
        return eval1(expression);
      }
  }
  
  public Fraction eval1(String str)
  {
    // Create array of values (removing spaces)
    String[] arr = Utils.splitAt(str, ' ');
    // Go through array and replace storage values with fractions
    for (int i = 0; i < arr.length; i++)
      {
        if (arr[i].charAt(0) == 'r')
          {
          char charNum = arr[i].charAt(1);
          int num = Character.getNumericValue(charNum);
          String val = this.storage[num];
          arr[i] = val;
          }
      }
    Fraction value1 = new Fraction(arr[0]);
    Fraction value2;
    // Checks for a single element array
    if (arr.length == 1)
      {
        return value1.simplify();
      } // if

    // Loops through array of numbers and operators
    for (int i = 0; i < arr.length;)
      {
        // checks the value at current position in arr
        switch (arr[i])
          {
          /* Checks the type of value.
           * If an operator: grabs the next value and computes the
           * result of applying the operator. Moves the loop to the 
           * next operation, storing the new result in value1.
           * If a value: (first loop) moves to the next operator.
           */
            case "+":
              value2 = new Fraction(arr[i + 1]);
              value1 = value1.add(value2);
              i += 2;
              break;
            case "-":
              String temp = arr[i + 1];
              value2 = new Fraction(arr[i + 1]);
              value1 = value1.subtract(value2);
              i += 2;
              break;
            case "*":
              value2 = new Fraction(arr[i + 1]);
              value1 = value1.multiply(value2);
              i += 2;
              break;
            case "/":
              value2 = new Fraction(arr[i + 1]);
              value1 = value1.divide(value2);
              i += 2;
              break;
            case "^":
              int val2int = Integer.valueOf(arr[i + 1]);
              value1 = value1.pow(val2int);
              i += 2;
              break;
            default:
              i++;
              break;
          } // switch
      } // for 
    return value1.simplify();
  } // eval1
  
  /*public static void main(String[] args)
      throws Exception
  { 
    PrintWriter pen = new PrintWriter(System.out, true);
    Calculator calc1 = new Calculator();
    pen.println("simple " + calc1.evaluate("1 + 2"));
    pen.println("r0 " + calc1.evaluate("r0 = 1"));
    pen.println("r1 " + calc1.evaluate("r1 = 9 ^ 2"));
    pen.println("r2 " + calc1.evaluate("r2 = 5/3 * 2"));
    pen.println("r3 " + calc1.evaluate("r3 = 3 - r1"));
    pen.println("r4 " + calc1.evaluate("r2 - r1"));
    pen.close();
  }*/
}
