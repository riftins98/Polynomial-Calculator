public class ScalarInteger implements Scalar {
    private int number;

    public ScalarInteger(int number)
    {
        this.number = number;
    }
    @Override
    public Scalar add(Scalar s) {
        return s.add(this);
    }
    public Scalar add(ScalarInteger s){
        return new ScalarInteger(s.getNumber() + this.getNumber());
    }

    public Scalar add(ScalarRational s){
        int newNumerator = number * s.getDenominator() + s.getNumerator();
        return new ScalarRational(newNumerator,s.getDenominator()).reduce();
    }

    @Override
    public Scalar mul(Scalar s) {
        return s.mul(this);
    }

    @Override
    public Scalar mul(ScalarInteger s) {
        return new ScalarInteger(this.number *  s.getNumber());
    }

    @Override
    public Scalar mul(ScalarRational s) {
        int newNumerator = number * s.getNumerator();
        return new ScalarRational(newNumerator,s.getDenominator()).reduce();
    }

    @Override
    public Scalar neg() {
        return new ScalarInteger(-number);
    }

    @Override
    public Scalar power(int exponent) {
        if (exponent == 0)
            return new ScalarInteger(1);
        int ans = number;
        for (int i = 1; i < exponent; i++) {
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof ScalarInteger) {
            ScalarInteger that = (ScalarInteger) o;
            return that.number == this.number;
        }
        else if (o instanceof ScalarRational){
            ScalarRational that = (ScalarRational) o;
            ScalarRational thatRed = that.reduce();
            if (thatRed.getNumerator() % thatRed.getDenominator() == 0)
                return ((thatRed.getNumerator() / thatRed.getDenominator()) == this.number);
            return false;
        }
        return false;
    }


}
