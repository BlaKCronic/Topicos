package org.example.topicos.components;

public class CorredorThread extends Thread{

    public CorredorThread(String name){
        super(name);
    }

    @Override
    public void run() {
        super.run();
        System.out.println(this.getName() + " en la salida");
        for (int i = 1 ; i < 10; i++){
            try {
                Thread.sleep((long)(Math.random()*2000));
                System.out.println(this.getName() + " completó el kilometro " + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(this.getName() + " llegó a la meta");
    }
}
