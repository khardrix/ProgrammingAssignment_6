/*********************************************************************************************************************
 *********************************************************************************************************************
 *****  Class: CSC-360-001-2019-040    Semester: Summer 2019    Professor: Richard Fox    Student: Ryan Huffman  *****
 *****-----------------------------------------------------------------------------------------------------------*****
 *****                                       Programming Assignment #6                                           *****
 *****___________________________________________________________________________________________________________*****
 *****        This program is a simulation that simulates customers at a bank during a bank's business day.      *****
 *****              This program determines whether it is currently a busy time of the day for bank by           *****
 *****      checking to see if the current time is within the first or last 60 minutes of the business day or    *****
 *****         during lunch time (210 minutes after the bank opens until 270 minutes after the bank opens).      *****
 *****        A random number of new customers to enter the bank and get in line and how line it will take       *****
 *****             to service that customer are randomly generated values whose range depends upon               *****
 *****                         whether it is currently a busy time of the banking day.                           *****
 *****                                                                                                           *****
 *****           The simulation will be ran by using a while loop that increments the current time and           *****
 *****           the number of customers served, while decrementing the amount of time each bank teller          *****
 *****                            needs to finish serving the current customer.                                  *****
 *****               Once the bank teller's value reaches 0, they begin to serve another customer.               *****
 *****                                                                                                           *****
 *****                              The bank is open for 480 minutes each day.                                   *****
 *****  This simulation runs from the time the bank opens until the bank closes its doors after 480 minutes and  *****
 *****              all the customers that entered the bank while the bank was opened have been served.          *****
 *****          The simulation keeps track of the average amount of time it takes to serve a customer and        *****
 *****                  the maximum amount of time any customer waited in line to be served.                     *****
 *****                    After each simulation of a full banking business day is complete,                      *****
 *****           the number of bank tellers is incremented (from 2 to 6) and the process is repeated.            *****
 *****                                                                                                           *****
 *****                   After all of this has been completed, the type of Collection object                     *****
 *****                                   (Queue, Stack and two PriorityQueues.                                   *****
 *****    One PriorityQueue that serves the customers that need the shortest amount of time to serve first and   *****
 *****   another PriorityQueue that serves the customers that will need the most amount of time to serve first)  *****
 *****                                         is printed to the console.                                        *****
 *****                Then, the number of minutes the bank tellers were serving customers that day               *****
 *****                                         is printed to the console.                                        *****
 *****              Then, the number of tellers that was working respective to the rest of the values            *****
 *****                                         is printed to the console.                                        *****
 *****                Then, the maximum amount of time any customer had to wait in line to be served             *****
 *****                                         is printed to the console.                                        *****
 *****                   Lastly, the average amount of time each customer waited in line to served               *****
 *****                                         is printed to the console.                                        *****
 *****       All of this is repeated and printed for Queue, Stack and the two versions of a PriorityQueue.       *****
 *********************************************************************************************************************
 *********************************************************************************************************************/

// IMPORTS of needed tools and plug-ins
import java.util.*;


public class Main {

    // CLASS VARIABLE(s) declaration(s)
    private static MyQueue<Customer> line1 = new MyQueue<>();
    private static Stack<Customer> line2 = new Stack<>();
    private static PriorityQueue<Customer> line3SJF = new PriorityQueue<>();
    private static PriorityQueue<Customer> line4LJF = new PriorityQueue<>();
    private static Random randomGenerator = new Random();


