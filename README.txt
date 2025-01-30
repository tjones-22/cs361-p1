# Project #1: P1

* Author: Tristan Jones, Bryce Kratzer
* Class: CS361 Section 001 
* Semester: Spring 2025

## Overview

This project implements the ability to create a deterministic finite automata. The functions are 
implemented through the DFAInterface and FAInterface. The finite automata uses the 
abstract set class to represent states, their transitions, the finite automata's start state, and final state.

## Reflection

Write a brief (2-3 paragraph) reflection describing your experience with this 
project. Answer the following questions (but feel free to add other insights): 
- What worked well and what was a struggle?
- What concepts still aren't quite clear?
- What techniques did you use to make your code easy to debug and modify?
- What would you change about your design process?
- If you could go back in time, what would you tell yourself about doing this project?


The biggest struggle was going creating the transitions using a map. I've worked with hash maps a in CS321 but not enough to say I'm an expert.
So it took some time reading the documentation and testing to see if it transitions properly. What worked well was following the interface and
creating a custom equals method. The custom method made life simpler when finding states based on a string. To make it easy to debug, I tried making
the code easy to read and adding comments when necessary. We would use the debugger on the tests to see what was getting returned and use that to 
modify the method that was failing.
The only thing I can think about changing the design is taking more time to think about other structures that can be used to make searching for states 
quicker. We are searching linearly so maybe having an id on the states as well could help eliminate some states. I would tell myself in the past to look 
at the test cases to help add checks for certain methods so we won't scratch our heads debugging the code. There isn't any concepts that aren't clear to
me and that helped me build the class quicker.
## Compiling and Using

To run the program follow these steps:

- Verify you are in the same directory that has the folders:
    * fa
    * lib
    * test
- Compile the program using the following command (your working directory will be different)
    javac -cp .:/usr/share/java/junit.jar ./test/dfa/DFATest.java
- Run the tests using the following command:
    java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar org.junit.runner.JUnitCore test.dfa.DFATest


To use the program, see the provided DFATest.java file. This test suite demonstrates the 
creation and validation of deterministic finite automata (DFA) through various test cases. The tests verify 
proper DFA construction, state transitions, and string acceptance.
If you would like to create your own tests, follow these steps:

Create a private DFA initialization method following the pattern shown in dfa1(), dfa2(), dfa3(), and dfa4().
This method should:

- Initialize a new DFA object
- Add symbols to the alphabet (sigma)
- Add states and designate start/final states
- Define transitions between states
- Return the constructed DFA


Add test methods annotated with @Test that:

- Call your initialization method
- Test specific behaviors using assertions
- Verify string acceptance/rejection
- Check state properties and transitions
- Validate toString() output if needed

Example template for a new test case:
private DFA dfaNew() {
    DFA dfa = new DFA();
    dfa.addSigma('0');  // Add alphabet symbols
    
    dfa.addState("q0");  // Add states
    dfa.setStart("q0");  // Set start state
    dfa.setFinal("q0");  // Set final states
    
    dfa.addTransition("q0", "q0", '0');  // Add transitions
    
    return dfa;
}

@Test
public void testNew_1() {
    DFA dfa = dfaNew();
    assertTrue(dfa.accepts("0"));  // Test string acceptance
    // Add additional assertions as needed
}
----------
This README template is using Markdown. To preview your README output,
you can copy your file contents to a Markdown editor/previewer such
as [https://stackedit.io/editor](https://stackedit.io/editor).