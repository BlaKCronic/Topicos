package org.example.topicos.components;

public class CorredorThread extends Thread{

    public CorredorThread(String name){
        super(name);
    }

    @Override
    public void run() {
        super.run();
        for (int i = 1 ; i < 10; i++){
            try {
                Thread.sleep((long)(Math.random()*2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
