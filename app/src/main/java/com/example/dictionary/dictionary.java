package com.example.dictionary;

public class dictionary {
    public static final int a = 1;

    //    Program the search algorithm here
    public static String searchWord(String newText) {
        String newWord = "";
//        The list for the search array is MainActivity.wordList;
        //Search For word here
//        if ()
//upon search query complete, the word is @
//        Toast.makeText(getApplicationContext(), the word+" is at this "+position +" in the list", Toast.LENGTH_LONG).show();
        return newWord;
    }

    //    The word position in the list. After calculating the position for the word
    public static int wordPosition() {

//        This is temporary to test program
        int position = MainActivity.arraySize();
//        int position = hashCodePos();
        return position;
    }

    //    Remove the item from the list. (
    public static void removeItem(int position) {
//Program to remove the word from the wordlist file
//        Write here
//position=0;
//MainActivity.wordList.contains()
//        Item will be removed from the recycler view
        MainActivity.myWordList.remove(position);
        MainActivity.myAdapter.notifyItemRemoved(position);
    }

    public static int hashCodePos() {
        int i = 0;
        int r;
        for (String w : MainActivity.wordList) {

            r = 0;
//            int r = 0;
            int c;
            for (i = w.length() - 1; i >= 0; i--) {
                c = w.charAt(i);

                r = c + a * r;
//                r=;

            }

            return (r);
        }


        return 0;
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