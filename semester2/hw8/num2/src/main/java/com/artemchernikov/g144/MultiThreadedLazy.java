package com.artemchernikov.g144;

import java.util.function.Supplier;

/**A class describing multi threaded lazy calculating*/
public class MultiThreadedLazy<T> implements Lazy<T> {

    volatile private T value;
    volatile private Supplier<T> supplier;

    public MultiThreadedLazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    /**{@inheritDoc}*/
    @Override
    public T get() {
        synchronized (this) {
            if (supplier != null) {
                value = supplier.get();
                supplier = null;
            }
            return value;
        }
    }

}