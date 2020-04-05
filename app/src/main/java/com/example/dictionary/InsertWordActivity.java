package com.example.dictionary;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//import static com.example.dictionary.MainActivity.FILE_NAME;

//import static com.example.dictionary.dictionary.FILE_NAME;

public class InsertWordActivity extends AppCompatActivity {
    public EditText insertWord;
    public Button insertButton;
    public String s;
    public int count = 0;
    public ArrayList<String> tempArr;
    public int position = dictionary.wordPosition();

    //    Adds a new word item at the appropriate index
    public static void insertItem(int position, String newWord) {
        MainActivity.myWordList.add(position, new WordItem(newWord));
        MainActivity.myAdapter.notifyItemInserted(position);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_word);


        insertWord = findViewById(R.id.insertText);
        insertButton = findViewById(R.id.insert);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                gets the array size from main activity so as to input new word at bottom of array
                insertWord.onEditorAction(EditorInfo.IME_ACTION_DONE);

                s = insertWord.getText().toString();
                if (s.isEmpty()) {
                    Toast.makeText(getApplicationContext(), s + "Invalid Word", Toast.LENGTH_LONG).show();
                }
//                 Passes the word to be inserted. Displays the new word has been inserted in the list.
                else {
                    MainActivity.wordList.add(s);
//                    Collections.sort(MainActivity.wordList);
                    Collections.sort(MainActivity.wordList, new Comparator<String>() {
                        @Override
                        public int compare(String s1, String s2) {
                            return s1.compareToIgnoreCase(s2);
                        }
                    });
//                    Display a toast that shows the word has been inserted into the list
                    Toast.makeText(getApplicationContext(), "\"" + s + "\"" + " has been inserted in the list", Toast.LENGTH_LONG).show();
//                  Pass the word to the dictionary class so that it can be inserted into the txt file
                    writeFile();
//                     Pass the word to the insertItem method so that it can be inserted into the recyclerView
                    insertItem(position, s);
                    insertWord.setText("");
                }
            }
        });

    }

    //    Clears the txt file in the internal memory
    public void clearFile() {

        File file = new File(getExternalFilesDir("raw"), "words.txt");
        file.deleteOnExit();
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
        clearFile();
        FileOutputStream fOutput = null;


        for (String t : MainActivity.wordList) {
//For each word inserted count++

            t = "\n" + t;
            count = count + 1;


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


}


