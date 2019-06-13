package com.artemchernikov.g144;

/**An interface describing lazy calculating*/
public interface Lazy<T> {

    /**
     * A method returns result of calculating
     * @return value that is calculated by the first method call, and the same object by subsequent calls
     * */
    T get();

}
