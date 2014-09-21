package edu.grinnell.csc207.mauckchi.utils;

/*
 * Took equals(BigInteger), equals(Fraction) and equals(Object) from
 * Sam's eboard discussion:
 * http://www.math.grin.edu/~rebelsky/Courses/CSC207/2014F/eboards/eboard.10.md
 */

/*
 * Reused Fraction class from HW 4
 */
import java.math.BigInteger;

public class Fraction
{

  /**
  * A simple implementation of Fractions.
  *
  * @author Samuel A. Rebelsky
  * @author Leah, China, and Eileen
  * @author CSC152 2005S
  * @version 1.0 of February 2005
  */

  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+
  /*
  * (1) Denominators are always positive. Therefore, negative fractions are
  * represented with a negative numerator. Similarly, if a fraction has a
  * negative numerator, it is negative.
  *
  * (2) Fractions are not necessarily stored in simplified form. To obtain a
  * fraction in simplified form, one must call the simplify method.
  */
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+
  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;
  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
  * Build a new fraction with numerator num and denominator denom.
  *
  * Warning! Not yet stable.
  */
  public Fraction(BigInteger num, BigInteger denom)
  {
    this.num = num;
    this.denom = denom;
  } // Fraction(BigInteger, BigInteger)

  /**
  * Build a new fraction with numerator num and denominator denom.
  *
  * Warning! Not yet stable.
  */
  public Fraction(int num, int denom)
  {
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
  } // Fraction(int, int)

  /**
  * Build a new fraction with numerator and denominator determined by 
  * all reasonable strings that describe fractions.
  */
  public Fraction(String str)
  {
    int index = str.indexOf("/");
    if (index == -1)
      {
        if (isNeg(str))
          {
            this.num = BigInteger.valueOf(Integer.parseInt(str.substring(1)));
            this.num = this.num.negate();
          } // if str[0] = '-'
        else
          {
            this.num = BigInteger.valueOf(Integer.parseInt(str));
          } // else
        this.denom = BigInteger.valueOf(1);
      } // if no "/" in str
    else
      {
        if (isNeg(str))
          {
            this.num =
                BigInteger.valueOf(Integer.parseInt(str.substring(1, index)));
            this.num = this.num.negate();
          } // if str[0] = '-'
        else
          {
            this.num =
                BigInteger.valueOf(Integer.parseInt(str.substring(0, index)));
          } // else
        this.denom =
            BigInteger.valueOf(Integer.parseInt(str.substring(index + 1)));
      } // else
  } // Fraction(String)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+
  /**
  * Checks if the string starts with a '-'.
  */
  public static boolean isNeg(String str)
  {
    char c = str.charAt(0);
    if (c == '-')
      return true;
    return false;
  } // isNeg(String)

  /**
  * Express this fraction as a double.
  */
  public double doubleValue()
  {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
  * Add the fraction other to this fraction.
  */
  public Fraction add(Fraction addMe)
  {
    BigInteger resultNumerator;
    BigInteger resultDenominator;
    // The denominator of the result is the
    // product of this object's denominator
    // and addMe's denominator
    resultDenominator = this.denom.multiply(addMe.denom);
    // The numerator is more complicated
    resultNumerator =
        (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));
    // Return the computed value
    Fraction result = new Fraction(resultNumerator, resultDenominator);
    return result.simplify();
  }// add(Fraction)

