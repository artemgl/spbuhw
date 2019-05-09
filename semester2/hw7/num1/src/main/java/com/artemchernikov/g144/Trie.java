package com.artemchernikov.g144;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**A class describing trie*/
public class Trie implements Serializable {

    private Vertex root;

    public Trie() {
        root = new Vertex();
    }

    /**
     * A method adds element to the trie
     * @param element to add
     * @return true if received element didn't exist in the trie and false otherwise
     * */
    public boolean add(String element) {
        if (contains(element)) {
            return false;
        }

        Vertex lastPossibleVertex = root;
        for (char character : element.toCharArray()) {
            Vertex nextVertex = lastPossibleVertex.next.get(character);

            lastPossibleVertex.subsequentTerminals++;

            if (nextVertex != null) {
                lastPossibleVertex = nextVertex;
            } else {
                lastPossibleVertex.next.put(character, new Vertex());
                lastPossibleVertex = lastPossibleVertex.next.get(character);
            }
        }
        lastPossibleVertex.subsequentTerminals++;

        return lastPossibleVertex.isTerminal = true;
    }

    /**
     * A method checks if element exists in the trie
     * @param element to check
     * @return true if the trie contains received element and false otherwise
     * */
    public boolean contains(String element) {
        Vertex lastPossibleVertex = root;
        for (char character : element.toCharArray()) {
            lastPossibleVertex = lastPossibleVertex.next.get(character);

            if (lastPossibleVertex == null) {
                return false;
            }
        }

        return lastPossibleVertex.isTerminal;
    }

    /**
     * A method removes elements from the trie
     * @param element to remove
     * @return true if received element existed in the trie and false otherwise
     * */
    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        }

        Vertex lastPossibleVertex = root;
        Vertex lastMeaningfulVertex = root;
        char characterAtLastMeaningfulVertex = element.charAt(0);
        for (char character : element.toCharArray()) {
            lastPossibleVertex.subsequentTerminals--;

            if (lastPossibleVertex.next.size() > 1 || lastPossibleVertex.isTerminal) {
                lastMeaningfulVertex = lastPossibleVertex;
                characterAtLastMeaningfulVertex = character;
            }

            lastPossibleVertex = lastPossibleVertex.next.get(character);
        }
        lastPossibleVertex.subsequentTerminals--;

        if (lastPossibleVertex.next.isEmpty()) {
            lastMeaningfulVertex.next.remove(characterAtLastMeaningfulVertex, lastMeaningfulVertex.next.get(characterAtLastMeaningfulVertex));
        } else {
            lastPossibleVertex.isTerminal = false;
        }

        return true;
    }

    public int size() {
        return root.subsequentTerminals;
    }

    /**
     * A method returns number of strings from the trie which start with received prefix
     * @param prefix
     * @return number of strings
     * */
    public int howManyStartWithPrefix(String prefix) {
        Vertex lastPossibleVertex = root;
        for (char character : prefix.toCharArray()) {
            Vertex nextVertex = lastPossibleVertex.next.get(character);

            if (nextVertex != null) {
                lastPossibleVertex = nextVertex;
            } else {
                return 0;
            }
        }

        return lastPossibleVertex.subsequentTerminals;
    }

    /**An auxiliary method writes vertex recursively*/
    private void writeVertex(Vertex vertex, BufferedWriter writer) throws IOException {
        writer.write(vertex.isTerminal + "\n");
        writer.write(vertex.subsequentTerminals + "\n");
        writer.write(vertex.next.size() + "\n");

        for (Map.Entry<Character, Vertex> entry : vertex.next.entrySet()) {
            writer.write(entry.getKey() + "\n");

            writeVertex(entry.getValue(), writer);
        }
    }

    /**
     * A method serializes the trie
     * @param out - output stream to write serialized trie in it
     * @throws IOException if writing was unsuccessful
     * */
    @Override
    public void serialize(OutputStream out) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writeVertex(root, writer);
        writer.close();
    }

    /**An auxiliary method reads vertex recursively*/
    private Vertex readVertex(Vertex vertex, BufferedReader reader) throws IOException {
        vertex.isTerminal = Boolean.parseBoolean(reader.readLine());
        vertex.subsequentTerminals = Integer.parseInt(reader.readLine());

        int size = Integer.parseInt(reader.readLine());
        for (int i = 0; i < size; i++) {
            vertex.next.put(reader.readLine().charAt(0), readVertex(new Vertex(), reader));
        }

        return vertex;
    }

    /**
     * A method deserializes the trie
     * Structure of the trie before deserialization will be erased
     * @param in - input stream to build the trie by it
     * @throws IOException if reading was unsuccessful
     * */
    @Override
    public void deserialize(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        root = readVertex(new Vertex(), reader);
        reader.close();
    }

    /**A class describing vertex of the trie*/
    private class Vertex {
        private Map<Character, Vertex> next;
        private boolean isTerminal;
        private int subsequentTerminals;

        private Vertex() {
            next = new HashMap<>();
            isTerminal = false;
            subsequentTerminals = 0;
        }
    }

}
