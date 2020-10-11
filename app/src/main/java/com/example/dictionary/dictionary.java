package com.example.dictionary;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class dictionary {
    public static final int a = 31;
    private static final String FILE_NAME = "words.txt";
    //The word position in the list. After calculating the position for the word
    public static int wordPosition = MainActivity.arraySize();
    //a smaller version of the MainActivity.werdArrayList full of words that have matching 1st char match
    private static ArrayList<String> minSearchArray = new ArrayList<>();
    //has the index of the words from the MainActivity.werdArrayList
    private static ArrayList<Integer> minSearchIndex = new ArrayList<>();

//    public static int hashCode(String s) {
//
//        int i;
//        int r = 0;
//        char c;
//
//        for (i = s.length() - 1; i >= 0; i--) {
//            c = s.charAt(i);
//
//            r = (int) c + a * r;
//
//        }
//
//        return (r);
//    }


    //Shrink the arraylist so we can search only the words that start with the right character
    //recursively compares the char of the search word with the chars of the words in the MainActivity.wordArrayList
    public static boolean searchWord(String text, int charIndex, ArrayList<String> _arrayList) {

        char ch = text.charAt(charIndex);
        for (int i = 0; i < _arrayList.size(); i++) {
            String word = _arrayList.get(i);

            if (ch == word.charAt(charIndex)) {

                // checks if the arrayList is the same as the Main array so as not to be repeated in the recursion
                if (_arrayList.equals(MainActivity.wordListArray)) {
                    minSearchArray.add(word);
                    minSearchIndex.add(i);
                }
                //if the current array being searched through is the minimized array minArray and the current word is the same as the text to search
                //this condition also checks if the current word equals the search text with regards to the case.
                if (_arrayList.equals(minSearchArray) && word.equals(text)) {
                    //gets and sets the position from minSearchIndex. This value @ i should correspond with the actual position in the Main array and in the words.txt file
                    wordPosition = minSearchIndex.get(i);
//                    break;
                    return true;
                }
            }

        }
        ++charIndex;
        searchWord(text, charIndex, minSearchArray);
        return false;
    }


    //TODO search, insert, delete algorthms the array with the list of words to apply data structures is "MainActivity.wordListArray"
//    public static boolean searchWord(String text) {
//      /*  TODO program the searchWord algorithm here using the wordListArray as the list of words to search through.
//                   Return boolean val if word exists */
//
//      //search each char at a time recursively
//        /*1st COMPARE the first character for each word in the array with the first character of the 'text' and save the word in a new array
//        2nd using this array compare the next character in the
//        * */
//        //for (String word:MainActivity.wordListArray
//        // ) {        }
//        minSearchWord(text);
//
//
//
//    }

    public static boolean insertWord(String text) {/*  TODO program the searchWord algorithm here using the wordListArray as the list of words to search through.
                   Return boolean val if word exists */
        searchWord(text, 0, MainActivity.wordListArray);

        MainActivity.wordListArray.add(wordPosition, text);
        //checks if the word has been inserted in the correct position
        return true;//MainActivity.wordListArray.get(wordPosition).equals(text);

    }

    public static boolean deleteWord(String text) {
      /*  TODO program the deleteWord algorithm here using the wordListArray as the list of words to search through.
                   Return boolean val if word has been deleted */
        searchWord(text, 0, MainActivity.wordListArray);

        MainActivity.wordListArray.remove(wordPosition);
        //checks if the word has been deleted in the correct position by comparing the text to the word now occupying that position in the array
        return true;//!MainActivity.wordListArray.get(wordPosition).equals(text);

    }


    //Insert: Upon method call the current list is deleted and replaced with an updated arrayList
    public static void writeFile() {
        int count = 0;
        clearFile();
        for (String t : MainActivity.wordListArray) {
//For each word inserted count++

            t = t + "\n";
            count = count + 1;


            try {
                FileOutputStream fOutput;// = null;
                fOutput = new FileOutputStream(FILE_NAME, true);

                fOutput.write(t.getBytes());


                fOutput.flush();
                fOutput.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
//            finally {
//                if (fOutput != null) {
//                    try {
//                        fOutput.close();
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
        }

    }

    public static void clearFile() {
        //    Clears the txt file in the internal memory
        try {
            FileWriter fw = new FileWriter(FILE_NAME, false);

            PrintWriter printWriter = new PrintWriter(fw, false);
            printWriter.flush();
            printWriter.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}