package com.artemchernikov.g144;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**An interface describing serializable essence*/
public interface Serializable {

    /**
     * A method serializes essence
     * @param out - output stream to write serialized essence in it
     * @throws IOException if writing was unsuccessful
     * */
    void serialize(OutputStream out) throws IOException;

    /**
     * A method deserializes essence
     * @param in - input stream to create essence by it
     * @throws IOException if reading was unsuccessful
     * */
    void deserialize(InputStream in) throws IOException;

}
