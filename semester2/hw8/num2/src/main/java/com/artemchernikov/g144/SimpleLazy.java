package com.artemchernikov.g144;

import java.util.function.Supplier;

/**A class describing singly threaded lazy calculating*/
public class SimpleLazy<T> implements Lazy<T> {

    private T value;
    private Supplier<T> supplier;

    public SimpleLazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    /**{@inheritDoc}*/
    @Override
    public T get() {
        if (supplier != null) {
            value = supplier.get();
            supplier = null;
        }
        return value;
    }

}
