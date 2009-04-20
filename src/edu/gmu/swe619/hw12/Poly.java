/*
Name: Deepak Sumra
SWE - 619 (Spring 2009)
Assignment - 5
*/
package edu.gmu.swe619.hw12;

import java.util.NoSuchElementException;

/**
 *
 * @author Deepak
 */
public class Poly {

    private int[] trms;
    private int deg;
    
    
    // Effects: Initializes this to be the zero polynomial
    public Poly() {
       trms = new int[1]; trms[0] = 0;
       deg = 0;
    }

    // Effects: If n < 0 throws IllegalArgumentException
    // else initializes this to be the polynomial c*x^n
    public Poly(int c, int n) throws IllegalArgumentException {
       if (n < 0) {
          throw new IllegalArgumentException("Poly(int, int) constructor");
       }
       if (c == 0) {
          trms = new int[1]; trms[0] = 0;
          deg = 0;
          return;
       }
       trms = new int[n+1];
       for (int i=0; i < n; i++) {
          trms[i] = 0;
       }
       trms[n] = c;
       deg = n;
    }

    private Poly (int n) {
       trms = new int[n+1];
       deg = n;
    }

    // Effects: returns the degree of this
    public int degree() {
       return deg;
    }

    // Effects: returns the coefficent of the term of this whose exponent is d
    public int coef(int d) {
       return (d < 0 || d > deg) ? 0 : trms[d];
    }

    // Effects: If q is null throw NullPointerException
    // else return the Poly this - q
    public Poly sub(Poly q) throws NullPointerException {
       return add(q.minus());
    }

    // Effects: return the Poly -this
    public Poly minus() {
       Poly r = new Poly(deg);
       for (int i=0; i <= deg; i++) {
          r.trms[i] = -trms[i];
       }
       return r;
    }

    // Effects: If q is null throw NullPointerException
    // else return the Poly this + q
    public Poly add(Poly q) throws NullPointerException {
       Poly la, sm;
       if (deg > q.deg) {
          la = this; sm = q;
       }
       else {
          la = q; sm = this;
       }
       int newdeg = la.deg;
       if (deg == q.deg) {
          for (int k = deg; k > 0; k--) {
             if (trms[k] + q.trms[k] != 0) {
                break;
             }
             else {
                newdeg--;
             }
          }
       }
       Poly r = new Poly(newdeg);
       int i;
       for (i = 0; i <= sm.deg && i <= newdeg; i++) {
           r.trms[i] = sm.trms[i] + la.trms[i];
       }
       for (int j = i; j <= newdeg; j++) {
          r.trms[j] = la.trms[j];
       }
       return r;
    }

    // Effects: If q is null throw NullPointerException
    // else return the Poly this * q
    public Poly mul(Poly q) throws NullPointerException {

       if ((q.deg == 0 && q.trms[0] == 0) || (deg == 0 && trms[0] == 0)) {
          return new Poly();
       }

       Poly r = new Poly(deg + q.deg);
       r.trms[deg + q.deg] = 0;
       for (int i = 0; i <= deg; i++) {
          for (int j = 0; j <= q.deg; j++) {
             r.trms[i+j] += trms[i]*q.trms[j];
          }
       }
       return r;
    }

    public String toString() {
       String r = "Poly:";

       if (deg == 0 || trms[0] != 0) {
           r += " " + trms[0];
       }

       for (int i = 1; i <= deg; i++) {
          if (trms[i] < 0) {
             r += " - " + -trms[i] + "x^" + i;
          }
          else if (trms[i] > 0) {
             r += " + " +  trms[i] + "x^" + i;
          }
       }
       return r;
    }

                              
    //Effects: Retruns a generator that will produce exponents
    //of nonzero terms of this (as Integers) up to the degree,
    //in order of increasing exponent
    //Requires: call nextIterator() before calling nextObject()
    //to produce next exponent of nonzero terms

    public ImmutableIterator terms()
    {
        return new PolyGen(this, 0);
    }

    //inner class
    //abstraction function for PolyGen
    //AF(c) = [x1,...,xn] such that
    //each xi is an integer and
    //every index i>=n of a nonzero element of c.p.trms is in
    //the sequence and no other elements are in the sequence
    //and xi>xj for all i>j>=1.

    //representation invariant
    //c.p != null && (0 <= c.n <= c.p.deg)

    private static class PolyGen implements ImmutableIterator{
        private Poly p; //the Poly being iterated
        private int n ; //the next term to condsider        
        
        PolyGen (Poly it, int n){
            //Requires it != null
            p = it;
            this.n = n;

        }

        //Effects: Returns true if there are more elements to yield 
        //else retruns false
        public boolean hasNext(){return n <= p.deg;}

        
        //Effects: If there are more results to yield, returns next result 
     //otherwise, throws NoSuchElementException                        
        public Object nextObject() throws NoSuchElementException{
            
            for(int e = n; e<=p.deg ; e++)
            {
                if(p.trms[e]!=0)
                {                    
                    return new Integer(e);
                }

            }

            throw new NoSuchElementException("PolyGen.nextObject");
        }

        //Effects: If there are more results to yield, returns next Iterator
        //otherwise, throws NoSuchElementException
        public ImmutableIterator nextIterator() throws NoSuchElementException{
            
            for(int k = n; k<=p.deg; k++)
            {
                if(p.trms[k]!=0)
                {
                    return new PolyGen(p, k+1);                                  
                }
            }
           
           throw new NoSuchElementException("PolyGen.nextObject");

        }
                        
    }//end PolyGen


    public static void main(String[] args)
    {
        Poly test = new Poly(2,3);
        //test.toString();
        Poly q = new Poly(10,5);
        Poly t = new Poly(2,2);
        Poly sum = test.add(q);
        Poly sum1 = sum.add(t);
        System.out.println(test.toString());
        System.out.println(q.toString());
        System.out.println(sum.toString());
        System.out.println(sum1.toString());

        ImmutableIterator g = sum1.terms();
        while(g.hasNext())
        {
            Object p = g.nextObject();
            Integer I = (Integer)p;
            System.out.println(I);
            g = g.nextIterator();
            
        }
        
               
    }

}
