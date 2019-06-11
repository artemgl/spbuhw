package com.artemchernikov.g144;

import java.util.function.Supplier;

public class MultiThreadedLazy<T> implements Lazy<T> {

    volatile private T value;
    volatile private Supplier<T> supplier;

    public MultiThreadedLazy(Supplier<T> receivedSupplier) {
        supplier = new Supplier<>() {
            private boolean isCalculated;

            @Override
            public T get() {
                synchronized (this) {
                    if (isCalculated) {
                        return value;
                    }
                    isCalculated = true;
                    return value = receivedSupplier.get();
                }
            }
        };
    }

    public T get() {
        return supplier.get();
    }

}