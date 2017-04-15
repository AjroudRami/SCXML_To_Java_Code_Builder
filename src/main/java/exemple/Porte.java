package exemple;

import stateMachine.StateMachine;

import java.lang.reflect.InvocationTargetException;

import static java.lang.Thread.sleep;

/**
 * Created by user on 15/04/2017.
 */
public class Porte implements Runnable{

    private StateMachine sm;

    public Porte(StateMachine machine){
        this.sm = machine;
    }


    public void run() {
        new Thread(new Runnable(){
            public void run(){
                while(true){

                }
            }
        }).start();
    }

    //Simule l'ouverture de la porte
    public void open(){
        try {
            Thread.sleep(1000);
            sm.notifyEvent("opened");
            System.out.println("porte ouverte");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            Thread.sleep(1000);
            sm.notifyEvent("closed");
            System.out.println("porte fermé");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
