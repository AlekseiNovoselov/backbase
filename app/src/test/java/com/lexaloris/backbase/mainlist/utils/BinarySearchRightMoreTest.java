package com.lexaloris.backbase.mainlist.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinarySearchRightMoreTest {
    private BinarySearchRightMore binarySearchRightMore = new BinarySearchRightMore();

    private ArrayList<String> createSimpleArray() {
        List<String> list = Arrays.asList("a", "b", "bb", "bb", "bb", "c", "d", "e", "fff", "fffg");
        return new ArrayList<>(list);
    }

    @Test
    public void findNothingInEmpty() {
        ArrayList<String> array = new ArrayList<>();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "a", size);
        int expectedValue = -1;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findSpace() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "", size);
        int expectedValue = 9;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findNothing() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "9", size);
        int expectedValue = -1;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findLastA() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "a", size);
        int expectedValue = 0;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findLastB() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "b", size);
        int expectedValue = 4;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findLastBB() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "bb", size);
        int expectedValue = 4;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findLastC() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "c", size);
        int expectedValue = 5;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findLastD() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "d", size);
        int expectedValue = 6;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findLastE() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "e", size);
        int expectedValue = 7;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findLastF() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "f", size);
        int expectedValue = 9;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findLastFF() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "ff", size);
        int expectedValue = 9;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findLastFFF() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "fff", size);
        int expectedValue = 9;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void findLastFFFF() {
        ArrayList<String> array = createSimpleArray();
        int size = array.size();
        int actualValue = binarySearchRightMore.findIndex(array, 0, size - 1, "ffff", size);
        int expectedValue = -1;
        assertEquals(expectedValue, actualValue);
    }

}
