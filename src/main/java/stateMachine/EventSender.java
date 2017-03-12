package stateMachine;

/**
 * Created by Rami on 12/03/2017.
 */
public class EventSender extends Caller {

    public EventSender(String event, AbstractStateMachine stateMachine, int delay){
        super.calledObject = stateMachine;
        super.args = new String[] {event};
        super.delay = delay;
        try {
            super.calledMethod = super.calledObject.getClass().getMethod("notifyEvent", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void call(){
        super.call();
    }
}
