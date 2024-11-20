import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class dining {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of philosophers: ");
        final int numPhilosophers = scanner.nextInt();
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Object[] forks = new Object[numPhilosophers];
        AtomicInteger finishedPhilosophers = new AtomicInteger(0);

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if (i % 2 == 0) {
                philosophers[i] = new Philosopher(leftFork, rightFork, finishedPhilosophers, numPhilosophers);
            } else {
                philosophers[i] = new Philosopher(rightFork, leftFork, finishedPhilosophers, numPhilosophers);
            }

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}

class Philosopher implements Runnable {
    private final Object leftFork;
    private final Object rightFork;
    private final AtomicInteger finishedPhilosophers;
    private static volatile boolean allFinished = false;
    private final int totalPhilosophers;
    private boolean hasEaten = false;

    public Philosopher(Object leftFork, Object rightFork, AtomicInteger finishedPhilosophers, int totalPhilosophers) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.finishedPhilosophers = finishedPhilosophers;
        this.totalPhilosophers = totalPhilosophers;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep((int) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            while (!allFinished) {
                doAction(System.nanoTime() + ": Thinking");
                synchronized (leftFork) {
                    doAction(System.nanoTime() + ": Picked up left fork");
                    synchronized (rightFork) {
                        doAction(System.nanoTime() + ": Picked up right fork - eating");
                        if (!hasEaten) {
                            hasEaten = true;
                            int count = finishedPhilosophers.incrementAndGet();
                            if (count == totalPhilosophers) {
                                allFinished = true;
                            }
                        }
                        doAction(System.nanoTime() + ": Put down right fork");
                    }
                    doAction(System.nanoTime() + ": Put down left fork. Back to thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
