package com.example.dictionary;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class dictionary {
    public static final int a = 31;
    private static final String FILE_NAME = "words.txt";

    //The word position in the list. After calculating the position for the word
    public static int wordPosition() {
        return MainActivity.arraySize();
    }


    public static int hashCode(String s) {

        int i;
        int r = 0;
        char c;

        for (i = s.length() - 1; i >= 0; i--) {
            c = s.charAt(i);

            r = (int) c + a * r;

        }

        return (r);
    }


    //TODO search, insert, delete algorthms the array with the list of words to apply data structures is "MainActivity.wordListArray"
    public static boolean searchWord(String text) {
      /*  TODO program the searchWord algorithm here using the wordListArray as the list of words to search through.
                   Return boolean val if word exists */
        return false;
    }

    public static boolean insertWord(String text) {
      /*  TODO program the searchWord algorithm here using the wordListArray as the list of words to search through.
                   Return boolean val if word exists */
        return false;
    }

    public static boolean deleteWord(String text) {
      /*  TODO program the deleteWord algorithm here using the wordListArray as the list of words to search through.
                   Return boolean val if word exists */
        return false;
    }

    public static void sortWordsList() {

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
                FileOutputStream fOutput = null;
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