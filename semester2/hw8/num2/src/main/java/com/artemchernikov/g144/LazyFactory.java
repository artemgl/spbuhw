package com.artemchernikov.g144;

import java.util.function.Supplier;

/**A class describing tool for creating objects implementing interface Lazy*/
public class LazyFactory {

    /**
     * A method creates object implementing interface Lazy for single-threaded operations
     * @param supplier calculation based on which the object is created
     * @return object implementing interface Lazy for single-threaded operations
     * */
    public static <T> Lazy<T> createSimpleLazy(Supplier<T> supplier) {
        return new Lazy<>() {
            private T value;
            private boolean isCalculated;

            @Override
            public T get() {
                if (isCalculated) {
                    return value;
                }

                isCalculated = true;
                return value = supplier.get();
            }
        };
    }

    /**
     * A method creates object implementing interface Lazy for multi-threaded operations
     * @param supplier calculation based on which the object is created
     * @return object implementing interface Lazy for multi-threaded operations
     * */
    public static <T> Lazy<T> createMultiThreadedLazy(Supplier<T> supplier) {
        return new Lazy<>() {
            private T value;
            private boolean isCalculated;

            @Override
            public T get() {
                synchronized (this) {
                    if (isCalculated) {
                        return value;
                    }

                    isCalculated = true;
                    return value = supplier.get();
                }
            }
        };
    }

}
