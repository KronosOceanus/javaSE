import java.io.*;
import java.net.*;

/**
 * 得到网络连接与输入输出流
 * 读取，根据协议字符串处理信息（从字符串中得到真实信息）
 * 异常处理：移除异常的映射关系，关闭相应的网络，IO资源
 */
public class ServerThread extends Thread{

    private Socket socket;
    BufferedReader br = null;
    PrintStream ps = null;
    public ServerThread(Socket socket){
        this.socket = socket;
    };

    public void init(){
        try {
            //获取Scoket对应的输入输出流
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());
            String line  = null;
            while((line = br.readLine()) != null){
                //判断用户名
                if(line.startsWith(CrazyitProtocol.USER_ROUND)
                    && line.endsWith(CrazyitProtocol.USER_ROUND))
                {
                    //得到真实信息
                    String userName = getRealMsg(line);
                    //用户名重复
                    if(Server.clients.map.containsKey(userName)){
                        System.out.println("重复");
                        //发送用户名重复标志
                        ps.println(CrazyitProtocol.NAME_REP);
                    }
                    else {
                        System.out.println("成功");
                        ps.println(CrazyitProtocol.LOGIN_SUCCESS);
                        //在服务器端保存用户名与输出流的映射关系
                        Server.clients.put(userName,ps);
                    }
                }
                //私聊消息
                else if(line.startsWith(CrazyitProtocol.PRIVATE_ROUND)
                    && line.endsWith(CrazyitProtocol.PRIVATE_ROUND))
                {
                    String userAndMsg = getRealMsg(line);
                    //使用分隔标志将用户名与消息分隔为String数组
                    String user = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[0];
                    String msg = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[1];
                    //得到对应输出流并且输出
                    Server.clients.map.get(user).println(Server.clients.getKeyByValue(ps)
                        + "悄悄对你说：" + msg);
                }
                //公聊信息
                else{
                    String msg = getRealMsg(line);
                    //遍历输出流
                    for(PrintStream clientPs : Server.clients.valueSet()){
                        clientPs.println(Server.clients.getKeyByValue(clientPs)
                            + "说：" + msg);
                    }
                }
            }
        }catch (IOException e){
            //移除映射关系
            Server.clients.removeByValue(ps);
            System.out.println(Server.clients.map.size());
            //关闭网络，IO资源
            try{
                if(br != null){
                    br.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(socket != null){
                    socket.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    public String getRealMsg(String line){
        return line.substring(CrazyitProtocol.PORTOCOL_LEN,
                line.length() - CrazyitProtocol.PORTOCOL_LEN);
    }
}
