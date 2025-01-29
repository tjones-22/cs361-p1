package fa.dfa;

public class test {
   public static void main(String[] args) {
    DFA dfa = new DFA();

    dfa.addState("state1");
    dfa.addState("state2");
    System.out.println(dfa.toString());
   }
}
