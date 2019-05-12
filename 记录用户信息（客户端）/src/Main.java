import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        //接收与发送信息
        client.init();
        client.readAndSent();
    }
}
