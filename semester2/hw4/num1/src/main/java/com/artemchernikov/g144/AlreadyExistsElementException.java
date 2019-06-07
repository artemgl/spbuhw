package com.artemchernikov.g144;

/**A class describing exception that is thrown when such element already exists*/
class AlreadyExistsElementException extends IllegalArgumentException {
    AlreadyExistsElementException(String message) {
        super(message);
    }
}