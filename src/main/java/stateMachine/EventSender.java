package stateMachine;

/**
 * Created by Rami on 12/03/2017.
 */
public class EventSender extends Caller {

    public EventSender(String event, AbstractStateMachine stateMachine, int delay){
        super.calledObject = stateMachine;
        super.args = new Event[] {new Event(event).setType(Event.Type.SEND) };
        super.delay = delay;
        try {
            super.calledMethod = super.calledObject.getClass().getMethod("notifyEvent", Event.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void call(){
        super.call();
    }
}
