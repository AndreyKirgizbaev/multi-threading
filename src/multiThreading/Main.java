package multiThreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) throws InterruptedException {

        List<Worker> workers = new ArrayList<>();

        System.out.println("Enter number of workers");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfWorkers = 5;
        try {
            String StringNumberOfWorkers = reader.readLine();
            numberOfWorkers = Integer.parseInt(StringNumberOfWorkers);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < numberOfWorkers; ++i) {
            Worker worker = new Worker();
            worker.start();
            workers.add(worker);
        }

        WorkBalancer balancer = new WorkBalancer(workers);

        for (int i = 0; i < 50; ++i) {
            balancer.execute(() -> {
                try {
                    DoWork();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    static void DoWork() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName() + " said \"I did\"");
    }
}
