package work;

public class Main{

    public static void main(String[] args) throws InterruptedException {

        MyStorage storage = new MyStorage();

        PutThread putThread = new PutThread(storage, 1);	//Создание потока
        GetThread getThread = new GetThread(storage, 1);

        getThread.start();                    //Запуск потока
        putThread.start();

        getThread.join();
        System.out.println("GET THREAD END");

        putThread.join();
        System.out.println("PUT THREAD END");

        System.out.println("all threads completed END");

    }
}