package stateMachine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rami on 08/03/2017.
 */
public abstract class AbstractStateMachine implements Serializable {

    private List<Transition> transitionList;
    private List<State> stateList;
    private State initState;
    private State currentState;

    public AbstractStateMachine(){
        this.transitionList = new ArrayList<Transition>();
        this.stateList = new ArrayList<State>();
    }

    protected void linkStates(){
        for(Transition transition : transitionList){
            for(State state : stateList){
                if(initState == null && state.isInit()){
                    this.initState = state;
                }
                if(state.getId().equals(transition.from().getId())){
                    state.addTransition(transition);
                    transition.setFrom(state);
                }
                if(state.getId().equals(transition.to().getId())){
                    transition.setTo(state);
                }
            }
        }
        if(initState == null && stateList.size() != 0){
            initState = stateList.get(0);
        }
    }

    protected void init(){
        this.currentState = initState;
    }

    protected void notifyEvent(String event){
        this.currentState = this.currentState.notifyEvent(event);
    }
}
