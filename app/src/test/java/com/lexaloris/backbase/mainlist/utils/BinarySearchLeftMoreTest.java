package com.lexaloris.backbase.mainlist.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinarySearchLeftMoreTest {
    private BinarySearchLeftMore binarySearchLeftMore = new BinarySearchLeftMore();

    private ArrayList<String> createSimpleArray() {
        List<String> list = Arrays.asList("a", "b", "bb", "bb", "bb", "c", "d", "e", "fff", "fffg");
        return new ArrayList<>(list);
    }

    @Test
    public void findNothingInEmpty() {
        ArrayList<String> array = new ArrayList<>();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "a");
        int expectedValue = -1;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findNothing() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "9");
        int expectedValue = -1;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findFirstA() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "a");
        int expectedValue = 0;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findFirstB() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "b");
        int expectedValue = 1;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findFirstBB() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "bb");
        int expectedValue = 2;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findFirstC() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "c");
        int expectedValue = 5;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findFirstD() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "d");
        int expectedValue = 6;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findFirstE() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "e");
        int expectedValue = 7;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findFirstF() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "f");
        int expectedValue = 8;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findFirstFF() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "ff");
        int expectedValue = 8;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findFirstFFF() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "fff");
        int expectedValue = 8;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findFirstCaseSensitiveFFF() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "FfF");
        int expectedValue = 8;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findFirstFFFF() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchLeftMore.findIndex(array, 0, size - 1, "ffff");
        int expectedValue = -1;
        assertEquals(expectedValue, actualValue);
    }
}
