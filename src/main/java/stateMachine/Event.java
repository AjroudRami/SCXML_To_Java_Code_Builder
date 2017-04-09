package stateMachine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rami on 08/03/2017.
 */
public class Event implements Serializable {
    private String name;
    private List<Callable> callables;
    private Type type;

    public enum Type {SEND, RAISE}

    public Event(String name){
        this.name = name;
        this.type = Type.SEND;
        this.callables = new ArrayList<Callable>();
    }

    public void trigger(){
        for(int i=0; i<callables.size(); i++){
            callables.get(i).call();
        }
    }

    public String getName(){
        return this.name;
    }

    public Event addCallable(Callable c){
        this.callables.add(c);
        return this;
    }

    public int getDelay(){
        int delay = 0;
        for(Callable c : this.callables){
            return c.getDelay();
        }
        return delay;
    }

    public Type getType(){
        return this.type;
    }

    public Event setType(Type t){
        this.type = t;
        return this;
    }
}
