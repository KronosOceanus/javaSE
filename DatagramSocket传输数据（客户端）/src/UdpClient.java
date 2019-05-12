import java.net.*;
import java.util.Scanner;

/**
 * 服务端IP和端口是固定的
 * 客户端可以将数据直接发送到服务端
 * 剩下与服务端基本相同
 */
public class UdpClient {
    public static final int DEST_PORT = 30000;
    public static final String DEST_IP = "127.0.0.1";
    private static final int DATA_LEN = 4096;
    byte[] inBuff = new byte[DATA_LEN];
    private DatagramPacket inPacket = new DatagramPacket(inBuff,inBuff.length);
    private DatagramPacket outPacket = null;

    public void init() throws Exception{
        try(
                //使用随机端口
                DatagramSocket socket = new DatagramSocket();
                ){
            //通过getByName方法将字符串转化为IP地址（没有构造器）
            outPacket = new DatagramPacket(new byte[0],0,InetAddress.getByName(DEST_IP),DEST_PORT);
            Scanner sc = new Scanner(System.in);
            while(sc.hasNextLine()){
                byte[] buff = sc.nextLine().getBytes();
                //赋值之后，发送前需要重新设置发送内容
                outPacket.setData(buff);
                socket.send(outPacket);
                socket.receive(inPacket);
                System.out.println(new String(inBuff,0,inPacket.getLength()));
            }
        }
    }
}
