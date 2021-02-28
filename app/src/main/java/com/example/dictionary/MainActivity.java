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
                boolean _found = dictionary.searchWord(dictionary.hashMap, query);
                //A snackbar  (and the words position determined from 0-wordListArray.size) will be returned to the screen if the word is found
                if (_found) {
                    Toast.makeText(getApplicationContext(), query + " is in the list", Toast.LENGTH_LONG).show();
                } else {
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
//        final WriteThread writeThread = new WriteThread();
        myAdapter.setOnItemClickListener(new WordAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                //Deletes the word from the adapter (list on screen) and from the hashmap
                dictionary.deleteWord(dictionary.hashMap, myWordList.get(position).getWord());
//                wordPosition += 1;
//                arrayPosition=position;
                //Item will be removed from the recycler view
                MainActivity.myWordList.remove(position);
                MainActivity.myAdapter.notifyItemRemoved(position);

                //updates the txt file upon word delete
                WriteThread deleteThread = new WriteThread();
                deleteThread.start();
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

                // if the table does not contain the key (indicated by the 1st character in word) create a new linked list and point to it.
                //Else get the linked list of the character/key of hte word and insert @ end of list
                dictionary.LinkedChainedEntry linkedChainedEntry = new dictionary.LinkedChainedEntry();
                if (!dictionary.hashMap.containsKey(line.charAt(0))) {
                    linkedChainedEntry.insert(line);
                    dictionary.hashMap.put(line.charAt(0), linkedChainedEntry);
                } else {
                    linkedChainedEntry = dictionary.hashMap.get(line.charAt(0));
                    linkedChainedEntry.insert(line);
                }

                //Insert into the displays list
                InsertWordActivity.insertItem(count, line);
                count++;
            }
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


    //    a thread will be created to rewrite the file upon deletion
    class WriteThread extends Thread {
        @Override
        public void run() {
            dictionary.writeFile(file);
        }
    }
}