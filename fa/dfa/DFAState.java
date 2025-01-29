package fa.dfa;

import java.util.Objects;

import fa.State;

public class DFAState extends State {
    private String name;
    public DFAState(String name){
        super(name);
        this.name = name;
        
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DFAState dfaState = (DFAState) obj;
        return Objects.equals(name, dfaState.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
