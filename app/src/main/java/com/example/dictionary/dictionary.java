package com.example.dictionary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class dictionary {

    //Hashmap for the hash table dictionary
    public static HashMap<Character, LinkedChainedEntry> hashMap = new HashMap<>();

    public static boolean searchWord(HashMap<Character, LinkedChainedEntry> m, String word) {
        LinkedChainedEntry entry = m.get(word.charAt(0));
        LinkedNode current = entry.head;
        while (current != null) {
            if (current.word.equals(word)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static void insertWord(HashMap<Character, LinkedChainedEntry> m, String word) {

        try {
            LinkedChainedEntry chainedEntry = m.get(word.charAt(0));

            LinkedNode currentNode = chainedEntry.head;
            LinkedNode newNode = new LinkedNode(word);


            while (currentNode != null) {
                System.out.println(currentNode.word.compareTo(word));
                //insert @ head
                if (currentNode.previous == null && currentNode.word.compareTo(word) > 0) {
                    chainedEntry.head = newNode;
                    currentNode.previous = chainedEntry.head;
                    chainedEntry.head.next = currentNode;
                    break;
                }
                //insert @ between.
                if (currentNode.previous != null && currentNode.next != null && currentNode.word.compareTo(word) < 0) {//e.g. str1.compareTo(str2). str1<str2=-1, str1=str2==0, str1>str2=1
                    newNode.next = currentNode.next;
                    currentNode.next.previous = newNode;
                    newNode.previous = currentNode;
                    currentNode.next = newNode;
                    break;
                }
                //insert @ end
                else if (currentNode.word.compareTo(word) < 0 && currentNode.next == null) {
                    chainedEntry.tail = newNode;
                    currentNode.next = chainedEntry.tail;
                    chainedEntry.tail.previous = currentNode;
                    break;
                }
                currentNode = currentNode.next;
            }
        } catch (NullPointerException e) {
            //if the key doesn't exist and a NullPointer error is thrown, a new key and value is created
            LinkedChainedEntry newChainEntry = new LinkedChainedEntry();
//            LinkedNode newNode = new LinkedNode(word);
//            newChainEntry.head=newChainEntry.tail=newNode;
            newChainEntry.insert(word);
            m.put(word.charAt(0), newChainEntry);
        }
    }

    public static void deleteWord(HashMap<Character, LinkedChainedEntry> m, String word) {

        LinkedChainedEntry chainedEntry = m.get(word.charAt(0));
        LinkedNode tempNode = chainedEntry.head;

        while (tempNode != null) {

            if (tempNode.word.equals(word)) {
                //delete @ head
                if (tempNode.previous == null) {
                    /*if the previous node is null we are @ head node.
                     * tempNode Next value will become the head, while the pointer to the previous value will be null.
                     * tempNodes previous and next values will be null*/
                    chainedEntry.head = tempNode.next;
                    chainedEntry.head.previous = null;
                    //delete @ head w/ one node in list
                    if (chainedEntry.head.word.equals(chainedEntry.tail.word)) {
                    /*if the heads next pointer is the tail remove the node entirely.
                    i.e. if there is only one node in the list remove the list entirely from the list*/
                        m.remove(word.charAt(0));
                    }
                }
                //delete @ between
                else if (tempNode.next != null) {
                    tempNode.previous.next = tempNode.next;
                    tempNode.next.previous = tempNode.previous;
//                    tempNode.previous = tempNode.next = null;
                }
                //delete @ end
                else {//if (tempNode.next == null) {
                    chainedEntry.tail = tempNode.previous;
                    chainedEntry.tail.next = null;
                }
                break;
            }
            tempNode = tempNode.next;
        }
    }

    //Insert: Upon method call, the current list is deleted and replaced with an updated arrayList.
    // Although not a sufficient way. Its one of the few ways ive got this to work
    public static void writeFile(File file) {

        clearFile(file);
        int count = 0;
        for (LinkedChainedEntry element : hashMap.values()) {
            LinkedNode currentNode = element.head;
            while (currentNode != null) {

                //For each word in the linked list write its word into file
//                for (String t : MainActivity.wordListArray) {
                String t = currentNode.word + "\n";
                count = count + 1;

                FileOutputStream fOutput = null;
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
                currentNode = currentNode.next;
            }
        }
    }

    public static void clearFile(File file) {
        //    Clears the txt file in the internal memory
        try {
            FileWriter fw = new FileWriter(file, false);
            file.deleteOnExit();
            PrintWriter printWriter = new PrintWriter(fw, false);
            printWriter.flush();
            printWriter.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Both need to be static so it can be accessed from both the MainActivity and InsertWordActivity.*/
    //Create the node class
    public static class LinkedNode {
        public String word;
        LinkedNode previous;
        LinkedNode next;

        public LinkedNode(String word) {
            this.word = word;
        }
    }

    //Create a Doubly linked list class
    public static class LinkedChainedEntry {
        LinkedNode head;
        LinkedNode tail;

        public LinkedChainedEntry() {
            this.head = null;
            this.tail = null;
        }

        public void insert(String data) {
            //Inserts a node into the respectable linked list
            LinkedNode newNode = new LinkedNode(data);
            if (this.head == null) {
                //Both head and tail will point to newNode
                this.head = this.tail = newNode;
            }
            //Add newNode as new tail of the list
            else {
                //newNode will be added after tail such that tail's next will point to newNode
                this.tail.next = newNode;
                //newNode's previous will point to tail
                newNode.previous = this.tail;
                //newNode will become new tail
                this.tail = newNode;
            }
        }
    }
}