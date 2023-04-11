public class ScalarInteger implements Scalar {
    private int number;

    public ScalarInteger(int number)
    {
        this.number = number;
    }
    @Override
    public Scalar add(Scalar s) {
        if (s instanceof ScalarInteger)
            return new ScalarInteger(this.number + ((ScalarInteger) s).getNumber());
        else
        {
            int newNumerator = number * ((ScalarRational)s ).getDenominator() +
                    ((ScalarRational)s).getNumerator();
            return new ScalarRational(newNumerator,((ScalarRational) s).getDenominator());
        }
    }

    @Override
    public Scalar mul(Scalar s) {
        if (s instanceof ScalarInteger)
            return new ScalarInteger(this.number * ((ScalarInteger) s).getNumber());
        else {
            int newNumerator = number * ((ScalarRational)s).getNumerator();
            return new ScalarRational(newNumerator,((ScalarRational) s).getDenominator());
        }
    }

    @Override
    public Scalar neg() {
        return new ScalarInteger(-number);
    }

    @Override
    public Scalar power(int exponent) {
        int ans = number;
        for (int i = 0; i < exponent; i++) {
            ans = ans * number;
        }
        return new ScalarInteger(ans);
    }

    @Override
    public int sign() {
        if (number < 0)
            return -1;
        else if (number>0)
            return  1;
        else
            return  0;
    }

    @Override
    public String toString() {
        return number + "";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
