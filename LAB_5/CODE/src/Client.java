import java.io.*;
import java.net.Socket;
import java.util.Locale;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                // адрес - локальный хост, порт - 4004, такой же как у сервера
                clientSocket = new Socket("localhost", 4004); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                while (true) {

                    System.out.println("Enter Math / Exit:");
                    String menu = reader.readLine();
                    if (menu.toLowerCase(Locale.ROOT).equals("exit"))
                        break;

                    // если соединение произошло и потоки успешно созданы - мы можем
                    //  работать дальше и предложить клиенту что то ввести
                    // если нет - вылетит исключение
                        System.out.print("\nFirst number: ");
                    double firstNumber = Double.parseDouble(reader.readLine());
                        System.out.print("Operation(+, -, /, *): ");
                    String operation = reader.readLine();
                        System.out.print("Second number: ");
                    double secondNumber = Double.parseDouble(reader.readLine());

                    // не напишет в консоль
                    out.write(firstNumber + "\n"); // отправляем сообщение на сервер
                    out.flush();
                    out.write(operation + "\n"); // отправляем сообщение на сервер
                    out.flush();
                    out.write(secondNumber + "\n"); // отправляем сообщение на сервер
                    out.flush();

                    String serverWord = in.readLine(); // ждём, что скажет сервер
                    System.out.println(serverWord); // получив - выводим на экран
                }

            } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (Exception e) {
            System.out.print("Trouble with server ");
            System.err.println(e.getMessage());
        }
    }
}