import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;

    private final Semaphore fizzBuzzSem = new Semaphore(0);
    private final Semaphore fizzSem = new Semaphore(0);
    private final Semaphore buzzSem = new Semaphore(0);
    private final Semaphore numberSem = new Semaphore(1);
    private final AtomicInteger numberInOrder = new AtomicInteger(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 0; i < n / 3 - n / 15; i++) {
            fizzSem.acquire();
            printFizz.run();
            numberInOrder.getAndIncrement();
            openCorrectSemaphore(numberInOrder);
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 0; i < n / 5 - n / 15; i++) {
            buzzSem.acquire();
            printBuzz.run();
            numberInOrder.getAndIncrement();
            openCorrectSemaphore(numberInOrder);
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 0; i < n / 15; i++) {
            fizzBuzzSem.acquire();
            printFizzBuzz.run();
            numberInOrder.getAndIncrement();
            openCorrectSemaphore(numberInOrder);
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n - (n / 5 + n / 3) + n / 15; i++) {
            numberSem.acquire();
            printNumber.accept(numberInOrder.getAndIncrement());
            openCorrectSemaphore(numberInOrder);
        }
    }

    void openCorrectSemaphore(AtomicInteger nextNumber) {
        int number = nextNumber.get();
        if (number > n) {
            return;
        }
        if (number % 5 == 0 && number % 3 == 0) {
            fizzBuzzSem.release();
        } else if (number % 5 == 0) {
            buzzSem.release();
        } else if (number % 3 == 0) {
            fizzSem.release();
        } else {
            numberSem.release();
        }
    }
}