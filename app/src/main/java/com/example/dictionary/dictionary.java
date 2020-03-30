package com.example.dictionary;

public class dictionary {


    //    Program the search algorithm here
    public static String searchWord(String searchView) {
        String newWord = "";
//        The list for the search array is MainActivity.wordList;
        //Search For word here
//upon search query complete, the word is @
//        Toast.makeText(getApplicationContext(), the word+" is at this "+position +" in the list", Toast.LENGTH_LONG).show();
        return newWord;
    }

    //    The word position in the list. After calculating the position for the word
    public static int wordPosition() {

//        This is temporary to test program
        int position = MainActivity.arraySize();
        return position;
    }

    //    Remove the item from the list. (
    public static void removeItem(int position) {
//Program to remove the word from the wordlist file
//        Write here


//        Item will be removed from the recycler view
        MainActivity.myWordList.remove(position);
        MainActivity.myAdapter.notifyItemRemoved(position);
    }
}


//File file = new File(getExternalFilesDir("raw"),FILE_NAME);
//
//        FileOutputStream fOutput = null;
//        try {
//            fOutput=new FileOutputStream(FILE_NAME,true);
////            String fileName=file;
//            fOutput = openFileOutput(FILE_NAME, MODE_PRIVATE);
////            byte[] byteArr=;
//            fOutput.write(s.getBytes());
////            fOutput.write(s.getBytes());
//            fOutput.close();
////            File fileName = new File("words.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            if (fOutput != null) {
//                try {
//                    fOutput.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


//This one can append to an external file
// File file = new File(getExternalFilesDir("raw"), FILE_NAME);
//s=s+"\n";
//        FileOutputStream fOutput = null;
//        try {
//            fOutput=new FileOutputStream(file,true);
////            String fileName=file;
////            fOutput = openFileOutput("words.txt", Context.MODE_PRIVATE);
////            byte[] byteArr=;
//            fOutput.write(s.getBytes());
////            fOutput.write(s.getBytes());
//            fOutput.close();
////            File fileName = new File("words.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fOutput != null) {
//                try {
//                    fOutput.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }