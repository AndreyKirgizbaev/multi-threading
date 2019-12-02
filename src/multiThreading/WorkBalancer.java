package multiThreading;

import java.util.Comparator;
import java.util.List;

class WorkBalancer {
    private List<Worker> workers;

    public WorkBalancer(List<Worker> workers) {
        this.workers = workers;
    }

    void execute(Runnable task) throws InterruptedException {
        Worker minWorker = workers.stream().min(Comparator.comparingInt(Worker::getWorkCount)).get();
        minWorker.execute(task);
    }
}
