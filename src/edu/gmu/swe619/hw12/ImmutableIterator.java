package edu.gmu.swe619.hw12;

/*
Name: Deepak Sumra
SWE - 619 (Spring 2009)
Assignment - 5
*/

//package infs619assignment5;

/**
 *
 * @author Deepak
 */
public interface  ImmutableIterator {
    
     //Effects: Returns true if there are more elements to yield 
     //else retruns false
     public boolean hasNext();
     
     //Effects: If there are more results to yield, returns next result 
     //otherwise, throws NoSuchElementException
     public Object nextObject();
     
     //Effects: If there are more results to yield, returns next Iterator
     //otherwise, throws NoSuchElementException
     public ImmutableIterator nextIterator();

}
