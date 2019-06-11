package com.artemchernikov.g144;

import java.util.function.Supplier;

public class SimpleLazy<T> implements Lazy<T> {

    private T value;
    private Supplier<T> supplier;

    public SimpleLazy(Supplier<T> receivedSupplier) {
        supplier = new Supplier<>() {
            private boolean isCalculated;

            @Override
            public T get() {
                if (isCalculated) {
                    return value;
                }
                isCalculated = true;
                return value = receivedSupplier.get();
            }
        };
    }

    public T get() {
        return supplier.get();
    }

}
