package fa.dfa;

/**
 * This is a class to help test out some of the DFA functionality before 
 * running unit tests
 */

public class test {
   public static void main(String[] args) {
      DFA dfa = new DFA();

      dfa.addSigma('0');
		dfa.addSigma('1');
		
		System.out.println(dfa.addState("a"));
		System.out.println(dfa.addState("b"));
		System.out.println(dfa.setStart("a"));
		System.out.println(dfa.setFinal("b"));
		
		System.out.println(dfa.addState("a"));
		System.out.println(dfa.setStart("c"));
		System.out.println(dfa.setFinal("c"));
		
		System.out.println(dfa.addTransition("a", "a", '0'));
		System.out.println(dfa.addTransition("a", "b", '1'));
		System.out.println(dfa.addTransition("b", "a", '0'));
		System.out.println(dfa.addTransition("b", "b", '1'));
		
		System.out.println(dfa.addTransition("c", "b", '1'));
		System.out.println(dfa.addTransition("a", "c", '1'));
		System.out.println(dfa.addTransition("a", "b", '2'));

      System.out.println(dfa.toString());
   }
}
