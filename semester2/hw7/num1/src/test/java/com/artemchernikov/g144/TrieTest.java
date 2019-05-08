package com.artemchernikov.g144;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void add() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("three");
        trie.add("four");
        trie.add("five");

        assertEquals(5, trie.size());
    }

    @Test
    void shouldNotAddExistentElement() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("three");
        trie.add("four");
        trie.add("five");

        assertFalse(trie.add("two"));
        assertEquals(5, trie.size());
    }

    @Test
    void contains() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("three");
        trie.add("four");
        trie.add("five");

        assertTrue(trie.contains("three"));
        assertFalse(trie.contains("seven"));
    }

    @Test
    void remove() {
        Trie trie = new Trie();
        trie.add("abe");
        trie.add("abfh");
        trie.add("abdg");
        trie.add("ac");
        trie.add("abd");
        trie.add("abf");
        trie.add("abfa");

        trie.remove("abdg");
        assertTrue(trie.contains("abd"));

        trie.remove("abf");
        assertTrue(trie.contains("abfa"));
        assertTrue(trie.contains("abfh"));

        trie.remove("abfa");
        assertTrue(trie.contains("abfh"));

        assertEquals(4, trie.size());
    }

    @Test
    void shouldNotRemoveNonexistentElement() {
        Trie trie = new Trie();
        trie.add("one");
        trie.add("two");
        trie.add("three");
        trie.add("four");
        trie.add("five");

        assertFalse(trie.remove("ten"));
        assertEquals(5, trie.size());
    }

    @Test
    void howManyStartWithPrefix() {
        Trie trie = new Trie();
        trie.add("Book");
        trie.add("Image");
        trie.add("Imagination");
        trie.add("Immune");
        trie.add("Inch");

        assertEquals(0, trie.howManyStartWithPrefix("Booklet"));
        assertEquals(1, trie.howManyStartWithPrefix("Book"));
        assertEquals(2, trie.howManyStartWithPrefix("Imag"));
        assertEquals(3, trie.howManyStartWithPrefix("Im"));
        assertEquals(4, trie.howManyStartWithPrefix("I"));
        assertEquals(5, trie.howManyStartWithPrefix(""));
    }

    @Test
    void serializeAndDeserialize() throws IOException {
        Trie trie = new Trie();
        trie.add("abg");
        trie.add("abgh");
        trie.add("abghj");
        trie.add("abghi");
        trie.add("abghk");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        trie.serialize(out);

        trie.add("gjq");
        trie.remove("abghj");

        ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());
        trie.deserialize(inputStream);

        assertFalse(trie.contains("gjq"));
        assertTrue(trie.contains("abghj"));
    }

}