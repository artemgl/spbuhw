package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class LazyTest {

    @Test
    void getTest() {
        Supplier<Integer> supplierInt = () -> 5;
        getTestForCertain(LazyFactory.createSimpleLazy(supplierInt), supplierInt);
        getTestForCertain(LazyFactory.createMultiThreadedLazy(supplierInt), supplierInt);

        Supplier<String> supplierLine = () -> "Test";
        getTestForCertain(LazyFactory.createSimpleLazy(supplierLine), supplierLine);
        getTestForCertain(LazyFactory.createMultiThreadedLazy(supplierLine), supplierLine);
    }

    @Test
    void shouldCalculateOnce() {
        AtomicInteger countOfCalculating = new AtomicInteger();
        Supplier<Integer> lazyCalculating = () -> {
            countOfCalculating.getAndIncrement();
            return 0;
        };

        Lazy<Integer> lazy = LazyFactory.createSimpleLazy(lazyCalculating);
        lazy.get();
        lazy.get();
        lazy.get();
        lazy.get();
        assertEquals(1, countOfCalculating.get());

        countOfCalculating.set(0);
        lazy = LazyFactory.createMultiThreadedLazy(lazyCalculating);
        lazy.get();
        lazy.get();
        lazy.get();
        lazy.get();
        assertEquals(1, countOfCalculating.get());
    }

    @Test
    void shouldProcessReturningNull() {
        Supplier<Integer> supplier = () -> null;
        getTestForCertain(LazyFactory.createSimpleLazy(supplier), supplier);
        getTestForCertain(LazyFactory.createMultiThreadedLazy(supplier), supplier);
    }

    @Test
    void shouldThrowNullPointerException() {
        Supplier<Integer> supplier = null;
        assertThrows(NullPointerException.class, () -> getTestForCertain(LazyFactory.createSimpleLazy(supplier), supplier));
        assertThrows(NullPointerException.class, () -> getTestForCertain(LazyFactory.createMultiThreadedLazy(supplier), supplier));
    }

    @Test
    void shouldNotBeginRaces() throws InterruptedException {
        AtomicInteger countOfCalculating = new AtomicInteger();
        Supplier<Integer> lazyCalculating = () -> {
            countOfCalculating.getAndIncrement();
            return 0;
        };
        Lazy<Integer> lazy = LazyFactory.createMultiThreadedLazy(lazyCalculating);

        int numberOfThreads = 100;
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    lazy.get();
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(1, countOfCalculating.get());
    }

    void getTestForCertain(Lazy lazy, Supplier supplier) {
        final int numberOfChecks = 4;
        for (int i = 0; i < numberOfChecks; i++) {
            assertEquals(supplier.get(), lazy.get());
        }
    }

}