  /**
  * Convert this fraction to a string for ease of printing.
  */
  public String toString()
  {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO))
      {
        return "0";
      } // if it's zero
    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()

  /**
  * Return an additive inverse of the original fraction.
  */
  public Fraction negate()
  {
    return this.multiply(new Fraction(-1, 1));
  } // negate()

  /**
  * Return a fraction that is the product of the given fraction
  * and the multiplicand.
  */
  public Fraction multiply(Fraction multiplicand)
  {
    BigInteger resultNumerator;
    BigInteger resultDenominator;
    // The denominator of the result is the
    // product of this object's denominator
    // and multiplicand's denominator
    resultDenominator = this.denom.multiply(multiplicand.denom);
    // The numerator is the product of this
    // object's numerator and multiplicand's
    // numerator
    resultNumerator = this.num.multiply(multiplicand.num);
    // Return the computed value
    Fraction result = new Fraction(resultNumerator, resultDenominator);
    return result.simplify();
  }// multiply(Fraction)

  /**
  * Return a fraction that is the difference of the given fraction and the
  * subtrahend.
  */
  public Fraction subtract(Fraction subtrahend)
  {
    BigInteger resultNumerator;
    BigInteger resultDenominator;
    // The denominator of the result is the
    // product of this object's denominator
    // and subtrahend's denominator
    resultDenominator = this.denom.multiply(subtrahend.denom);
    // The numerator is more complicated
    resultNumerator =
        (this.num.multiply(subtrahend.denom)).subtract(subtrahend.num.multiply(this.denom));
    // Return the computed value
    Fraction result = new Fraction(resultNumerator, resultDenominator);
    return result.simplify();
  }// subtract(Fraction)

  /**
  * Return a fraction that is the quotient of the given fraction
  * and the divisor.
  */
  public Fraction divide(Fraction divisor)
  {
    BigInteger resultNumerator;
    BigInteger resultDenominator;
    // The denominator of the result is the
    // product of this object's denominator
    // and divisor's dnumerator
    resultDenominator = this.denom.multiply(divisor.num);
    // The numerator is the product of this
    // object's numerator and divisor's
    // denominator
    resultNumerator = this.num.multiply(divisor.denom);
    // Return the computed value
    Fraction result = new Fraction(resultNumerator, resultDenominator);
    return result.simplify();
  }// divide(Fraction)

  /**
  * Return a fraction that is the given fraction raised to the given
  * exponent.
  */
  public Fraction pow(int expt)
  {
    // copy the given fraction
    Fraction result = this;
    // loop through the absolute value of the exponent
    // multiplying the result by the given fraction each time
    for (int i = 2; i <= Math.abs(expt); i++)
      {
        result = this.multiply(result);
      } // for i
    // check if the exponent was negative or equal to zero
    if (expt == 0)
      {
        return new Fraction("1");
      } // if
    else if (expt < 0)
      {
        BigInteger temp = result.num;
        result.num = result.denom;
        result.denom = temp;
      } // else
    return result.simplify();
  } // pow(int)

  /**
  * Return a fraction that is the given fraction simplified.
  */
  public Fraction simplify()
  {
    BigInteger resultNumerator;
    BigInteger resultDenominator;
    // get the gcd (greatest common divisor)
    BigInteger gcd = this.num.gcd(this.denom);

    // change num and denom by dividing by gcd
    resultNumerator = this.num.divide(gcd);
    resultDenominator = this.denom.divide(gcd);

    // if num is 0, fraction is zero so set denom to 1
    if (resultNumerator == BigInteger.valueOf(0))
      {
        resultDenominator = BigInteger.valueOf(1);
      } // if

    // if denom is < 0, negate num and denom
    if (resultDenominator.compareTo(BigInteger.valueOf(0)) == -1)
      {
        resultNumerator = resultNumerator.multiply(BigInteger.valueOf(-1));
        resultDenominator = resultDenominator.multiply(BigInteger.valueOf(-1));
      } // if
    return new Fraction(resultNumerator, resultDenominator);
  } // simplify()

  /**
  * Check if the given object is equal to the object called on.
  * (Used for fractional comparison)
  */
  public boolean equals(Object other)
  {
    // If the objects occupy the same area of memory, they are
    // the same
    if (this == other)
      {
        return true;
      } // this == other
    // If the other object is a Fraction
    else if (other instanceof Fraction)
      {
        return this.equals((Fraction) other);
      } // other is a Fraction
    // If the other object is a BigInteger
    else if (other instanceof BigInteger)
      {
        return this.equals((BigInteger) other);
      } // other is BigInteger
    // Everything else is different.
    else
      return false;
  } // equals(Object)

  /** 
   * Determine if this fraction is the same as a BigInteger.
   * A fraction is the same as a BigInteger when the denominator
   * of the fraction is 1 and the numerator is the same as the
   * BigInteger.
   */
  public boolean equals(BigInteger other)
  {
    return this.denom.equals(BigInteger.ONE) && this.num.equals(other);
  } // equals(BigInteger)

  /** 
   * Determine if this fraction is the same as another fraction.
   * Two fractions are the same if they have the same numerator
   * and denominator.
   */
  public boolean equals(Fraction other)
  {
    return this.num.equals(other.num) && this.denom.equals(other.denom);
  } // equals(Fraction)

} // class Fraction
