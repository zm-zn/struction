/**
 * Create by zm on 2019/11/16 14:53
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


// 固定线程池，一开始是就申请好线程
public class ThreadPool1 {
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);
    private Thread[] workers = new Thread[5];


    ThreadPool1() {
        for (int i = 0; i < 5; i++) {
            workers[i] = new Worker(workQueue);
            workers[i].start();
        }
    }


    public void execute(Runnable cmd) throws InterruptedException {
        workQueue.put(cmd);
    }

     public void stop(){

     }

    private static class Worker extends Thread {
        private BlockingQueue<Runnable> workQueue;


        Worker(BlockingQueue<Runnable> queue) {
            workQueue = queue;
        }


        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Runnable cmd = workQueue.take();
                    cmd.run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadPool1 pool = new ThreadPool1();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("第一个事情");
                }
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("第二个时期");
                }
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("第三 个时期");
                }
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("第四个时期");
                }


            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("第五个时期");
                }
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("第流个时期");
                }
            }
        });
    }
}