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
    private static final ArrayList<String> mainArray = new ArrayList<>();
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

    //    public boolean searchWord(String text, int charIndex, ArrayList<String> _arrayList) {
//
//        Character c1 = text.charAt(charIndex);
//        boolean _searched = false;
//        int compare;
//        int prevCompare = text.compareTo(_arrayList.get(0));
//
//        for (int i = 0; i < _arrayList.size(); i++) {
//            String word = _arrayList.get(i);
//
//            // checks if the arrayList is the same as the Main array so as not to be repeated in the recursion
//            if (_arrayList.equals(mainArray) && c1.equals(word.charAt(0))) {
//                minSearchArray.add(word);
//                minSearchIndex.add(i);
//            }
//
//            //if the current array being searched through is the minimized array minArray and the current word is the same as the text to search
//            //this condition also checks if the current word equals the search text with regards to the case.
//            if (_arrayList.equals(minSearchArray) && text.equals(word) == true) {
//
//                //gets and sets the position from minSearchIndex. This value @ i should correspond with the actual position in the Main array and in the words.txt file
//                wordPosition = minSearchIndex.get(i);
//                System.out.println(wordPosition);
//                _searched = true;
//
//            } else if (_arrayList.equals(minSearchArray) && text.equals(word) == false) {
//                //if we searching through the minnimized array and we encounter a word at which the characters @ that index do not match, remove the word and its index
//                //remove corresponding index with with word in the minarrays
//
//                compare = text.compareTo(word);
//                if (prevCompare >= compare) {
//                    if (compare <= -1) {
//                        wordPosition = minSearchIndex.get(i) - 1;
//                        System.out.println(wordPosition);
//
//                    } else if (compare >= 1) {
//                        wordPosition = minSearchIndex.get(i) + 1;
//                        System.out.println(wordPosition);
//                    }
//                    //minSearchArray.remove(i);
//                    //minSearchIndex.remove(i);
//                    prevCompare = compare;
//                }
//
//                _searched = false;
//
//            }
//
//        }
//        if (_arrayList.equals(mainArray)) {
//            charIndex = 1;
//            _searched = searchWord(text, charIndex, minSearchArray);
//
//        }
//        return _searched;
//
//    }
    public static boolean searchWord(String text, ArrayList<String> _arrayList) {

        Character c1 = text.charAt(0);
        boolean _searched = false;
        int compare;
        int prevCompare = 94;//text.compareTo(_arrayList.get(0));

        for (int i = 0; i < _arrayList.size(); i++) {
            String word = _arrayList.get(i);

            // checks if the arrayList is the same as the Main array so as not to be repeated in the recursion
            if (c1.equals(word.charAt(0))) {//_arrayList.equals(MainActivity.wordListArray) &&
                minSearchArray.add(word);
                minSearchIndex.add(i);
            }
        }
        //if the current array being searched through is the minimized array minArray and the current word is the same as the text to search
        //this condition also checks if the current word equals the search text with regards to the case.
//            if (_arrayList.equals(minSearchArray)) {
        for (int i = 0; i < minSearchArray.size(); i++) {
            String word = minSearchArray.get(i);

            if (text.compareTo(word) == 0) {
                wordPosition = minSearchIndex.get(i);
                System.out.println(wordPosition);
//                        _searched = true;
                return true;
            } else {
                compare = text.compareTo(word);
                if (prevCompare >= compare) {
                    if (compare <= -1) {
                        //text is before word. wordposition is updated to position before word
                        wordPosition = minSearchIndex.get(i) - 1;
                        System.out.println(wordPosition);

                    } else if (compare >= 1) {
                        //text is after word. wordposition is updated to position after word
                        wordPosition = minSearchIndex.get(i) + 1;
                        System.out.println(wordPosition);
                    }
                    //minSearchArray.remove(i);
                    //minSearchIndex.remove(i);
                    prevCompare = compare;
                }
                _searched = false;
            }

        }
        //gets and sets the position from minSearchIndex. This value @ i should correspond with the actual position in the Main array and in the words.txt file
//            }

//        }
//        if (_arrayList.equals(MainActivity.wordListArray)) {
////            charIndex = 1;
//            _searched = searchWord(text,  minSearchArray);
//
//        }
        minSearchArray = null;
        minSearchIndex = null;
        System.out.println(wordPosition);
        return _searched;
    }

    @Test
    public void myTest() {
//    String[] first = {"add", "asdf", "awesrt", "awd", "bad", "badd", "catt", "cter", "cbnf"};
        mainArray.add("add");
        mainArray.add("asdf");
        mainArray.add("awerst");
        mainArray.add("bad");
        mainArray.add("badd");


        mainArray.add("Catiline");
        mainArray.add("Cato");
        mainArray.add("Catskill");
        mainArray.add("cabvdn");
        //boolean searched = searchWord("Catskilll", mainArray);        assertEquals(searched, false);
        // boolean searched=delete("cabvdn",mainArray);        assertEquals(searched,true);
        // boolean searched = insert("cabvdn", mainArray);        assertEquals(searched, true);
    }

    //    @Test
    public boolean delete(String text, ArrayList<String> _arrayList) {
        searchWord(text, _arrayList);

        _arrayList.remove(wordPosition);
        //checks if the word has been deleted in the correct position by comparing the text to the word now occupying that position in the array
        return true;//_arrayList.get(wordPosition).equals(text);
    }

    //    @Test
    public boolean insert(String text, ArrayList<String> _arrayList) {
        searchWord(text, _arrayList);

        _arrayList.add(wordPosition, text);
        //checks if the word has been inserted in the correct position
        return true;//MainActivity.wordListArray.get(wordPosition).equals(text);
    }

}