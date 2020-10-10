package com.example.dictionary;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void minarray() {
        String[] first = {"add", "asdf", "awesrt", "awd", "bad", "badd", "catt", "cter", "cbnf"};
        String[] second = {"asdf", "adf", "asdf"};
        String[] compare = {"catt", "cter", "cbnf"};

        char ch = 'c';
        int length = 3;
        for (String text : first) {
            if (ch == text.charAt(0)) {
                second[second.length - length--] = text;
            }
        }

        assertArrayEquals(second, compare);
    }
}