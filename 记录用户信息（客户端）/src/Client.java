import javax.swing.*;
import java.io.*;
import java.net.*;

public class Client {
    private static int SERVER_POST = 30000;
    private Socket socket;
    private PrintStream ps;
    private BufferedReader br;
    //代表键盘的输入流
    private BufferedReader keyIn;

    public void init() throws IOException {
        try {
            keyIn = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket("127.0.0.1",SERVER_POST);
            ps = new PrintStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String tip = "";
            //不断要求输入用户名
            while (true){
                String userName = JOptionPane.showInputDialog(tip + "输入用户名");
                //加上协议字符串然后发送
                ps.println(CrazyitProtocol.USER_ROUND + userName + CrazyitProtocol.USER_ROUND);
                String result = br.readLine();
                //名字重复，服务端会发送NAME_REP字符串
                if(result.equals(CrazyitProtocol.NAME_REP)){
                    tip = "用户名重复！请重新";
                    continue;
                }
                //登陆成功
                if (result.equals(CrazyitProtocol.LOGIN_SUCCESS)){
                    break;
                }
            }
        }
        //服务器异常
        catch (UnknownHostException uhe){
            System.out.println("找不到远程服务器！");
            closeRs();
            System.exit(1);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        //登陆成功且没有发生异常，启动客户端处理线程（收到消息才需要启动线程）
        new ClientThread(br).start();
    }
    //读取键盘输入，向网络发送
    public void readAndSent(){
        try{
            String line = null;
            while((line = keyIn.readLine()) != null){
                //私聊
                if(line.indexOf(":") > 0 && line.startsWith("//")){
                    //子串起始位置
                    line = line.substring(2);
                    ps.println(CrazyitProtocol.PRIVATE_ROUND +
                        line.split(":")[0] + CrazyitProtocol.SPLIT_SIGN +
                            line.split(":")[1] + CrazyitProtocol.PRIVATE_ROUND);
                }
                //公聊
                else {
                    ps.println(CrazyitProtocol.MSG_ROUND +
                        line + CrazyitProtocol.MSG_ROUND);
                }
            }
        }catch (IOException e){
            System.out.println("网络连接异常，请重新登陆！");
            closeRs();
            System.exit(1);
        }
    }

    //关闭异常资源
    private void closeRs(){
        try{
            //Socket与br由服务器端来关闭
            if(keyIn != null || br != null || ps != null){
                ps.close();
            }
            if (socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}