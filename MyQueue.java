/**********************************************************************************************************************
 **********************************************************************************************************************
 *****  Class: CSC-360-001-2019-040    Semester: Summer 2019    Professor: Richard Fox    Student: Ryan Huffman   *****
 *****------------------------------------------------------------------------------------------------------------*****
 *****                   This is the our Generic Queue class that contains one Instance Datum:                    *****
 *****                        A Generic LinkedList called "queue" and initialized to null                         *****
 *****                                                                                                            *****
 *****                    This class also contains the enqueue, dequeue and isEmpty methods.                      *****
 **********************************************************************************************************************
 **********************************************************************************************************************/

// IMPORTS of needed tools and plug-ins
import java.util.LinkedList;


// Generic Queue Class with a Generic LinkedList Instance Datum
public class MyQueue<E> {

    // Instance Data, States or Fields
    private LinkedList<E> queue;


    // No-arg Constructor to Initialize the Instance Datum LinkedList<E> queue to null
    public MyQueue(){
        // Initialize the Instance Datum LinkedList "queue" to null
        // queue = null;

        queue = new LinkedList<>();
    }


    // Enqueue method to add the parameter item of Generic Type "E" to the Instance Datum LinkedList called "queue"
    public void enqueue(E newItem){
        // Add the new item to the end of the Instance Datum LinkedList "queue"
        queue.addLast(newItem);
    }


    // Dequeue method to remove the first entity from the Instance Datum LinkedList "queue" if there is any entities
        // in the LinkedList. If the Instance Data LinkedList is empty, then return null
    public E dequeue(){
        // if statement block to check that the Instance Datum LinkedList "queue" is not empty.
            // If the LinkedList is not empty, Remove the item from the LinkedList and Return that item
        if(!queue.isEmpty()){
            return queue.removeFirst();
        }
        // else statement block that executes if the Instance Datum LinkedList "queue" is empty. In that case,
            // Return null
        else{
            return null;
        }
    }


    // boolean method that returns whether the Instance Datum LinkedList "queue" is empty
    public boolean isEmpty(){
        return (queue.isEmpty());
    }


    // Peek method to check if the Instance Datum LinkedList "queue" is not empty. If the Instance Datum LinkedList
        // "queue" is not empty, Return (without Removing) the first item in the LinkedList "queue"
            // THIS METHOD WILL NOT BE USED IN THIS ASSIGNMENT
    public E peek(){
        // if statement block to check if the Instance Datum LinkedList "queue" is not empty. If the LinkedList "queue"
            // is not empty, Return (without Removing it) the first item in the LinkedList "queue"
        if(!queue.isEmpty()){
            return queue.getFirst();
        }
        // else statement block that executes if the Instance Datum LinkedList "queue" is empty. If this is the case,
            // Return null
        else {
            return null;
        }
    }


    // Accessor Method to get the LinkedList "queue"
    public LinkedList<E> getQueue() {
        return queue;
    }
}
