package work;

public class MyStorage {
    static int storageAvailable = 10;
    static int takenPlace = 0;

    public synchronized void get(int power, int personalNumber) {
        while(takenPlace - power < 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("C "+ personalNumber + " Customer consume: " + power);
        takenPlace -= power;
        System.out.println("  NOWg " + takenPlace);
        notify();
    }

    public synchronized void put(int count, int personalNumber) {
        while (takenPlace + count >= storageAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("M "+ personalNumber + " Manufacturer put: " + count);
        takenPlace += count;
        System.out.println("  NOWp " + takenPlace);
        notify();
    }

}
