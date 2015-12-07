

public class Fraction {
private	int numerator;
private int denominator;

	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public Fraction() {
		numerator = 1;
		denominator = 1;
	}

	public int getNumerator() {
		return numerator;
	}

	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}

	Fraction addFractions(Fraction f1, Fraction f2)
	{
		Fraction c = new Fraction();
		c.setNumerator(f1.getNumerator()*f2.getDenominator() + f2.getNumerator()*f1.getDenominator());
		c.setDenominator(f1.getDenominator()*f2.getDenominator());
		return (c);
	}
	
	Fraction subFractions(Fraction f1, Fraction f2)
	{
		Fraction d = new Fraction();
		d.setNumerator(f1.getNumerator()*f2.getDenominator() - f2.getNumerator()*f1.getDenominator());
		d.setDenominator(f1.getDenominator()*f2.getDenominator());
		return (d);
	}
}
