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
            try{
                Event e = getNextEvent();
                if(e != null){
                    System.out.println("Sending event " + e.getName());
                    stateMachine.triggerEvent(e.getName());
                }
            }catch(Exception e){
                System.err.println(e.getMessage());
            }

        }
    }

    public void stop(){
        this.run = false;
    }

    public void start(){
        this.run = true;
    }

    private synchronized Event getNextEvent(){
        try {
        for(int i = 0; i<eventsStack.size(); i++){
            if(eventsStack.get(i).getType().equals(Event.Type.SEND)){
                return eventsStack.remove(i);
            }
        }
        }catch (java.lang.NullPointerException e){
            System.err.println(e.getMessage());
        }
        try {
            for (int i = 0; i < eventsStack.size(); i++) {
                if (eventsStack.get(i).getType().equals(Event.Type.RAISE)) {
                    return eventsStack.remove(i);
                }
            }
        }catch (java.lang.NullPointerException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public synchronized void notifyEvent(Event e) {
        System.out.println("adding event " + e.getName());
        this.eventsStack.add(e);
    }
}
