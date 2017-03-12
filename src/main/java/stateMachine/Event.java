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

    public Event(String name){
        this.name = name;
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
}