package work;

public class GetThread extends Thread
{
    MyStorage storage;
    int power = 0;
    int id;

    public GetThread(MyStorage storage, int id) {
        this.storage = storage;
        this.id = id;
    }

    @Override
    public void run()	//Этот метод будет выполнен в побочном потоке
    {
        for (int i = 0; i < 10; i++){
            power = (int)(Math.random()*2+1);
            storage.get(power, id);
            if (PutThread.iterations >= 10 && MyStorage.takenPlace == 0){
                System.out.println("Everything is sold!!!!! Buyers left " + (10 - i));
                break;
            }

            try {
                sleep((int)(Math.random() * 1000));
            } catch (InterruptedException ignored) { }
        }
        if (PutThread.iterations >= 10 && MyStorage.takenPlace != 0) {
            System.out.println("Buyers are gone!!!!! Items left " + MyStorage.takenPlace);
        }
    }
}

