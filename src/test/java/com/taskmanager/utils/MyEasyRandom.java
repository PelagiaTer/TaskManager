package com.taskmanager.utils;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MyEasyRandom {

    private static final EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters()
            .collectionSizeRange(3, 10)
            .objectPoolSize(50_000)
    );

    public static EasyRandom getInstance() {
        return easyRandom;
    }

    public static <T> T nextObject(final Class<T> type) {
        return easyRandom.nextObject(type);
    }

    public static double nextDouble() {
        return easyRandom.nextDouble();
    }
}
