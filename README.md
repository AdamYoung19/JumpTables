# JumpTables
Assignment for Advance data structures about the use of jump tables



This is a Java-based console application built for CSC 325 Advanced Data Structures. It serves as an interactive visualizer for three fundamental data structures: Stacks, Queues, and Lists.

The application is engineered entirely around a Finite State Machine (FSM) architecture, completely avoiding standard switch statement menus for state transitions. Instead, it utilizes three HashMap-based "Jump Tables" mapped to specific interfaces (StateEnterExitMeth, StateStayMeth) to handle entering, maintaining, and exiting application states.  

Core FeaturesFinite State Machine Architecture: Uses HashMap jump tables to route execution logic blindly based on the current State enum (IDLE, STACK, QUEUE, LIST).   

Persistent File I/O: Automatically saves and loads data structure contents to text files (stack.txt, queue.txt, list.txt) during state transitions.   

Dynamic Visualization: Renders standard ASCII-art representations of each data structure dynamically based on user input (push, pop, enqueue, dequeue, append, remove).   

ANSI Color Output: Integrates terminal escape codes to format the stack in red, the queue in green, the list in blue, and the menus in yellow.   

Included FilesJumpTableMain.java: The primary source file containing the complete application logic, Screen class, state methods, and the main entry point.   

Bonus.class: A pre-compiled dependency used to verify data structure states and trigger a hidden terminal Easter egg.   

stack.txt, queue.txt, list.txt: Local storage files generated and managed by the application's File I/O logic.   

Execution RequirementsThis application relies on ANSI escape codes and specific pre-compiled dependencies. Standard Windows Command Prompt is not recommended.  

Recommended Environment: Git Bash or a similar Linux-like Bash shell. PowerShell may render colors but can experience compatibility issues with the .class execution.   

How to RunOpen your terminal and navigate to the project directory.Compile the Java file:
Bash
javac JumpTableMain.java

Execute the compiled program:
Bash
java JumpTableMain


Follow the on-screen yellow menu prompts to navigate between the data structures and modify their contents.