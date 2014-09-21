package edu.grinnell.csc207.mauckchi.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils
{
  public static BigDecimal sqrt(BigDecimal d, BigDecimal epsilon)
  {
    BigDecimal a = new BigDecimal(1);
    BigDecimal two = new BigDecimal(2);

    while ((d.divide(a, 10, RoundingMode.CEILING).subtract(a)).abs()
                                                              .compareTo(epsilon) != -1)
      {
        a = ((d.divide(a, 10, RoundingMode.CEILING).add(a)).divide(two));
      } // while
    return a;

  } // sqrt(BigDecimal, BigDecmial)

  /*
   * We were unclear on how to go about this problem, and came
   * across this group's solution. We used their ideas on how 
   * to deal with the cases of even and odd exponents without
   * using recursion.
   * https://github.com/wolterzo/csc207-hw5/blob/master/src/edu
   * /grinnell/csc207/aza/utils/Utils.java
   */
  
  public static int expt(int x, int n)
  {
    // initializing result to the base
    int result = x;
    // tempExponent is used to keep track of how many times we
    // can go through the loop, so we don't multiply past n
    int tempExponent = 1;

    // if the exponent is 0, the result is always 1
    if (n == 0)
      return 1;
    // if the exponent is 1, the result is the base number
    if (n == 1)
      return x;

    // looping through and checking to make sure that we don't
    // go over the exponent
    while (tempExponent < n)
      {
        // if difference between n and tempExponent is even
        // and 2 * tempExponent is smaller than n, then square
        // current result and store it back in result, also multiplying
        // tempExponent by 2 to account for the squaring that occurred
        if (((n - tempExponent) % 2 == 0) && ((tempExponent * 2) < n))
          {
            result *= result;
            tempExponent *= 2;
          } // if
        
        // if difference between n and tempExponent is odd or 
        // 2 * tempExponent > n, multiply current result by 
        // x and store back in result. Increment tempExponent
        // by 1 to account for this multiplication.
        else
          {
            result *= x;
            tempExponent++;
          } // else
      } // while
    return result;
  } // expt (int, int)

  public static void main(String[] args)
  {
   // BigDecimal sixteen = new BigDecimal(3300);
   // BigDecimal c = new BigDecimal(.01);
   // BigDecimal root = sqrt(sixteen, c);
   // System.out.println("root " + root);
    
    int exp = expt(7,0);
    System.out.println(exp);
  }
  
  public static String[] splitAt(String str, char c)
  {
    String[] tempArr = new String[str.length()];
    int counter = 0;
    int index1 = 0;
    int index2 = str.indexOf(c);
    // loops through entire length of tempArr
    for (int i = 0; i < tempArr.length; i++)
      {
        // checking for the end of the string
        if (index1 == str.lastIndexOf(c) + 1)
          {
            tempArr[i] = str.substring(index1);
            counter++;
            break;
          } // if
        else
          {
            tempArr[i] = str.substring(index1, index2);
            index1 = index2 + 1;
            index2 = str.indexOf(c, index1);
          } // else
        counter++;
      } // for
    String[] resultArr = new String[counter];
    //transferring tempArr elements into resultArr
    for (int i = 0; i < resultArr.length; i++)
      {
        resultArr[i] = tempArr[i];
      } // for
    return resultArr;
  } // splitAt();


} // class Utils