package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import fa.State;

public class DFA implements DFAInterface {
    private LinkedHashSet<DFAState> states;
    private LinkedHashSet<Character> sigma;
    private LinkedHashSet<DFAState> startState;
    private LinkedHashSet<DFAState> finalState;
    private Map<DFAState, Map<Character, DFAState>> transitions;

    public DFA() {
        states = new LinkedHashSet<DFAState>();
        sigma = new LinkedHashSet<Character>();
        startState = new LinkedHashSet<>();
        finalState = new LinkedHashSet<>();
        transitions = new HashMap<>();
    }

    @Override
    public boolean addState(String name) {
        
        

        return states.add(new DFAState(name));

    }

    @Override
    public boolean setFinal(String name) {

        for (DFAState DFAState : states) {
            if (DFAState.equals(name)) {
                finalState.add(DFAState);
                return true;
            }
        }

        return false;

    }

    @Override
    public boolean setStart(String name) {
        if(startState.size() == 0){
            startState.add(new DFAState(name));
                return true;
        }
        for (DFAState DFAState : states) {
            if (DFAState.equals(name) && (startState.size() == 0)) {
                startState.add(DFAState);
                return true;
            

            }
        }
        return false;
    }

    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        boolean doesContain = true;

        for (int i = 0; i < s.length(); i++) {
            if (!(sigma.contains(s.charAt(i)))) {
                doesContain = false;
                break;
            }
        }
        return doesContain;
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        for (State state : states) {
            if (state.equals(name)) {
                return state;
            }

        }
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        for (DFAState state : finalState) {
            if (state.equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isStart(String name) {
        return startState.contains(name);
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        // Find the source and destination states
        DFAState source = null;
        DFAState destination = null;

        for (DFAState state : states) {
            if (state.getName().equals(fromState)) {
                source = state;
            }
            if (state.getName().equals(toState)) {
                destination = state;
            }
        }

        // If either state is not found, return false
        if (source == null || destination == null) {
            return false;
        }

        // Add the transition
        transitions.putIfAbsent(source, new HashMap<>());
        Map<Character, DFAState> stateTransitions = transitions.get(source);

        if (stateTransitions.containsKey(onSymb)) {
            // Transition for this symbol already exists; return false to indicate failure
            return false;
        }

        stateTransitions.put(onSymb, destination);
        return true;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        // Create a new DFA
        DFA newDFA = new DFA();

        // Copy states
        for (DFAState state : states) {
            newDFA.addState(state.getName());
            if (finalState.contains(state)) {
                newDFA.setFinal(state.getName());
            }
            if (startState.contains(state)) {
                newDFA.setStart(state.getName());
            }
        }

        // Copy transitions with swapped labels
        for (DFAState fromState : transitions.keySet()) {
            Map<Character, DFAState> stateTransitions = transitions.get(fromState);

            for (Map.Entry<Character, DFAState> entry : stateTransitions.entrySet()) {
                char symbol = entry.getKey();
                DFAState toState = entry.getValue();

                // Swap the transition label if necessary
                if (symbol == symb1) {
                    newDFA.addTransition(fromState.getName(), toState.getName(), symb2);
                } else if (symbol == symb2) {
                    newDFA.addTransition(fromState.getName(), toState.getName(), symb1);
                } else {
                    newDFA.addTransition(fromState.getName(), toState.getName(), symbol);
                }
            }
        }

        return newDFA;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // 1. States (Q)
        sb.append("Q = { ");
        for (DFAState state : states) {
            sb.append(state.getName()).append(" ");
        }
        sb.append("}\n");

        // 2. Alphabet (Sigma)
        sb.append("Sigma = { ");
        for (Character symbol : sigma) {
            sb.append(symbol).append(" ");
        }
        sb.append("}\n");

        // 3. Transition Table (delta)
        sb.append("delta =\n");
        sb.append("\t"); // Initial tab for column headers
        for (Character symbol : sigma) {
            sb.append(symbol).append("\t");
        }
        sb.append("\n");

        for (DFAState state : states) {
            sb.append(state.getName()).append("\t"); // Row header (state name)
            Map<Character, DFAState> stateTransitions = transitions.get(state);

            for (Character symbol : sigma) {
                if (stateTransitions != null && stateTransitions.containsKey(symbol)) {
                    sb.append(stateTransitions.get(symbol).getName()).append("\t");
                } else {
                    sb.append("-\t"); // Use "-" to indicate no transition
                }
            }
            sb.append("\n");
        }

        // 4. Start State (q0)
        sb.append("q0 = ");
        for (DFAState state : startState) {
            sb.append(state.getName());
            break; // Only one start state is expected
        }
        sb.append("\n");

        // 5. Final States (F)
        sb.append("F = { ");
        for (DFAState state : finalState) {
            sb.append(state.getName()).append(" ");
        }
        sb.append("}\n");

        return sb.toString();
    }

}
