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
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //    myWordList is for the Ui list. wordList is for txt file
    public static ArrayList<WordItem> myWordList;
    public static WordAdapter myAdapter;
    public RecyclerView myRecyclerView;
    public Button insert;
    public int count = 0;
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

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    Program the search algorithm here
                if (wordList.contains(newText)) {
                    Toast.makeText(getApplicationContext(), newText + " is in the list", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), newText + " isn't in the list", Toast.LENGTH_LONG).show();
                }

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


//Program to remove the word from the wordlist file
                MainActivity.wordList.remove(position);
                writeFile();

//                Item will be removed from the recycler view
                MainActivity.myWordList.remove(position);
                MainActivity.myAdapter.notifyItemRemoved(position);
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

    //    Reads the file situated at the assets folder
    public void readFile() {
        BufferedReader reader;
        try {

            InputStream inStream = getAssets().open("words.txt");
            reader = new BufferedReader(new InputStreamReader(inStream));
            String line = reader.readLine();

            while (line != null) {
                line = reader.readLine();
                wordList.add(line);
                if (line == null) {
                    break;
                }
                InsertWordActivity.insertItem(dictionary.wordPosition(), line);
            }
//wordList.remove(wordList.get(MainActivity.arraySize()-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    Same as that in InsertWordActivity. Some Items could not be resolved due to static context
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

    //    Same as that in InsertWordActivity. Some Items could not be resolved due to static context
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
