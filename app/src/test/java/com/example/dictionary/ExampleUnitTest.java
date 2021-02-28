package com.example.dictionary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final ArrayList<String> mainArray = new ArrayList<>();
    private static final ArrayList<String> minSearchArray = new ArrayList<>();
    //has the index of the words from the MainActivity.werdArrayList
    private static final ArrayList<Integer> minSearchIndex = new ArrayList<>();
    public static int wordPosition;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void minarray() {
        String[] first = {"add", "asdf", "awesrt", "awd", "bad", "badd", "catt", "cter", "cbnf"};
        String[] second = {"asdf", "adf", "asdf"};
        String[] compare = {"catt", "cter", "cbnf"};

        char ch = 'c';
        int length = 3;
        for (String text : first) {
            if (ch == text.charAt(0)) {
                second[second.length - length--] = text;
            }
        }

        assertArrayEquals(second, compare);
    }

    @Test
    public void myTest() {
//    String[] first = {"add", "asdf", "awesrt", "awd", "bad", "badd", "catt", "cter", "cbnf"};
        mainArray.add("add");

        //boolean searched = searchWord("Catskilll", mainArray);        assertEquals(searched, false);
        // boolean searched=delete("cabvdn",mainArray);        assertEquals(searched,true);
        // boolean searched = insert("cabvdn", mainArray);        assertEquals(searched, true);

        Map<Character, String> m = new TreeMap<>();
        m.put('a', "add");
        m.put('a', "asdf");
        m.put('a', "awerst");
        m.put('b', "bad");
        m.put('b', "badd");
        m.put('c', "Catiline");
        m.put('c', "Cato");
        m.put('c', "Catskill");
        m.put('c', "cabvdn");

        System.out.println(m);

    }

    @Test
    public void HashTest() {
        //Hashmap for the hash table dictionary
        HashMap<Character, LinkedChainedEntry> m = new HashMap<>();

        //create an array to simulate traversal through words.txt
        String[] first = {"add", "asdf", "awesrt", "awd", "bad", "badd", "catt", "cter", "cbnf"};

        //for each element in the array. Add it to the corresponding key based on its first character and while the entrySet is not empty
        for (String s : first) {
            // if the table does not contain the key (indicated by the 1st character in word) create a new linked list and point to it.
            //Else get the linked list of the character/key of hte word and insert @ end of list
            LinkedChainedEntry linkedChainedEntry = new LinkedChainedEntry();
            if (!m.containsKey(s.charAt(0))) {
                linkedChainedEntry.insert(s);
                m.put(s.charAt(0), linkedChainedEntry);
            } else {
                linkedChainedEntry = m.get(s.charAt(0));
                linkedChainedEntry.insert(s);
            }
        }

        for (HashMap.Entry<Character, LinkedChainedEntry> entry : m.entrySet()) {
            Character key = entry.getKey();
            LinkedChainedEntry element = entry.getValue();
            System.out.println("Key: " + key + "\tElement: ");
            element.display();
        }

        System.out.println("=====================DELETE STRING \"cter\"=======================");
        delete(m, "cter");

        System.out.println("=====================INSERT WORD \"cter\"=======================");
        insertNode(m, "cter");

        System.out.println("=====================SEARCH WORD \"cter\"=======================");
        boolean searched = searchWord(m, "cter");
        assertTrue(searched);

    }

    public boolean searchWord(Map<Character, LinkedChainedEntry> m, String word) {
        LinkedChainedEntry entry = m.get(word.charAt(0));
        LinkedNode current = entry.head;
        while (current != null) {
            if (current.word.equals(word)) {

                entry.display();
                return true;
            }
            current = current.next;
        }
        entry.display();
        return false;
    }

    public void insertNode(Map<Character, LinkedChainedEntry> m, String word) {

        try {
            LinkedChainedEntry chainedEntry = m.get(word.charAt(0));

            LinkedNode currentNode = chainedEntry.head;
            LinkedNode newNode = new LinkedNode(word);


            while (currentNode != null) {
                System.out.println(currentNode.word.compareTo(word));
                //insert @ head
                if (currentNode.previous == null && currentNode.word.compareTo(word) > 0) {
                    chainedEntry.head = newNode;
//                    currentNode.previous = null;
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
            chainedEntry.display();
        } catch (NullPointerException e) {
            //if the key doesn't exist and a NullPointer error is thrown, a new key and value is created
            LinkedChainedEntry newChainEntry = new LinkedChainedEntry();
//            LinkedNode newNode = new LinkedNode(word);
//            newChainEntry.head=newChainEntry.tail=newNode;
            newChainEntry.insert(word);
            m.put(word.charAt(0), newChainEntry);
            newChainEntry.display();

//            insertNode(m, word);
        }
    }

    public void delete(Map<Character, LinkedChainedEntry> m, String word) {

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
        chainedEntry.display();
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

        public void display() {
            //Todo: gotten from online. Should implement own
            //Node current will point to head
            LinkedNode current = head;
            if (head == null) {
                System.out.println("List is empty");
                return;
            }
            System.out.println("Adding a node to the end of the list: ");
            while (current != null) {
                //Prints each node by incrementing the pointer.

                System.out.print(current.word + " ");
                current = current.next;
            }
            System.out.println();
        }
    }
}