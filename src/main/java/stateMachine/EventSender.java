package stateMachine;

/**
 * Created by Rami on 12/03/2017.
 */
public class EventSender extends Caller {

    public EventSender(String event, AbstractStateMachine stateMachine){
        super.calledObject = stateMachine;
        super.args = new String[] {event};
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
