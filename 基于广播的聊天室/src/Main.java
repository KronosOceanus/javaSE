import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * 将MulticastSocket加入广播IP地址
 * 所有加入的MulticastSocket都能接收到广播
 */
public class Main implements Runnable{

    //地址，目的端口，数据大小
    private static final String BROADCAST_IP = "230.0.0.1";
    public static final int BROADCAST_POST = 30000;
    private static final int DATA_LEN = 4096;

    private MulticastSocket socket = null;
    private InetAddress broadcastAddress = null;
    private Scanner scan = null;
    //接收数据的数组，两个DatagramPacket
    byte[] inBuff = new byte[DATA_LEN];
    private DatagramPacket inPacket = new DatagramPacket(inBuff,inBuff.length);
    private DatagramPacket outPacket = null;

    public void init()throws Exception{
        try(
                Scanner scan = new Scanner(System.in);
                ){
            //要启动线程使用MulticastSocket，所以不能在try()中初始化
            socket = new MulticastSocket(BROADCAST_POST);
            broadcastAddress = InetAddress.getByName(BROADCAST_IP);
            //将MulticastSocket加入到广播IP地址！！！
            socket.joinGroup(broadcastAddress);
            //设置本地MulticastSocket发送的数据会回到自身
            socket.setLoopbackMode(false);

            outPacket = new DatagramPacket(new byte[0],0,
                    broadcastAddress,BROADCAST_POST);
            //run方法为执行体
            new Thread(this).start();

            //读取键盘输入
            while(scan.hasNextLine()){
                byte[] buff = scan.nextLine().getBytes();
                outPacket.setData(buff);
                socket.send(outPacket);
            }
        }
        finally {
            //确保该方法结束后MulticastSocket被关闭
            socket.close();
        }
    }

    public void run(){
        try{
            while(true){
                socket.receive(inPacket);
                System.out.println("聊天信息：" + new String(inBuff,0,
                        inPacket.getLength()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            try{
                //从广播IP地址中移除该MulticastSocket
                if(socket != null){
                    socket.leaveGroup(broadcastAddress);
                    socket.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception{
        new Main().init();
    }
}
