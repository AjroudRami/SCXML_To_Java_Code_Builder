package stateMachine;
/**
Generated By SCXMLToJava class
**/
public class StateMachine{
public StateMachine(){
this.states.add( new State(State_2,false));
this.transitions.add(new Transition(new State("State_2"), new State("State_3")));
this.transitions.add(new Transition(new State("State_2"), new State("State_3")));
this.states.add( new State(State_3,false));
this.transitions.add(new Transition(new State("State_3"), new State("State_2")));
}}