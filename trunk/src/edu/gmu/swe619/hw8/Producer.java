package edu.gmu.swe619.hw8;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {

    protected BlockingQueue<Integer> queue;
    protected int n;

    public Producer(BlockingQueue<Integer> queue, int n) {
        this.queue = queue;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < n; i++) {
            try {
            	queue.put(new Integer(i));
                System.out.println("produce: " + i);
                sleep((int)(Math.random() * 100));
            }  catch (InterruptedException e) {}
        }
    }
}
