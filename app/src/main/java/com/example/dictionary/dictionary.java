package com.example.dictionary;

public class dictionary {
    public static final int a = 31;

    //    The word position in the list. After calculating the position for the word
    public static int wordPosition() {

//        This is temporary to test program
        int position = MainActivity.arraySize();
//        int position = hashCode(InsertWordActivity)
        return position;
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