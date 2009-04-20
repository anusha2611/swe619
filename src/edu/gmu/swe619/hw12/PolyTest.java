/*
Name: Deepak Sumra
SWE - 619 (Spring 2009)
Assignment - 5
*/


package edu.gmu.swe619.hw12;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Deepak
 */
public class PolyTest {
   
   @Test 
   public void hasNextTest1()
   {
       final Poly p = new Poly(3,4);
       ImmutableIterator it = p.terms();       
       assertTrue(it.hasNext());         
   }
   
   public void hasNextTest2()
   {
       final Poly p = new Poly(0,0);
       ImmutableIterator it = p.terms();       
       assertTrue(it.hasNext());
       it.nextIterator();
       assertFalse(it.hasNext());
   }
   
   
   @Test
   public void nextObjectTest1()
   {
       final Poly p = new Poly(3,4); // 3 * x ^ 4       
       ImmutableIterator it = p.terms();
       
       assertEquals(4, it.nextObject());
   }
   
   @Test
   public void nextObjectTest2()
   {
       final Poly p = new Poly(3,4); // 3 * x ^ 4       
       final Poly q = new Poly(5,6);//5 * x ^ 6
       final Poly test = p.add(q);//3 * x ^ 4 + 5 * x ^ 6
       
       ImmutableIterator it = test.terms();
       
       assertEquals(4, it.nextObject());
       it = it.nextIterator();
       assertEquals(6, it.nextObject());
   }
   
   @Test
   public void nextObjectTest3()
   {
       final Poly p = new Poly(3,4); // 3 * x ^ 4       
       final Poly q = new Poly(5,6);//5 * x ^ 6
       final Poly r = new Poly(2,2);//2 * x ^ 2
       final Poly test1 = p.add(q);//3 * x ^ 4 + 5 * x ^ 6
       final Poly test2 = test1.add(r);//2 * X ^ 2 + 3 * x ^ 4 + 5 * x ^ 6
       
       ImmutableIterator it = test2.terms();
       
       assertEquals(2, it.nextObject());   
       it = it.nextIterator();
       assertEquals(4, it.nextObject());
       it = it.nextIterator();
       assertEquals(6, it.nextObject());       
   }
      
   
   @Test
   public void nextIteratorTest1()
   {
       Poly p = new Poly(3,4); // 3 * x ^ 4       
       Poly q = new Poly(2,3);//2 * x ^ 3
       p.add(q);
       ImmutableIterator it1 = p.terms();
       
       Poly r = new Poly(2,3); // 2 * x ^ 3
       ImmutableIterator it2 = r.terms();

       ImmutableIterator it3 = it1.nextIterator();

       assertNotSame(it2, it3);
   }
     
}
