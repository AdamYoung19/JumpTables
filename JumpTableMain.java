//imports
import java.util.HashMap;


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
class JumpTable {
    // HashMaps
    private HashMap<State, StateEnterExitMeth> stateEnterMeths;
    private HashMap<State, StateStayMeth> stateStayMeths;
    private HashMap<State, StateEnterExitMeth> stateExitMeths;

    //State Tracker
    private State currentState;


}

public class JumpTableMain {
    public static void main(String[] args){
        
    }
}