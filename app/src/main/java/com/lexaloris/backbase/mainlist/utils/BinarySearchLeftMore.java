package com.lexaloris.backbase.mainlist.utils;

import java.util.ArrayList;

class BinarySearchLeftMore {

    int findIndex(ArrayList<String> array, int left, int right, String prefix) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            boolean doesStringStartWithPrefix = doesStringStartWithPrefix(array.get(mid), prefix);
            if ((mid == 0 || isStringLessThanPrefix(array.get(mid - 1), prefix)) && doesStringStartWithPrefix) {
                return mid;
            } else if (isStringLessThanPrefix(array.get(mid), prefix)) {
                return findIndex(array, (mid + 1), right, prefix);
            } else {
                return findIndex(array, left, (mid - 1), prefix);
            }
        }
        return -1;
    }

    private boolean isStringLessThanPrefix(String string, String prefix) {
        return prefix.compareToIgnoreCase(string) > 0;
    }

    private boolean doesStringStartWithPrefix(String string, String prefix) {
        return string.startsWith(prefix);
    }
}
