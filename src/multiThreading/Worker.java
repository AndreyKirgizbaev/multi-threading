package multiThreading;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

class Worker {
    private SynchronousQueue<Runnable> queue;
    private AtomicInteger workCount;

    public Worker() {
        workCount = new AtomicInteger(0);
        queue = new SynchronousQueue<>();
    }

    public void start() {
        Thread thread = new Thread(() ->
        {
            while (true) {
                try {
                    Runnable task = queue.take();
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                workCount.decrementAndGet();
            }
        });

        thread.start();
    }

    void execute(Runnable task) throws InterruptedException {
        workCount.incrementAndGet();
        queue.put(task);
    }

    int getWorkCount() {
        return workCount.get();
    }
}
