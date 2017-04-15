package exemple;

import stateMachine.StateMachine;

/**
 * Created by user on 15/04/2017.
 */
public class Main {


    public static void main(String[] args){

        StateMachine sm = new GStateMachine();
        Porte porte = new Porte(sm);

        try {
            sm.connectToEvent("open", porte, porte.getClass().getMethod("open"));
            sm.connectToEvent("close", porte, porte.getClass().getMethod("close"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Thread t = new Thread(porte);
        t.start();
        sm.start();

        //On simule l'arrivée d'une personne
        sm.notifyEvent("someone");
    }
}
