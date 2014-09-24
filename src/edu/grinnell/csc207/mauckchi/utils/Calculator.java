package edu.grinnell.csc207.mauckchi.utils;

public class Calculator
{
  // Create r0 - r7 storage Fields
  String[] storage = { "0", "0", "0", "0", "0", "0", "0", "0" };

  // Construct calculator
  public Calculator()
  {
  }

  /** Evaluate the given whole expression */
  public Fraction evaluate(String expression)
    throws Exception
  {
    // check if expression is setting specified r storage option
    if (expression.charAt(0) == 'r' && expression.length() > 2 && expression.charAt(3) == '=')
      {
        // find index of storage
        char charNum = expression.charAt(1);
        int num = Character.getNumericValue(charNum);
        // evaluate the sub-expression to be set
        Fraction result = eval1(expression.substring(5));
        // set the storage to the given expression's value
        this.storage[num] = result.toString();
        //System.out.println("found r " + num);
        return result;
      } // if setting r storage
     // simply return evaluated expression
     return eval1(expression);
  } // evaluate(String)

  /** Evaluate the given expression (of the naive format) */
  public Fraction eval1(String str)
    throws Exception
  {
    // Create array of values (removing spaces)
    String[] arr = Utils.splitAt(str, ' ');
    // Go through array and replace storage values with fractions
    for (int i = 0; i < arr.length; i++)
      {
        // check for r
        if (arr[i].charAt(0) == 'r')
          {
            // check for storage index
            String Num = arr[i].substring(1);
            int num = Integer.parseInt(Num);
            // find storage value
            String val = this.storage[num];
            // replace array value with storage value
            arr[i] = val;
          } // if r
      } // for loop through array
    // grab the first array value
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
  } // eval1(String)
} // Calculator class
