import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HEAD {
    private static HttpURLConnection connection;
    static BufferedReader reader;
    static String line;
    static StringBuffer responseContent = new StringBuffer();

    public static void main(String[] args) throws IOException {

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/albums/1");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);

            String message = connection.getResponseMessage();
            System.out.println(message);

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }
            reader.close();

            System.out.println(responseContent.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
        }

    }
}
