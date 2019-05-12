import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * 通过一个Selector管理SocketChannel
 * 每次有新数据都启动一个线程处理
 */
public class NClient {
    private Selector selector = null;
    static final int PORT = 30000;
    private Charset charset = Charset.forName("UTF-8");
    private SocketChannel sc = null;

    public void init() throws IOException {
        selector = Selector.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1",PORT);
        sc = SocketChannel.open(isa);
        sc.configureBlocking(false);
        sc.register(selector,SelectionKey.OP_READ);
        //读取服务端数据
        new ClientThread().start();

        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            sc.write(charset.encode(line));
        }
    }

    //处理服务器数据的线程
    private class ClientThread extends Thread{
        public void run(){
            try{
                while(selector.select() > 0){
                    for(SelectionKey sk : selector.selectedKeys()){
                        selector.selectedKeys().remove(sk);
                        //有可读取的数据
                        if(sk.isReadable()){
                            SocketChannel sc = (SocketChannel)sk.channel();
                            ByteBuffer buff = ByteBuffer.allocate(1024);
                            String content = "";
                            while(sc.read(buff) > 0){
                                sc.read(buff);
                                buff.flip();
                                content += charset.decode(buff);
                            }
                            System.out.println(content);
                            //为下一次读取做准备
                            sk.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
