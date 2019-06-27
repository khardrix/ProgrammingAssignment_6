/**********************************************************************************************************************
 **********************************************************************************************************************
 *****  Class: CSC-360-001-2019-040    Semester: Summer 2019    Professor: Richard Fox    Student: Ryan Huffman   *****
 *****------------------------------------------------------------------------------------------------------------*****
 *****                                    This is the Customer Object Class                                       *****
 *****         (The Comparable Interface and the compareTo methods are to be used with the PriorityQueues)        *****
 *****              This Class contains two int Instance Datums to store the Customer's entry time and            *****
 *****                             the amount of time it took to service that Customer.                           *****
 *****                         This Class also has Accessor Methods for both Instance Datum.                      *****
 *****                                                                                                            *****
 *****             This Class implements the Comparable Interface and the compareTo method compares               *****
 *****                          the amount of time it took to service that Customer.                              *****
 *****                      If it took the same amount of time to service both Customers,                         *****
 *****                   then the tie is broken by which Customer had the earlier entry time.                     *****
 **********************************************************************************************************************
 **********************************************************************************************************************/

// Class that Implements Comparable since that is required for a PriorityQueue
public class Customer implements Comparable {

    // Instance Datum, States or Fields
    private int entryTime;
    private int serviceTime;


    // 2-arg Constructor
    public Customer(int entryTime, int serviceTime){
        // Set the initial values of the Instance Datum by the arguments passed into this 2-arg Constructor
        this.entryTime = entryTime;
        this.serviceTime = serviceTime;
    }


    // Accessor method for the int Instance Datum "entryTime"
    public int getEntryTime() {
        return entryTime;
    }


    // Accessor method for the int Instance Datum "serviceTime"
    public int getServiceTime() {
        return serviceTime;
    }


    // Overriding the compareTo method from the Comparable Interface for the "Shortest Job First" approach
    @Override
    public int compareTo(Object o) {

        // if statement block to check if this Customer's service time is equal to the other Customer's service time
            // and if so, Return this Customer's entry time minus the other Customer's entry time
        if(this.serviceTime == ((Customer)o).getServiceTime()){
            return (this.entryTime - ((Customer)o).getEntryTime());
        }
        // else statement block that executes if this Customer's service time is not equal to the other Customer's
            // service time. If this is the case, Return this Customer's service time minus the Other Customer's
            // service time
        else{
            return (this.serviceTime - ((Customer)o).getServiceTime());
        }
    }


/*
    // Overriding the compareTo method from the Comparable Interface for the "Longest Job First" approach
    @Override
    public int compareTo(Object o) {

        // if statement block to check if this Customer's service time is equal to the other Customer's service time
            // and if so, Return the other Customer's entry time minus this Customer's entry time
        if(this.serviceTime == ((Customer)o).getServiceTime()){
            return (((Customer)o).getEntryTime() - this.entryTime);
        }
        // else statement block that executes if this Customer's service time is not equal to the other Customer's
            // service time. If this is the case, Return the other Customer's service time minus this Customer's
            // service time
        else{
            return (((Customer)o).getServiceTime() - this.serviceTime);
        }
    }
*/

    // Override the Object classes toString method
    @Override
    public String toString() {
        return "Entry Time: " + this.entryTime + "\nService Time: " + this.serviceTime + "\n";
    }
}
