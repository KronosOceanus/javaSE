import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {

    BufferedReader br = null;
    public ClientThread(BufferedReader br) throws IOException {
        this.br = br;
    }
    public void run(){
        try{
            String content = null;
            while((content = br.readLine()) != null){
                System.out.println(content);

                /**
                 * 还可添加更复杂的功能
                 * 不过需要相应的协议字符串
                 */
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(br != null){
                    br.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}