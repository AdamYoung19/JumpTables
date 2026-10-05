//imports
import java.util.HashMap;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Scanner;


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

}

public class JumpTableMain {
    public static void main(String[] args){
        
    }
}