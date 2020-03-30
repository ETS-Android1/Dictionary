package com.example.dictionary;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.dictionary.dictionary.searchWord;

public class MainActivity extends AppCompatActivity {
    //    myWordList is for the Ui list. wordList is for txt file
    public static ArrayList<WordItem> myWordList;
    public static WordAdapter myAdapter;
    public RecyclerView myRecyclerView;
    public Button insert;
    public SearchView searchView;
    public RecyclerView.LayoutManager myLayoutManager;
    //    public String FILE_NAME = "words.txt";
    public static ArrayList<String> wordList = new ArrayList<>();



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
        createWordList();
        buildRecyclerView();
        readFile();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

//                if (!searchView.isIconified()) {
//                    searchView.setIconified(true);
//                }
//                searchView.onActionViewCollapsed();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //                Run search query program by sending the query string to the search query class
                searchWord(newText);
                return false;
            }
        });

    }

    public void createWordList() {


    }

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
                dictionary.removeItem(position);

            }
        });
    }

    public void setButtons() {
        //        instantiate the insert and remove buttons. Insert opens a new activity. Remove deletes a word
        insert = findViewById(R.id.insert);
//        remove = findViewById(R.id.delete);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsertActivity();
            }
        });

    }

    public void openInsertActivity() {
        Intent intent = new Intent(this, InsertWordActivity.class);
        startActivity(intent);
    }

    //    Some method calls cannot be static. Therefore making a reference to this method from a static context results in an error
//    public void writeFileForMain() {
//
//        try {
//            PrintWriter printWriter=new PrintWriter(file);
//            printWriter.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        FileOutputStream fOutput = null;
////        Iterator iterate =wordList.iterator();
////        while (iterate.hasNext()){
//        int count = 0;
//        for (String t : wordList) {
////            String t;
////                    t=iterate.next();
//            t = "\n" + t;
//            count++;
//            try {
//                fOutput = new FileOutputStream(file, true);
////            String fileName=file;
////            fOutput = openFileOutput("words.txt", Context.MODE_PRIVATE);
////            byte[] byteArr=;
//                fOutput.write(t.getBytes());
////            fOutput.write(s.getBytes());
//                fOutput.close();
////            File fileName = new File("words.txt");
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (fOutput != null) {
//                    try {
//                        fOutput.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }


    public void readFile() {
//         File file = new File(getExternalFilesDir("raw"), FILE_NAME);

        BufferedReader reader;
        try {
//            InputStream inStream = new FileInputStream(file);
            InputStream inStream = getAssets().open("words.txt");
//                    Scanner scan = new Scanner(inStream);
            reader = new BufferedReader(new InputStreamReader(inStream));
            String line = reader.readLine();
            while (line != null) {
//                        Log.d("StackOverflow", line);
                line = reader.readLine();
                wordList.add(line);
                InsertWordActivity.insertItem(dictionary.wordPosition(), line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
