package fa.dfa;

import java.util.Objects;

import fa.State;

/**
 * @author Tristan Jones
 */
public class DFAState extends State {
    private String name;

    /**
     * @constructor 
     * 
     * @param name
     * Takes a string for the State class this class extends and uses it 
     * for a custom equals method to compare DFA obj to strings
     */
    public DFAState(String name){
        super(name);
        this.name = name;
        
    }
    /**
     * @return boolean
     * A custom equals method for comparing the state name variable to the 
     * string that will be given in DFA class
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DFAState dfaState = (DFAState) obj;
        return Objects.equals(name, dfaState.name);
    }

    /**
     * @return int
     * returns the hash code of the name variable in this class
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    /**
     * @return String
     * returns the name variable as a string
     */
    @Override
    public String toString() {
        return name;
    }
}
