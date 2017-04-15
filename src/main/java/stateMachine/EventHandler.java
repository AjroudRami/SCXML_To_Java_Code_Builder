package stateMachine;

import java.util.ArrayList;

/**
 * Created by Rami on 08/04/2017.
 */
public class EventHandler implements Runnable {

    private ArrayList<Event> eventsStack;
    private AbstractStateMachine stateMachine;
    private boolean run;

    public EventHandler(AbstractStateMachine stateMachine){
        this.eventsStack = new ArrayList<Event>();
        this.stateMachine = stateMachine;
        this.run = false;
    }


    public void run() {
        start();
        while(run){
            Event e = getNextEvent();
            if(e != null){
                stateMachine.triggerEvent(e.getName());
            }
        }
    }

    public void stop(){
        this.run = false;
    }

    public void start(){
        this.run = true;
    }

    private Event getNextEvent(){
        for(int i = 0; i<eventsStack.size(); i++){
            if(eventsStack.get(i).getType().equals(Event.Type.SEND)){
                return eventsStack.remove(i);
            }
        }
        for(int i = 0; i<eventsStack.size(); i++){
            if(eventsStack.get(i).getType().equals(Event.Type.RAISE)){
                return eventsStack.remove(i);
            }
        }
        return null;
    }

    public void notifyEvent(Event e) {
        System.out.println("adding event " + e.getName());
        this.eventsStack.add(e);
    }
}
