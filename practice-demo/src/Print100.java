import java.util.concurrent.TimeUnit;

/**
 * 如何用 wait() 实现两个线程交替打印 0 ~ 100 的奇偶数？
 */
public class Print100 {

    private static int count;
    private final static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        OodEveRunnable oodEveRunnable = new OodEveRunnable();

        new Thread(oodEveRunnable, "偶数").start();
        new Thread(oodEveRunnable, "奇数").start();



    }


    static class OodEveRunnable implements Runnable {

        @Override
        public void run() {

            while (count <= 100) {

                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "：" + count);
                    count++;



                    if (count < 100) {
                        lock.notify();
                        try {
                            // wait，释放锁
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

}
