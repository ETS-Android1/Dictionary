package com.example.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //myWordList is for the Ui list. wordListArray is for txt file
    public static ArrayList<WordItem> myWordList;
    public static WordAdapter myAdapter;
    public static ArrayList<String> wordListArray = new ArrayList<>();
    public RecyclerView myRecyclerView;
    public Button insert;
    public SearchView searchView;
    public RecyclerView.LayoutManager myLayoutManager;
    public File file;

    public static int arraySize() {
        return myWordList.size();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWordList = new ArrayList<>();
        searchView = findViewById(R.id.searchView);
        setButtons();
        buildRecyclerView();
        readFile();
        file = new File(getExternalFilesDir("raw"), "words.txt");

        //on word search return a snackbar whether the word is in the list
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                boolean _found = dictionary.searchWord(query, MainActivity.wordListArray);
                //A snackbar  (and the words position determined from 0-wordListArray.size) will be returned to the screen if the word is found
                if (_found) {
                    Toast.makeText(getApplicationContext(), query + " is in the list at index " + dictionary.wordPosition, Toast.LENGTH_LONG).show();
                } else if (!_found) {
                    Toast.makeText(getApplicationContext(), query + " isn't in the list", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String _word) {
                return false;
            }
        });
    }


    // Builds recycler view for the list of words displayed on the screen
    public void buildRecyclerView() {
        myRecyclerView = findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myAdapter = new WordAdapter(myWordList);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);
        final WriteThread writeThread = new WriteThread();
        myAdapter.setOnItemClickListener(new WordAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                //Deletes the word from the adapter (list on screen) and from the mainArray
                dictionary.deleteWord(position);
                //Item will be removed from the recycler view
                MainActivity.myWordList.remove(position);

                MainActivity.myAdapter.notifyItemRemoved(position);

                writeThread.start();
//                WriteThreadRunnable writeThreadRunnable = new WriteThreadRunnable();
//                writeThreadRunnable.run();
            }
        });
    }

    //    Reads the txt file situated at the assets folder
    public void readFile() {
        BufferedReader reader;
        int count = 0;
        try {
            InputStream inStream = getAssets().open("words.txt");
            reader = new BufferedReader(new InputStreamReader(inStream));
            String line = " ";

            while (line != null) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                wordListArray.add(line);

                //The txt file in the internal storage is
                InsertWordActivity.insertItem(count, line);
                count++;
            }
//wordList.remove(wordList.get(MainActivity.arraySize()-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    Instantiate the insert button
    public void setButtons() {
        /*instantiate the insert.  Onclick, it will redirect and open a new activity.
        Redirects to the openInsertActivity()*/
        insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsertActivity();
            }
        });
    }

    //    On Click this method will open a new activity
    public void openInsertActivity() {
        Intent intent = new Intent(this, InsertWordActivity.class);
        startActivity(intent);
    }

    //    class WriteThreadRunnable implements Runnable{
//
//
//        @Override
//        public void run() {
//            dictionary.writeFile(file);
//        }
//    }
    //a thread will be created to rewrite the file upon
    class WriteThread extends Thread {

        @Override
        public void run() {
            dictionary.writeFile(file);
        }
    }
}