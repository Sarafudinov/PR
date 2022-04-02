import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private static double firstNumber;
    private static String operation;
    private static double secondNumber;

    public static void main(String[] args) {
        try {
            try  {
                server = new ServerSocket(4004); // серверсокет прослушивает порт 4004
                System.out.println("Сервер запущен!"); // хорошо бы серверу
                //   объявить о своем запуске
                clientSocket = server.accept(); // accept() будет ждать пока
                //кто-нибудь не захочет подключиться
                try { // установив связь и воссоздав сокет для общения с клиентом можно перейти
                    // к созданию потоков ввода/вывода.
                    // теперь мы можем принимать сообщения
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // и отправлять
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    while (true) {
                        // ждём пока клиент что-нибудь нам напишет
                        firstNumber = Double.parseDouble(in.readLine());
                        operation = in.readLine();
                        secondNumber = Double.parseDouble(in.readLine());

                        String result = firstNumber + " " + operation + " " + secondNumber + " = " +
                                        solutionResult(firstNumber, operation, secondNumber);
                        System.out.println(result);
                        // не долго думая отвечает клиенту
                        out.write("Привет, это Сервер! Подтверждаю, вы написали : " + result + "\n");
                        out.flush(); // выталкиваем все из буфера
                    }

                } finally { // в любом случае сокет будет закрыт
                    clientSocket.close();
                    // потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static double solutionResult(double firstNumber, String operation, double secondNumber) {
        double result = 0;

        if (operation.equals("/") && secondNumber == 0){
            System.out.println("Stupid user divides by 0");
            return result;
        }

        switch (operation){
            case "+" ->{ result = firstNumber + secondNumber; }
            case "-" ->{ result = firstNumber - secondNumber; }
            case "*" ->{ result = firstNumber * secondNumber; }
            case "/" ->{ result = firstNumber / secondNumber; }
        }

        return result;
    }
}