package com.task1.scheduler.item;

public interface ScheduledItem {

    void process();

    boolean isProcessed();

    void markProcessed();
}

