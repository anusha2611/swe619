package edu.gmu.swe619.hw8;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {

    protected BlockingQueue<Integer> queue;
    protected int n;

    public Consumer(BlockingQueue<Integer> queue, int n) {
        this.queue = queue;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < n; i++) {

            try {
                Object obj = queue.take();
                if (obj != null)
                    System.out.println("\tconsume: "+obj);
                sleep((int)(Math.random() * 400));
            }  catch (InterruptedException e) {}
        }
    }
}
