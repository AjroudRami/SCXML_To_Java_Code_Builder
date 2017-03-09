package stateMachine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rami on 08/03/2017.
 */
public class Transition implements Serializable{


    public boolean raisedFor(String event) {
        for(String str : this.events){
            if(str.equals(event)) return true;
        }
        return false;
    }

    protected enum Type{
            Internal, External;
    }

        private List<String> events;
        private List<Guard> guards;
        private State to;
        private State from;
        private Type type;

        Transition(){};


    public State from() {
        return from;
    }
    public State to() {
        return this.to;
    }

    public void setTo(State to) {
        this.to = to;
    }

    public void setFrom(State from) {
        this.from = from;
    }
}
