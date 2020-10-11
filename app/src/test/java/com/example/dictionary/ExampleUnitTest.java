package com.example.dictionary;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public static int wordPosition;
    private static ArrayList<String> mainArray = new ArrayList<>();
    private static ArrayList<String> minSearchArray = new ArrayList<>();
    //has the index of the words from the MainActivity.werdArrayList
    private static ArrayList<Integer> minSearchIndex = new ArrayList<>();

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

    @Test
    public void myTest() {
//    String[] first = {"add", "asdf", "awesrt", "awd", "bad", "badd", "catt", "cter", "cbnf"};
        mainArray.add("add");
        mainArray.add("asdf");
        mainArray.add("awerst");
        mainArray.add("bad");
        mainArray.add("badd");
        mainArray.add("catt");
        mainArray.add("cter");
        mainArray.add("cbvn");
        mainArray.add("cabvdn");
        //boolean searched=searchWord("catt",0,mainArray);assertEquals(searched,false);
        // boolean searched=delete("cabvdn",mainArray);        assertEquals(searched,true);
        boolean searched = insert("cabvdn", mainArray);
        assertEquals(searched, true);
    }

    public boolean searchWord(String text, int charIndex, ArrayList<String> _arrayList) {
        char ch = text.charAt(charIndex);
        for (int i = 0; i < _arrayList.size(); i++) {
            String word = _arrayList.get(i);

            if (ch == word.charAt(charIndex)) {

                // checks if the arrayList is the same as the Main array so as not to be repeated in the recursion
                if (_arrayList.equals(mainArray)) {
                    minSearchArray.add(word);
                    minSearchIndex.add(i);
                }
                //if the current array being searched through is the minimized array minArray and the current word is the same as the text to search
                //this condition also checks if the current word equals the search text with regards to the case.
                if (_arrayList.equals(minSearchArray) && word.equals(text)) {
                    //gets and sets the position from minSearchIndex. This value @ i should correspond with the actual position in the Main array and in the words.txt file
                    wordPosition = minSearchIndex.get(i);
                    System.out.println(minSearchIndex.get(i));
//                    break;
                    return true;
                }
            }

        }
        ++charIndex;
        searchWord(text, charIndex, minSearchArray);
        return false;
    }


    //    @Test
    public boolean delete(String text, ArrayList<String> _arrayList) {
        searchWord(text, 0, _arrayList);

        _arrayList.remove(wordPosition);
        //checks if the word has been deleted in the correct position by comparing the text to the word now occupying that position in the array
        return true;//_arrayList.get(wordPosition).equals(text);
    }

    //    @Test
    public boolean insert(String text, ArrayList<String> _arrayList) {
        searchWord(text, 0, _arrayList);

        _arrayList.add(wordPosition, text);
        //checks if the word has been inserted in the correct position
        return true;//MainActivity.wordListArray.get(wordPosition).equals(text);
    }

}