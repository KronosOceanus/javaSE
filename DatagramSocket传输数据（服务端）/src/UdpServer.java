import java.net.*;

/**
 * 服务器需要根据接收到的数据寻找客户端并发送数据
 * 创建读取数据的DatagramPacket（inPacket）并初始化
 * 根据inPacket的IP与端口创建outPacket
 */
public class UdpServer {
    public static final int PORT = 30000;
    //传输大小限制在64KB以下
    private static final int DATA_LEN = 4096;

    byte[] inBuff = new byte[DATA_LEN];
    //读取数据的DatahgramPacket可以直接赋值
    private DatagramPacket inPacket = new DatagramPacket(inBuff,inBuff.length);
    //发送数据的DatagramPacket要在发送的内容确定以后再赋值
    private DatagramPacket outPacket;
    String[] books = {
            "疯狂java讲义",
            "轻量",
            "疯狂android讲义"
    };

    public void init() throws Exception{
        try(
                DatagramSocket socket = new DatagramSocket(PORT);
                )
        {
            //可以接收1000个客户端发送的数据
            for (int i=0;i<1000;i++){
                //receive方法会阻塞调用它的线程
                socket.receive(inPacket);
                System.out.println(inBuff == inPacket.getData());
                //获取接收数据的大小
                System.out.println(new String(inBuff,0,inPacket.getLength()));
                //发送数据的确定，用于发送数据的DatagramPacket才能赋值
                byte[] senData = books[i%3].getBytes();
                //SocketAddress封装了一个InetAddress对象和一个代表端口的整数
                outPacket = new DatagramPacket(senData,senData.length,inPacket.getSocketAddress());
                socket.send(outPacket);
            }
        }
    }
}