    public static void main(String[] args) {

        // LOCAL VARIABLE(s) declaration(s)
        int serviceTime = 0; // <-- Time it will take to service the current Customer
        int customersServiced = 0; // <-- Total number of Customers serviced by the bank that day
        int totalWaitTime = 0; // <-- Total number of time Customers had to wait in line
        int maxWaitTime = 0; // <-- The maximum amount of time any single Customer had to wait in line
        int customerWaitTime = 0; // <-- The time the current Customer had to wait to be serviced
        int averageWaitTime = 0; // <-- The average amount of time a Customer had to wait to be serviced
        int numOfServers = 2; // <-- Number of bank tellers working
        int numberOfCustomersToAdd = 0; // <-- Number of new Customers to add to the line
        int currentTime = -1; // <-- The current amount of time that has lapsed since the bank opened that day
        String type = "Queue"; // <-- Stores the type of Collection Object that is currently being used
        Customer customer; // <-- The current Customer

        // Initialize the int Array "servers"
        int[] servers = new int[numOfServers];

        // for loop to Initialize the initial value of each server
        for(int i = 0; i < numOfServers; i++){
            servers[i] = 0;
        }

        // Print to the console column headings for Queues and Stacks and PriorityQueues
        System.out.println("Type \tminutes \ttellers \tmax \taverage");

        // while loop that will serve as our simulations
        while((!line1.isEmpty()) || (currentTime <= 480)){
            // Increment the currentTime
            currentTime++;

            // If the currentTime is less than or equal to 480 (Minutes the bank is open that day)
            if(currentTime <= 480){

                // Get a random number of Customers to add to the line based on whether it is a busy part of the day
                numberOfCustomersToAdd = numberOfNewCustomers(isBusyTime(currentTime));
                // Get a random amount of minutes it takes to service a Customer based on whether it is a busy part of
                    // the day
                serviceTime = timeTakenToService(isBusyTime(currentTime));

                // for loop to add Customers to the line
                for(int i = 0; i < numberOfCustomersToAdd; i++){
                    line1.enqueue(new Customer(currentTime, serviceTime));
                    // line2.push(new Customer(currentTime, serviceTime));
                    // line3SJF.offer(new Customer(currentTime, serviceTime));
                    // line4LJF.offer(new Customer(currentTime, serviceTime));
                }

            }

            // for loop to do everything in our simulation for each bank teller
            for(int i = 0; i < (servers.length - 1); i++) {
                if(servers[i] == 0) {
                    // if statement block to check if current line is not empty and only execute if the line isn't empty
                    if(!line1.isEmpty()){
                        // Get the Customer to add to the Customer variable
                        customer = line1.dequeue();
                        // customer = line2.pop();
                        // customer = line3SJF.remove();
                        // customer = line4LJF.remove();

                        // Increment the number of Customers served
                        customersServiced++;

                        // Set the value of the current bank teller to the time it will take to service the
                            // current Customer
                        servers[i] = customer.getServiceTime();

                        // Calculate the current Customer's wait time
                        customerWaitTime = currentTime - customer.getEntryTime();
                    }

                    // Add the current Customer's wait time to the total amount of time Customer's spent waiting
                        // in line that day
                    totalWaitTime += customerWaitTime;

                    // if statement block that checks if the current time is equal 0 (initial run of simulation) and
                        // if so, set the initial value of the maximum time a Customer spent waiting in line
                    if(currentTime == 0) {
                        maxWaitTime = customerWaitTime;
                    }

                    // if statement block that checks if the current Customer's waiting time is greater than
                        // the current maximum amount of time a Customer spent waiting in line that day. If so,
                        // set the new maximum time a Customer spent waiting in line that day, to the amount of time
                        // the current Customer spent waiting in line
                    if(customerWaitTime > maxWaitTime) {
                        maxWaitTime = customerWaitTime;
                    }

                }
                // else statement block that executes if the current server or bank teller's "value" doesn't equal 0 and
                    // decrement the value of the server or bank teller
                else {
                    servers[i]--;
                }

                // if statement block that executes if the current line is empty, the current time is greater than 480
                    // and the number of servers or bank tellers is less than or equal to 6
                if((line1.isEmpty()) && (currentTime > 480) && (numOfServers <= 6)){
                    // Initialize the int Array of servers or bank tellers
                    servers = new int[numOfServers];

                    // for loop to set the value of all the int elements in the servers int Array to 0
                    for(int j = 0; j < numOfServers; j++){
                        servers[j] = 0;
                    }

                    // Calculate the average amount of time a Customer waited in line that day
                    averageWaitTime = totalWaitTime / customersServiced;

                    if(type.length() <=3){
                        // Print to the console the type of Collection Object (PriorityQueue SJF or LJF)
                        System.out.print(type + "\t\t");
                    } else{
                        // Print to the console the type of Collection Object (Queue or Stack)
                        System.out.print(type + "\t");
                    }

                    // Print to the console the number of minutes or simulations the program ran (how long the bank
                        // employees worked for)
                    System.out.print(currentTime + "\t\t");

                    // if statement block to check if the value of currentTime is greater than 1000, if so
                        // add an additional tab so the columns line up correctly
                    if(currentTime < 1000){
                        System.out.print("\t");
                    }
                    // Print to the console the number of servers or bank tellers
                    System.out.print(servers.length + "\t\t\t");

                    // Print to the console the maximum amount of time a Customer waited in line that day
                    System.out.print(maxWaitTime + "\t");
                    // if statement block that checks if the maximum wait time was less than 1000 and if so
                        // add an additional tab so the columns stay lined up correctly
                    if(maxWaitTime < 1000){
                        System.out.print("\t");
                    }
                    // Print to the console the average of amount of time Customer's waited in line that day.
                    System.out.println(averageWaitTime);

                    // Set the value of currentTime to -1
                    currentTime = -1;
                    // Increment the number of servers or bank tellers
                    numOfServers++;
                }
            }
        }
    }


    // boolean method to determine if the current time is considered a busy time of day at the bank or not.
        // This method takes in an int value that represents the current time and returns "true" or "false"
        // depending on whether the current time is inside the blocks of time of the day that are considered to
        // be the busy times of the day. Which include the first and lasts 60 minutes of the day and the middle of the
        // day (210 minutes after open until 270 minutes after open)
    private static boolean isBusyTime(int time){

        return ((time >= 0 && time <= 60) || (time >= 210 && time <= 270) || (time >= 420));
    }


