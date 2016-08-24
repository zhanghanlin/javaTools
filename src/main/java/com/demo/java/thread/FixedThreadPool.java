package com.demo.java.thread;

import java.util.Random;
import java.util.concurrent.*;

import static com.demo.java.commons.Config.maxThreadNum;

/**
 * FixedThreadPool线程池
 */
public class FixedThreadPool {

    static ExecutorService executor = Executors.newFixedThreadPool(maxThreadNum);

    private static FixedThreadPool fixedThreadPool = new FixedThreadPool();

    public static FixedThreadPool getInstance() {
        return fixedThreadPool;
    }


    /**
     * 添加任务到线程池-无返回值
     *
     * @param task 任务编号
     */
    private void runFixedThreadPool(final String task) {
        executor.execute(() ->
                System.out.println("run task :" + task)
        );
    }

    /**
     * 添加任务到线程池-有返回值
     *
     * @param task 任务编号
     */
    private void run2FixedThreadPool(final String task) {
        FutureTask<String> future = new FutureTask<>(() -> {
            try {
                //模拟执行时间为随机值
                Thread.sleep(new Random().nextInt(100));
                System.out.println("run task :" + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //真正的任务在这里执行，这里的返回值类型为String，可以为任意类型
            return String.valueOf(new Random().nextInt(6000));
        });
        executor.execute(future);
        try {
            //接受任务返回，可以设置超时
            System.out.println(future.get(3, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            try {
                System.out.println("timeOut task->" + future.get());
            } catch (InterruptedException | ExecutionException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void main(String[] args) {
        FixedThreadPool pool = FixedThreadPool.getInstance();
        String task = "";//模拟任务
        for (int i = 0; i < 5; i++) {
            pool.runFixedThreadPool(i + task);
        }
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
        System.out.println("executor is shutdown ->" + executor.isShutdown());
    }
}
