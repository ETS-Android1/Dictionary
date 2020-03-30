package com.example.dictionary;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//import static com.example.dictionary.MainActivity.FILE_NAME;

//import static com.example.dictionary.dictionary.FILE_NAME;

public class InsertWordActivity extends AppCompatActivity {
    public EditText insertWord;
    public TextView showText;
    public Button insertButton;
    public String s;
    public int count = 0;
    public int position = dictionary.wordPosition();

    //    Adds a new word item at the appropriate index
    public static void insertItem(int position, String newWord) {
        MainActivity.myWordList.add(position, new WordItem(newWord));
        MainActivity.myAdapter.notifyItemInserted(position);
    }

    //    To insert a word into the txt file
//    public void insertWord(int position, String s) {
//       saveFile();
////        int r=0;
////        for (int i = s.length()-1; i >0 ; i--) {
////            char c =s.charAt(i);
////            r=(int) c + a*r;
//////            System.out.println("r="+r);
////        }
//
////        File f = new File("words.txt");
////        Scanner scan =new Scanner(f);
////        while(scan.hasNextLine()){
////            System.out.println(scan.next());
////        }
//
//        FileInputStream fInput=null;
//        fInput=openFileInput(FILE_NAME);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_word);


        insertWord = findViewById(R.id.insertText);
        insertButton = findViewById(R.id.insert);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                readFile();
                //                    int size = inStream.available();
//
//                    byte[] buffer = new byte[size];
//                    inStream.read(buffer);
//                    inStream.close();
//
//                        text = new String(buffer);
//                        insertItem(position, text);

//            fInput = openFileInput(FILE_NAME);
//            InputStreamReader inStreamReader = new InputStreamReader(fInput);
//            BufferedReader buffReader = new BufferedReader(inStreamReader);
//            StringBuilder stBuilder = new StringBuilder();
//            String text;
//            while ((text = buffReader.readLine()) != null) {
//                stBuilder.append(text).append("\n");
//            }

//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


//                gets the array size from main activity so as to input new word at bottom of array
                insertWord.onEditorAction(EditorInfo.IME_ACTION_DONE);
//                int position=
//                int position = MainActivity.arraySize();
                s = insertWord.getText().toString();
                if (s.isEmpty()) {
                    Toast.makeText(getApplicationContext(), s + "Invalid Word", Toast.LENGTH_LONG).show();
                }
//                 Passes the word to be inserted. Displays the new word has been inserted in the list.
                else {
//                    Display a toast that shows the word has been inserted into the list
                    Toast.makeText(getApplicationContext(), s + " has been inserted in the list", Toast.LENGTH_LONG).show();
//                  Pass the word to the dictionary class so that it can be inserted into the txt file

                    writeFile();

//                     Pass the word to the insertItem method so that it can be inserted into the recyclerView
                    insertItem(position, s);
                    insertWord.setText("");
                }
            }
        });


    }

    public void clearFile() {
        File file = new File(getExternalFilesDir("raw"), "words.txt");
        try {
            FileWriter fw = new FileWriter(file, false);

            PrintWriter printWriter = new PrintWriter(fw, false);
            printWriter.flush();
            printWriter.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //Same as the one in Main but no static context errors.
//Insert: Upon method call the current list is deleted and replaced with an updated arrayList
    public void writeFile() {

        File file = new File(getExternalFilesDir("raw"), "words.txt");
//        int count = 0;
        if (count > 0) {
            count = 0;
            clearFile();
        }

        FileOutputStream fOutput = null;


        for (String t : MainActivity.wordList) {
//For each word inserted count++
            t = "\n" + t;
            count = count + 1;

            try {
                fOutput = new FileOutputStream(file, true);
//            String fileName=file;
//            fOutput = openFileOutput("words.txt", Context.MODE_PRIVATE);
//            byte[] byteArr=;
                fOutput.write(t.getBytes());

//            fOutput.write(s.getBytes());
                fOutput.close();
//            File fileName = new File("words.txt");
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
        MainActivity.wordList = null;
    }
}