    // int method that returns a random int value based on the boolean argument passed into the method. The boolean
        // argument determines if it is a busy time during the day for the bank. The range of possible random values
        // depends on whether it is a busy part of the day or not
    private static int timeTakenToService(boolean isBusyTime){
        // int variable to store how long it will take to serve the Customer this method will relate to
        int custServTime = 0;
        // Random int variable that stores a randomly generated int value ranging from 1 - 100
        int probability = (randomGenerator.nextInt(100) + 1);

        // if statement block to check if it is currently a busy time of the bank business day. If so,
            // there is six possibilities in which to set the value of the variable to store the amount of time it
            // will take to service this Customer. This is all done by if and else if statement blocks and the value
            // of the randomly generated int value
        if(isBusyTime){
            if(probability > 0 && probability <= 40){
                custServTime = 1;
            } else if(probability > 40 && probability <= 70){
                custServTime = 2;
            } else if(probability > 70 && probability <= 85){
                custServTime = 3;
            } else if(probability > 85 && probability <= 93){
                custServTime = 4;
            } else if(probability > 93 && probability <= 97){
                custServTime = 5;
            } else if(probability > 97 && probability <= 100){
                custServTime = 6;
            }
        }
        // else statement block that executes if it is not currently a busy time of the business day at the bank.
            // if this is the case, there is 9 possible values in which to set the value of the int variable that stores
            // the amount of time it will take to service this Customer based upon the value of the randomly generated
            // in value and if and else if statement blocks that check that variables value
        else{
            if(probability > 0 && probability <= 30){
                custServTime = 1;
            } else if(probability > 30 && probability <= 55){
                custServTime = 2;
            } else if(probability > 55 && probability <= 70){
                custServTime = 3;
            } else if(probability > 70 && probability <= 80){
                custServTime = 4;
            } else if(probability > 80 && probability <= 87){
                custServTime = 5;
            } else if(probability > 87 && probability <= 91){
                custServTime = 6;
            } else if(probability > 91 && probability <= 94){
                custServTime = 7;
            } else if(probability > 94 && probability <= 97){
                custServTime = 8;
            } else if(probability > 97 && probability <= 100){
                custServTime = 9;
            }
        }

        // Return the int variable that stores the amount of time it will take to service the current Customer
        return custServTime;
    }


    // int method to determine how many new Customers got in line at the bank based upon the boolean argument on
        // whether it is considered a busy time of the business day at the bank.
    private static int numberOfNewCustomers(boolean isBusyTime){

        // Initialize an int variable to store the number of new Customers to create to put in line at the bankk.
            // Initialize this variable to 0
        int newCustomers = 0;
        // Random int variable to store a value to be used in the if and else if statement blocks to determine
            // how many new Customers to create and put in line at the bank
        int probability = (randomGenerator.nextInt(100) + 1);

        // if statement block to check if it is currently a busy time of the business day at the bank. If so,
            // there are 7 possible numbers of new Customers to create that range from 0 to 6
        if(isBusyTime){
            if(probability > 0 && probability <= 25){
                newCustomers = 0;
            } else if(probability > 25 && probability <= 45){
                newCustomers = 1;
            } else if(probability > 45 && probability <= 65){
                newCustomers = 2;
            } else if(probability > 65 && probability <= 80){
                newCustomers = 3;
            } else if(probability > 80 && probability <= 90){
                newCustomers = 4;
            } else if(probability > 90 && probability <= 95){
                newCustomers = 5;
            } else if(probability > 95 && probability <= 100){
                newCustomers = 6;
            }

        }
        // else statement block that executes if it is not currently a busy time of the business day at the bank.
            // If this is the case, go through the if and else if statement blocks to figure out how many new Customers
            // to create and put in line at the bank based upon the randomly generated int value variable (probability).
            // In this case, there are 5 possible values that could be assigned to the number of new Customers to create
            // and put in line at the bank. These values range from 0 to 4
        else{
            if(probability > 0 && probability <= 45){
                newCustomers = 0;
            } else if(probability > 45 && probability <= 70){
                newCustomers = 1;
            } else if(probability > 70 && probability <= 85){
                newCustomers = 2;
            } else if(probability > 85 && probability <= 95){
                newCustomers = 3;
            } else if(probability > 95 && probability <= 100){
                newCustomers = 4;
            }
        }

        // Return the number of new Customers to create and put in line at the bank
        return newCustomers;
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// EXAMPLE OUTPUT: BEGINNING //////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
For Queue:
Type 	minutes 	tellers 	max 	average
Queue	2220		2			1741	856
Queue	2099		3			1620	829
Queue	1195		4			715		662
Queue	748			5			269		530
Queue	622			6			269		434


For Stack:
Type 	minutes 	tellers 	max 	average
Stack	2488		2			2488	998
Stack	2514		3			2514	1021
Stack	1231		4			2514	810
Stack	789			5			787		651
Stack	585			6			584		536


For PriorityQueue with "Shortest Job First":
Type 	minutes 	tellers 	max 	average
SJF		2311		2			2096	641
SJF		2587		3			2373	685
SJF		1162		4			1055	533
SJF		792			5			692		425
SJF		643			6			533		343


For PriorityQueue with "Longest Job First":
Type 	minutes 	tellers 	max 	average
LJF		2529		2			2527	1349
LJF		2346		3			2344	1287
LJF		1262		4			1260	1036
LJF		784			5			784		846
LJF		546			6			545		697
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////// EXAMPLE OUTPUT: END /////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////