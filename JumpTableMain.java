//imports
import java.util.HashMap;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;



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


    public Screen() {
        // Initializer
        stateEnterMeths = new HashMap<>();
        stateStayMeths = new HashMap<>();
        stateExitMeths = new HashMap<>();

        stack = new Stack<>();
        queue = new LinkedList<>();
        list = new ArrayList<>();
        inputScanner = new Scanner(System.in);


        // DO Later: add methods to hashmaps




        


        // Set the initial state to IDLE
        currentState = State.IDLE;

        // Manually set the current state to IDLE
        if (stateEnterMeths.containsKey(currentState)){
            stateEnterMeths.get(currentState).invoke();
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