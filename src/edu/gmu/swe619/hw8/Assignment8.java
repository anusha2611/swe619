package edu.gmu.swe619.hw8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Assignment8 {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(20);
		Producer p = new Producer(bq, 10);
		Consumer c1 = new Consumer(bq, 10);
	    
		// Using a ExecutorService to run the threads
	    ExecutorService pool = Executors.newCachedThreadPool();
	    pool.submit(p);
        pool.submit(c1);
        
        // Set up a synchronizer
        final CountDownLatch ready = new CountDownLatch(2);
        final CountDownLatch start = new CountDownLatch(2);
        final CountDownLatch done = new CountDownLatch(2);
        
        ready.await();
        long startNanos = System.nanoTime();
        System.out.println("Start time: " + startNanos);

        start.countDown();
        done.await();
        System.out.println(System.nanoTime() - startNanos);
        
        pool.shutdown();
	}
}
