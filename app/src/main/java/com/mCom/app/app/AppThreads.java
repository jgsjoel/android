package com.mCom.app.app;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppThreads extends Application {

    private static ExecutorService executor;
    private static ScheduledExecutorService scheduledExecutor;

    private AppThreads(){}

    public static ExecutorService getInstance(){
        if(executor == null){
            executor = Executors.newFixedThreadPool(5);
        }
        return executor;
    }

    private static ScheduledExecutorService getScheduledExecutor(){
        if(scheduledExecutor == null){
            scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        }
        return scheduledExecutor;
    }

    public static void runScheduledThread(Runnable command){
        getScheduledExecutor().schedule(command,3, TimeUnit.SECONDS);
    }

}
