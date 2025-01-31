package fa.dfa;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import fa.State;

/**
 * @author Tristan Jones
 * @author Bryce Kratzer
 * 
 *  This class builds a DFA object for a user to be able to add states
 *  and connect them given the alphabet of their choice
 */

public class DFA implements DFAInterface {
    // instance variables

    private LinkedHashSet<DFAState> states;
    private LinkedHashSet<Character> sigma;
    private LinkedHashSet<DFAState> startState;
    private LinkedHashSet<DFAState> finalState;
    private Map<DFAState, Map<Character, DFAState>> transitions;

    /**
     * @constructor
     *              constructor for DFA object
     */
    public DFA() {
        states = new LinkedHashSet<DFAState>();
        sigma = new LinkedHashSet<Character>();
        startState = new LinkedHashSet<>();
        finalState = new LinkedHashSet<>();
        transitions = new HashMap<>();
    }

    @Override
    public boolean addState(String name) {
        DFAState newState = new DFAState(name);

        if (states.contains(newState)) {
            return false;
        }

        return states.add(newState);
    }

    @Override
    public boolean setFinal(String name) {

        for (DFAState DFAState : states) {
            if (DFAState.getName().equals(name)) {
                finalState.add(DFAState);
                return true;
            }
        }

        return false;

    }

    @Override
    public boolean setStart(String name) {
        if (startState.size() == 0) {
            for (DFAState DFAState : states) {
                if (DFAState.getName().equals(name)) {
                    startState.add(DFAState);
                    return true;
                }
            }

        } else {
            for (DFAState DFAState : states) {
                if (DFAState.getName().equals(name)) {
                    startState.clear();
                    startState.add(DFAState);
                    return true;
                }
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
        if (startState.isEmpty())
            return false;

        DFAState currentState = startState.iterator().next();

        for (char c : s.toCharArray()) {
            if (!sigma.contains(c))
                return false;

            Map<Character, DFAState> trans = transitions.get(currentState);
            if (trans == null || !trans.containsKey(c))
                return false;

            currentState = trans.get(c);
        }

        return finalState.contains(currentState);
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        for (State state : states) {
            if (state.getName().equals(name)) {
                return state;
            }

        }
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        for (DFAState state : finalState) {
            if (state.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isStart(String name) {
        for (DFAState dfaState : startState) {
            if (dfaState.getName().equals(name)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {

        if (!sigma.contains(onSymb))
            return false;

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

        if (stateTransitions.containsKey(onSymb))return true;
        

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

        // Copy sigma set
        for (Character symbol : sigma) {
            newDFA.addSigma(symbol);
        }

        // Copy transitions with swapped labels
        for (DFAState fromState : transitions.keySet()) {
            Map<Character, DFAState> stateTransitions = transitions.get(fromState);

            // Grabbing specific state tansition
            for (Map.Entry<Character, DFAState> entry : stateTransitions.entrySet()) {
                char symbol = entry.getKey();
                DFAState toState = entry.getValue();

                // Determine which symbol to use
                char transitionSymbol;
                if (symbol == symb1) {
                    transitionSymbol = symb2;
                } else if (symbol == symb2) {
                    transitionSymbol = symb1;
                } else {
                    transitionSymbol = symbol;
                }

                // Add the transition
                newDFA.addTransition(fromState.getName(), toState.getName(), transitionSymbol);
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
        sb.append("\t"); // Column headers for symbols
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
                    sb.append("-\t"); // Indicate no transition
                }
            }
            sb.append("\n");
        }

        // 4. Start State (q0)
        sb.append("q0 = ");
        if (!startState.isEmpty()) {
            sb.append(startState.iterator().next().getName());
        } else {
            sb.append("None"); // No start state set
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
