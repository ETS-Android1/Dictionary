package com.example.dictionary;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;


public class InsertWordActivity extends AppCompatActivity {
    public EditText insertWord;
    public Button insertButton;
    public String _insertWord;
    public int position = dictionary.wordPosition;
    public static File file;
    //    Adds a new word item at the appropriate index
    public static void insertItem(int position, String newWord) {
        MainActivity.myWordList.add(position, new WordItem(newWord));
        MainActivity.myAdapter.notifyItemInserted(position);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_word);
        file = new File(getExternalFilesDir("raw"), "words.txt");
        //Initializing insertWord & insertButton variables
        insertWord = findViewById(R.id.insertText);
        insertButton = findViewById(R.id.insert);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //gets the array size from main activity so as to input new word at bottom of array
                insertWord.onEditorAction(EditorInfo.IME_ACTION_DONE);
                _insertWord = insertWord.getText().toString();

                /* If the inserted word is empty return a toast with a msg*/
                if (_insertWord.isEmpty()) {
                    Toast.makeText(getApplicationContext(), _insertWord + "Invalid Word", Toast.LENGTH_LONG).show();
                } else {
                    /* Passes the word to be inserted. Displays the new word has been inserted in the list.
                    The word gets added and sorted in the main activity word list, followed by a toast to indicate success
                    i.e. The list of words displayed will show the newly inserted word sorted */
                    dictionary.insertWord(_insertWord);
//                    Collections.sort(MainActivity.wordListArray);
                    Toast.makeText(getApplicationContext(), "\"" + _insertWord + "\"" + " has been inserted in the list at index " + position, Toast.LENGTH_LONG).show();
                    //Pass the word to the insertItem method so that it can be inserted into the recyclerView
                    insertItem(position, _insertWord);
                    insertWord.setText("");
                    //Pass the word to the dictionary class so that it can be inserted into the txt file
                    //File file = new File(getExternalFilesDir("raw"), "words.txt");
                    //dictionary.writeFile(file);
                    WriteThread writeThread = new WriteThread();
                    writeThread.start();
                }
            }
        });

    }

    class WriteThread extends Thread {

        @Override
        public void run() {
            dictionary.writeFile(file);
        }
    }
}

