import java.io.*;
import java.net.*;

public class ServerThread implements Runnable{

    //定义当前线程需要处理的Socket和BufferedReader
    Socket s = null;
    BufferedReader br = null;

    public ServerThread(Socket s) throws Exception{
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    public void run(){
        try {
            String content = null;
            while ((content = br.readLine()) != null) {

                //将读取到的内容向每个Socket发送一遍实现聊天
                for(Socket s : MyServer.socketList){
                    PrintStream ps = new PrintStream(s.getOutputStream());
                    ps.println(content);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFromClient(){
        try{
            return br.readLine();
        }catch (Exception e){
            MyServer.socketList.remove(s);
        }
        return null;
    }
}
