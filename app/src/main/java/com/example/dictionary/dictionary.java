package com.example.dictionary;

import java.io.File;
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
    //a smaller version of the [MainActivity.wordArrayList] full of words that have matching 1st char match
    public static ArrayList<String> minSearchArray = new ArrayList<>();
    //has the index of the words from the [MainActivity.wordArrayList]
    public static ArrayList<Integer> minSearchIndex = new ArrayList<>();


    //    Shrink the arraylist so we can search only the words that start with the right character
//    recursively compares the char of the search word with the chars of the words in the MainActivity.wordArrayList
//    public static boolean searchWord(String text, int charIndex, ArrayList<String> _arrayList) {
//
//        char ch = text.charAt(charIndex);
//        for (int i = 0; i < _arrayList.size(); i++) {
//            String word = _arrayList.get(i);
//
//                // checks if the arrayList is the same as the Main array so as not to be repeated in the recursion
//                if (_arrayList.equals(MainActivity.wordListArray)&&ch == word.charAt(charIndex)) {
//                    minSearchArray.add(word);
//                    minSearchIndex.add(i);
//                }
//                //if the current array being searched through is the minimized array minArray and the current word is the same as the text to search
//                //this condition also checks if the current word equals the search text with regards to the case.
//                if (_arrayList.equals(minSearchArray) && word.equals(text)) {
//                    //gets and sets the position from minSearchIndex. This value @ i should correspond with the actual position in the Main array and in the words.txt file
//                    wordPosition = minSearchIndex.get(i);
////                    break;
//                    return true;
//                }else if (_arrayList.equals(minSearchArray) && !word.equals(text)){
////                    wordPosition=i;//minSearchIndex.indexOf();
//                    if(word.compareTo(text)>0){
//                        wordPosition = minSearchIndex.get(i) -1;return false;
//                    }else if (word.compareTo(text)<0){wordPosition = minSearchIndex.get(i) + 1;return false;}
//                    return false;}
//            }
//
//        ++charIndex;
//        return searchWord(text, charIndex, minSearchArray);
//
//    }
    public static boolean searchWord(String text, ArrayList<String> _arrayList) {

        Character c1 = text.charAt(0);
        boolean _searched = false;
        int compare;
        int prevCompare = 94;
        int nxtCompare = 94;
        String word;
        for (int i = 0; i < _arrayList.size(); i++) {
            word = _arrayList.get(i);

            // checks if the arrayList is the same as the Main array so as not to be repeated in the recursion
            if (c1.equals(word.charAt(0))) {
                minSearchArray.add(word);
                minSearchIndex.add(i);
            }
        }

        /*For each word in [minSearchArray], the word is compared with text.
        If the comparison between [text] and [word] results 0, the [wordPosition] is updated to that current position and the method exits.
        If the comparison between [text] and [word] results to a value < -1/ > 1 the [wordPosition] is updated to a value before or after its current value .*/
        for (int i = 0; i < minSearchArray.size(); i++) {
            word = minSearchArray.get(i);

            if (text.equals(word)) {
                wordPosition = minSearchIndex.get(i);
                minSearchArray.clear();
                minSearchIndex.clear();
                return true;
            } else if (!text.equals(word)) {
                compare = text.compareTo(word);

                /*TODO Check the text and word characters to determine where the right [wordPosition] should be
                 *  Error: Occurs when attempt to delete word that has been inserted correctly on list but not in array */
                //Double checks if hte word we compared is really the word we are searching for
                if (compare == 1 || compare == -1) {
//                    ++wordPosition;
                    if (word.contains(text)) {
//                        ++wordPosition;
                        break;
                    }
                } else if (prevCompare > compare) {
                    if (compare <= -1) {
                        //text is before word. [wordPosition] is updated to position before word
                        wordPosition = minSearchIndex.get(i) - 1;
                    } else if (compare >= 1) {
                        //text is after word. [wordPosition] is updated to position after word
                        wordPosition = minSearchIndex.get(i) + 1;
                    }
                    prevCompare = compare;
                }

            }
        }

        minSearchArray.clear();
        minSearchIndex.clear();
        //System.out.println(wordPosition);
        return false;
    }

    public static void insertWord(String text) {
        //searches the word to obtain a [wordPosition] for appending the [wordListArray]
        searchWord(text, MainActivity.wordListArray);
        MainActivity.wordListArray.add(wordPosition, text);
    }

    public static void deleteWord(int pos) {
        wordPosition = pos;
        MainActivity.wordListArray.remove(wordPosition);
        //Trims hte list to reduce list storage
        MainActivity.wordListArray.trimToSize();
    }

    //Insert: Upon method call, the current list is deleted and replaced with an updated arrayList
    public static void writeFile(File file) {
        int count = 0;
        clearFile(file);

        //For each word in the arrayList write its word into file
        for (String t : MainActivity.wordListArray) {

            t = t + "\n";
            count = count + 1;

            FileOutputStream fOutput = null;
            try {

                fOutput = new FileOutputStream(file, true);

                fOutput.write(t.getBytes());


                fOutput.flush();
                fOutput.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fOutput != null) {
                    try {
                        fOutput.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void clearFile(File file) {
        //    Clears the txt file in the internal memory
        try {
            FileWriter fw = new FileWriter(file, false);
            file.deleteOnExit();
            PrintWriter printWriter = new PrintWriter(fw, false);
            printWriter.flush();
            printWriter.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}