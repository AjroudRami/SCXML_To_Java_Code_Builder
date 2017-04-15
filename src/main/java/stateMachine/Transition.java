package stateMachine;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rami on 08/03/2017.
 */
public class Transition implements Serializable{


    protected enum Type{
        Internal, External
    }

    private List<String> events;
    //Not used variable, improvement for 2.0
    private List<Guard> guards;
    private List<Event> sentEvents;
    private State to;
    private State from;
    private Type type;

    public Transition(){
        events = new ArrayList<String>();
        guards = new ArrayList<Guard>();
        sentEvents = new ArrayList<Event>();
        type = Type.External;
    }


    public State from() {
        return from;
    }

    public State to() {
        return this.to;
    }

    public boolean raisedFor(String event) {
        for(String str : this.events){
            if(str.equals(event)) return true;
        }
        return false;
    }

    public Transition setTo(State to) {
        this.to = to;
        return this;
    }

    public Transition setFrom(State from) {
        this.from = from;
        return this;
    }

    public Transition addTriggerEvent(String event){
        this.events.add(event);
        return this;
    }

    public Transition addTriggeredEvent(Event e){
        this.sentEvents.add(e);
        return this;
    }

    public void trigger(){
        System.out.println("Triggering transition");
        for(int i=0; i<sentEvents.size(); i++){
            sentEvents.get(i).trigger();
        }
    }

    public boolean hasSentEvent(String name){
        for(Event e : sentEvents){
            if(e.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void connectToEvent(String eventName, Object object, Method method) {
        for(Event e : sentEvents){
            if(e.getName().equals(eventName)){
                int delay = e.getDelay();
                e.addCallable(new Caller(object, method, delay));
            }
        }
    }

    public void connectToEvent(String eventName, Object object, Method method, Object[] args){
        for(Event e : sentEvents){
            if(e.getName().equals(eventName)){
                int delay = e.getDelay();
                e.addCallable(new Caller(object, method, args, delay));
            }
        }
    }

    public void connectToEvent(String eventName, Callable callable){
        for(Event e : sentEvents){
            if(e.getName().equals(eventName)){
                e.addCallable(callable);
            }
        }
    }
}
