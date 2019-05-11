package com.artemchernikov.g144;

/**A class describing exception that is thrown when such element doesn't exist*/
class NoElementException extends IllegalArgumentException {
    NoElementException(String message) {
        super(message);
    }
}