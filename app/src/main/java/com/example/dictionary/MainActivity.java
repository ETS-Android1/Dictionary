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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //    myWordList is for the Ui list. wordList is for txt file
    public static ArrayList<WordItem> myWordList;
    public static WordAdapter myAdapter;
    public static ArrayList<String> wordListArray = new ArrayList<>();
    public RecyclerView myRecyclerView;
    public Button insert;
    public SearchView searchView;
    public RecyclerView.LayoutManager myLayoutManager;

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

        //on word search return a snackbar whether the word is in the list
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String _word) {
                //TODO Program the search algorithm here using "searchWord(newText);" below. "_word" is what you are passing through
                //Program the search algorithm here
//                 dictionary.searchWord(_word);
                if (wordListArray.contains(_word)) {
                    Toast.makeText(getApplicationContext(), _word + " is in the list", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), _word + " isn't in the list", Toast.LENGTH_LONG).show();
                }

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
        myAdapter.setOnItemClickListener(new WordAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {

                //Program to remove the word from the wordlist file
                //TODO Remove
                MainActivity.wordListArray.remove(position);
                dictionary.writeFile();

                //Item will be removed from the recycler view
                MainActivity.myWordList.remove(position);
                MainActivity.myAdapter.notifyItemRemoved(position);
            }
        });
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

    //    Reads the txt file situated at the assets folder
    public void readFile() {
        BufferedReader reader;
        try {
            InputStream inStream = getAssets().open("words.txt");
            reader = new BufferedReader(new InputStreamReader(inStream));
            String line = reader.readLine();

            while (line != null) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                wordListArray.add(line);

                //The txt file in the internal storage is
                InsertWordActivity.insertItem(dictionary.wordPosition(), line);
            }
//wordList.remove(wordList.get(MainActivity.arraySize()-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}