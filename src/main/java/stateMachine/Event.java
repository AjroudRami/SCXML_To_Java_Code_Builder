package stateMachine;

import java.io.Serializable;

/**
 * Created by Rami on 08/03/2017.
 */
public class Event implements Serializable {
    private String name;

    public Event(String name){
        this.name = name;
    }
}
