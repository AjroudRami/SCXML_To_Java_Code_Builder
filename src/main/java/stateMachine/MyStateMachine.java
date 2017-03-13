package stateMachine;

/**
 * Created by Rami on 12/03/2017.
 */
public class MyStateMachine extends GStateMachine {

    public MyStateMachine(){
        super();
        try {
            this.connectToEvent("print", this, this.getClass().getDeclaredMethod("printHello"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void printHello(){
        System.out.println("Hello!");
    }



}
