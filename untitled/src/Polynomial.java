import java.util.Iterator;
import java.util.LinkedList;

public class Polynomial {
     private LinkedList<Monomial> monomials;

    public Polynomial() {
        monomials = new LinkedList<>();
    }

    public static Polynomial build(String input)
    {
        Polynomial ans = new Polynomial();
        String [] splited = input.split("[ ]+");
        for (int i = 0; i < splited.length; i++) {
            Monomial a = new Monomial(i,new ScalarInteger(Integer.parseInt(splited[i])));
            ans.getMonomials().addLast(a);
        }
        return ans;
    }
    public Polynomial add(Polynomial p)
    {
        Polynomial ans = new Polynomial();
        Iterator thisIt = this.monomials.iterator();
        Iterator pIt = p.monomials.iterator();
        while(thisIt.hasNext() && pIt.hasNext()){
            Monomial a = (Monomial) thisIt.next();
            Monomial b = (Monomial) pIt.next();
            ans.getMonomials().addLast(a.add(b));
        }
        while (thisIt.hasNext())
            ans.getMonomials().addLast((Monomial) thisIt.next());
        while (pIt.hasNext())
            ans.getMonomials().addLast((Monomial) pIt.next() );
        return  ans;
    }
    public Polynomial mul(Polynomial p)
    {
        int zeros = 0;
        Polynomial ans = new Polynomial();
        Iterator thisIt = this.monomials.iterator();
        while (thisIt.hasNext())
        {
            Iterator pIt = p.monomials.iterator();
            Polynomial holder = new Polynomial();
            Monomial a =(Monomial) thisIt.next();
            for (int i = 0; i < zeros; i++) {
                holder.monomials.addLast(new Monomial(i,new ScalarInteger(0)));
            }
            while (pIt.hasNext())
            {
                Monomial b = (Monomial) pIt.next();
                holder.monomials.addLast(a.mul(b));
            }
            ans = ans.add(holder);
            zeros++;
        }
        return  ans;

    }
    public Scalar evaluate(Scalar s)
    {
        Scalar ans = new ScalarInteger(0);
        for (Monomial monomial: monomials)
            ans = ans.add(monomial.evaluate(s));
        return ans;
    }
    public Polynomial derivative()
    {
        Polynomial ans = new Polynomial();
        Iterator thisIt = this.monomials.iterator();
        if (thisIt.hasNext()){
            Monomial temp = (Monomial) thisIt.next();
        }
        while (thisIt.hasNext()){
            ans.monomials.addLast((Monomial) ((Monomial) thisIt.next()).derivative());
        }
        return ans;
    }

    @Override
    public String toString() {
        String ans = "";
        for(Monomial m : monomials)
        {
            if (m.sign() != 0)
                ans = ans + m.toString() + " ";
        }
        return  ans.substring(0,ans.length()-1);
    }
    public LinkedList<Monomial> getMonomials() {
        return monomials;
    }

    public void setMonomials(LinkedList<Monomial> monomials) {
        this.monomials = monomials;
    }
}
