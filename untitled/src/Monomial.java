public class Monomial {
    private int exponent;
    private Scalar coefficient;

    public Monomial(int exponent, Scalar coefficient) {
        this.exponent = exponent;
        this.coefficient = coefficient;
    }

    public Monomial add(Monomial m)
    {
        if (this.exponent != m.exponent)
            return null;
        return new Monomial(exponent,coefficient.add(m.coefficient));

    }
    public Monomial mul(Monomial m)
    {
        return  new Monomial(exponent+ m.exponent,coefficient.mul(m.coefficient));
    }
    public Scalar evaluate(Scalar s)
    {
        Scalar x = s.power(exponent);
        return s.mul(x);
    }
    public Monomial derivative()
    {
        return new Monomial(exponent-1,coefficient.mul(new ScalarInteger(exponent)));
    }
    public int sign()
    {
        return coefficient.sign();
    }
    @Override
    public String toString() {
        if (exponent == 0)
            return coefficient.toString();
        if (exponent == 1)
            return coefficient.toString() + "x";
        return coefficient.toString() + "x^" + exponent;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public Scalar getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Scalar coefficient) {
        this.coefficient = coefficient;
    }
}
