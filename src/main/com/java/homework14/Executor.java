package main.com.java.homework14;

import main.com.java.homework14.semaphore.SemaphoreImpl;

public class Executor  {
    public static void run() {
        SemaphoreImpl userZone = new SemaphoreImpl(6);
        Runnable userAction = () -> {
            try {
                userZone.acquire();
                System.out.println("Use acton\n>>>>>>>>>>>>>>>>>>>");

                Thread.sleep(3000,250);
                System.out.println("Use not action\n>>>>>>>>>>>>>>>>>>>");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userZone.release();
        };
        for (int i = 0; i < 10; i++) {
            new Thread(userAction).start();
        }
    }
}
