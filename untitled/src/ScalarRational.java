public class ScalarRational implements Scalar {
    private int numerator;
    private int denominator;

    public ScalarRational(int numerator, int denominator)
    {
        this.denominator = denominator;
        this.numerator = numerator;
    }

    @Override
    public Scalar add(Scalar s) {
        if (s instanceof ScalarRational) {
            int newNumerator = (this.numerator * ((ScalarRational) s).getDenominator())
                    + (this.denominator *((ScalarRational) s).getNumerator());
            int newDenominator = this.denominator * ((ScalarRational) s).getDenominator();
            return new ScalarRational(newNumerator,newDenominator);
        }
        else {
            int newNumerator = this.denominator * ((ScalarInteger) s).getNumber() + numerator;
            return new ScalarRational(newNumerator, this.denominator);
        }
    }

    @Override
    public Scalar mul(Scalar s) {
        if (s instanceof ScalarRational) {
            int newNumerator = this.numerator * ((ScalarRational) s).getNumerator();
            int newDenominator = this.denominator * ((ScalarRational) s).getDenominator();
            return new ScalarRational(newNumerator,newDenominator);
        }
        else {
            int newNumerator = this.numerator * ((ScalarInteger) s).getNumber();
            return new ScalarRational(newNumerator, this.denominator);
        }
    }

    @Override
    public Scalar neg() {
        return new ScalarRational(-numerator,denominator);
    }

    @Override
    public Scalar power(int exponent) {
        int newNumerator = this.numerator;
        int newDenominator = this.denominator;
        for (int i = 0; i < exponent; i++) {
            newNumerator = newNumerator * this.numerator;
            newDenominator = newDenominator * this.denominator;
        }
        return new ScalarRational(newNumerator,newDenominator);
    }

    @Override
    public int sign() {
        if ((this.numerator / this.denominator) > 0)
            return 1;
        else if ((this.numerator / this.denominator) < 0)
            return -1;
        else
            return 0;
    }

    public ScalarRational reduce(){
        int newNumerator = this.numerator;
        int newDenominator = this.denominator;
        for (int i = 2; i <= newNumerator && i <= newDenominator; i++) {
                while ((newNumerator % i == 0) && (newDenominator % i == 0)) {
                    newNumerator = newNumerator / i;
                    newDenominator = newDenominator / i;
                }
         }
        return new ScalarRational(newNumerator,newDenominator);
    }

    @Override
    public String toString() {
        int outcome = this.numerator % this.denominator;
        if (outcome == 0 )
            return (this.numerator/this.denominator) + "";
        else
            return this.numerator + "/" + this.denominator;
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
}
