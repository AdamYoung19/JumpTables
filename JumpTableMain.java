//imports
import java.util.HashMap;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



// Create Enum for all the states
enum State {
    IDLE,
    STACK,
    QUEUE,
    LIST
}


// Enter/exit (no parameters)
interface StateEnterExitMeth {
    void invoke();
}

// Stay Interface (either true or false)
interface StateStayMeth {
    boolean invoke();
}


// Jump Table Class
class Screen {
    // HashMaps
    private HashMap<State, StateEnterExitMeth> stateEnterMeths;
    private HashMap<State, StateStayMeth> stateStayMeths;
    private HashMap<State, StateEnterExitMeth> stateExitMeths;

    //State Tracker
    private State currentState;


    // Data Structure generics
    private Stack<Character> stack;
    private Queue<Character> queue;
    private ArrayList<Character> list;


    // User input scanner
    private Scanner inputScanner;


    // File name constants
    private final String STACK_FILE = "stack.txt";
    private final String QUEUE_FILE = "queue.txt";
    private final String LIST_FILE = "list.txt";


    public Screen() {
        // Initializer
        stateEnterMeths = new HashMap<>();
        stateStayMeths = new HashMap<>();
        stateExitMeths = new HashMap<>();

        stack = new Stack<>();
        queue = new LinkedList<>();
        list = new ArrayList<>();
        inputScanner = new Scanner(System.in);

        // Populate HashMaps
        stateEnterMeths.put(State.IDLE, this::stateEnterIdle);    
        stateStayMeths.put(State.IDLE, this::stateStayIdle);
        stateExitMeths.put(State.IDLE, this::stateExitIdle); 
        
        stateEnterMeths.put(State.STACK, this::stateEnterStack);
        stateStayMeths.put(State.STACK, this::stateStayStack);
        stateExitMeths.put(State.STACK, this::stateExitStack);
    

        // Set the initial state to IDLE
        currentState = State.IDLE;

        // Manually set the current state to IDLE
        if (stateEnterMeths.containsKey(currentState)){
            stateEnterMeths.get(currentState).invoke();
        }
        
    }
    // Helper function to read files and populate data structure from contents inside
        private void loadFromFile(String fileName, java.util.Collection<Character> dataStructure){
            dataStructure.clear();
            try {
                File file = new File(fileName);
                if (file.exists()) {
                    Scanner fileScanner = new Scanner(file);
                    if (fileScanner.hasNextLine()) {
                        String data = fileScanner.nextLine();

                        //Split string
                        String[] items = data.split(",");

                        // go through the array
                        for (String item : items) {
                            if (!item.isEmpty()) {
                                dataStructure.add(item.charAt(0));
                        }
                    }
                }
                fileScanner.close();
            } 
            } catch (IOException e) {
                System.out.println("Error reading from file: " + fileName);
            }
        }


    //Helper funciton to save data to file
    private void saveToFile(String fileName, java.util.AbstractCollection<Character> dataStructure) {
        try {
            FileWriter writer = new FileWriter(fileName);

            // Loop through data
            for (Character item : dataStructure) {
                writer.write(item + ",");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing file: " + fileName);
        }
    }



    // doState only calls stay method and returns true or false
    public boolean doState() {
        if (stateStayMeths.containsKey(currentState)){
            return stateStayMeths.get(currentState).invoke();
        }
        return false;
    }



    // changeState
    public void changeState(State newState) {

        // Exit current state
        if (currentState != newState) {
            if (stateExitMeths.containsKey(currentState)){
                stateExitMeths.get(currentState).invoke();
            }
        

        // set new state
        currentState = newState;

        // Enter new state
        if (stateEnterMeths.containsKey(currentState)){
            stateEnterMeths.get(currentState).invoke();
        }
        }
    }



    // TODO: create enter, stay, and exit methods for idle, stack, queue, and list states

      //Helper function to clear screen
      private void clearScreen() {
        for (int i = 0; i < 25; ++i) System.out.println();
      }  

      // -- IDLE STATES --

      //idle doesn't load file
      private void stateEnterIdle() {

      }

      private boolean stateStayIdle() {
        clearScreen();

        // Menu
        System.out.println("1. Stack");
        System.out.println("2. Queue");
        System.out.println("3. List");
        System.out.println("4. Quit");
        System.out.print("? ");

        //Read user input
        String input = inputScanner.nextLine().trim();

        switch (input) {
            case "1":
                changeState(State.STACK);
                return true;
            case "2":
                changeState(State.QUEUE);
                return true;
            case "3":
                changeState(State.LIST);
                return true;
            case "4":
                return false;
            default:
                // Something invalid or blank just reprint the menu
                return true;
        }
      }

      // Exit idle state (IDLE doesn't save)
      private void stateExitIdle() {

      }

      
    
    // -- STACK STATES --

    private void stateEnterStack() {
        loadFromFile(STACK_FILE, stack);
    }

    private boolean stateStayStack() {
        clearScreen();

        //Draw Stack
        if (stack.isEmpty()) {
            System.out.println("Empty stack");
        } else {
            for (int i = stack.size() - 1; i >= 0; i--) {
                System.out.println("| " + (stack.get(i)) + " |");
                System.out.println(" |---|");
            }
        }

        // Menu
        System.out.println("1. Push");
        System.out.println("2. Pop");
        System.out.println("3. Save & Move to Queue");
        System.out.println("4. Save & Move to List");
        System.out.println("5. Save & Quit");
        System.out.print("? ");

        // Bonus?
        // Bonus.check(stack);

        //Read user input
        String input = inputScanner.nextLine().trim();

        // User Input Conditions
        if (input.startsWith("1 ")) {
            //push
            if (input.length() >= 3) {
                stack.push(input.charAt(2));
            }
            return true;
        } else if (input.equals("2")) {
            if (!stack.isEmpty()) {
                stack.pop();
            }
            return true;
        } else if (input.equals("3")){
            changeState(State.QUEUE);
            return true;
        } else if (input.equals("4")) {
            changeState(State.LIST);
            return true;
        } else if (input.equals("5")) {
            stateExitStack();
            return false;
        }
        return true; //Redraw menu if invalid input


    }

    private void stateExitStack() {
        saveToFile(STACK_FILE, stack);
    }


}

public class JumpTableMain {
    public static void main(String[] args){
        Screen screen = new Screen();
        boolean keepRunning = true;
        while (keepRunning) {
            keepRunning = screen.doState();
        }
    }
}