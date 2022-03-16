package work;

public class PutThread extends Thread {

    static int iterations = 0;
    MyStorage storage;
    int power = 0;
    int id;

    public PutThread(MyStorage storage, int id) {
        this.storage = storage;
        this.id = id;
    }

    @Override
    public void run()    //Этот метод будет выполнен в побочном потоке
    {
        for (iterations = 0; iterations < 10; iterations++){
            power = (int)(Math.random()*2+1);
            storage.put(power, id);
            try {
                sleep((int)(Math.random() * 1000));
            } catch (InterruptedException ignored) { }
        }
    }
}