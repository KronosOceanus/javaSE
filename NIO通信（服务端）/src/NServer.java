import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.*;

/**
 * 同时处理连接到服务端的所有客户端
 * Selector监控1个ServerSocketChannel（用来连接）和多个SocketChannel（服务端和客户端对应）的IO状况
 * 这些Channel获得对象都是通过静态open方法，都需要设置非阻塞，并且注册（注册时设置属性）
 * ServerSocketChannel绑定到指定IP，如果有连接请求，调用accept方法创建服务器端的SocketChannel
 * 如果读取数据，由SelectionKey的channel方法得到相应的SocketChannel，并创建相应的Bytebuffer读取数据
 */
public class NServer {
    private Selector selector = null;
    static final int PORT = 30000;
    private Charset charset = Charset.forName("UTF-8");

    public void init() throws Exception {
        selector = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
        //绑定
        server.bind(isa);
        //设置非阻塞
        server.configureBlocking(false);
        //注册
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            //可通过select获取的，需要被IO处理的集合
            for (SelectionKey sk : selector.selectedKeys()) {
                //先删除
                selector.selectedKeys().remove(sk);

                //sk对应的Channel包含客户端连接请求
                if (sk.isAcceptable()) {
                    //接受连接，产生服务器端的SocketChannel
                    SocketChannel sc = server.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    //将sk对应的Channel设置成准备接受其他请求
                    sk.interestOps(SelectionKey.OP_ACCEPT);
                }

                //sk……有数据需要读取
                if (sk.isReadable()) {
                    //获取存有数据的sk的Channel
                    SocketChannel sc = (SocketChannel) sk.channel();
                    ByteBuffer buff = ByteBuffer.allocate(1024);
                    String content = "";
                    try {
                        while (sc.read(buff) > 0) {
                            //锁定空白区
                            buff.flip();
                            //解码器转换为字符串
                            content += charset.decode(buff);
                        }
                        System.out.println(content);
                        //准备下一次读取
                        sk.interestOps(SelectionKey.OP_READ);
                    } catch (IOException e) {
                        //从Selector中删除制定的SelectionKey
                        sk.cancel();
                        if (sk.channel() != null) {
                            sk.channel().close();
                        }
                    }

                    //聊天信息不为空，将内容发送到所有SocketChannel
                    if (content.length() > 0) {
                        //遍历Selector注册的所有SelectionKey（已注册的Channel）
                        for(SelectionKey key : selector.keys()){
                            Channel targetChannel = key.channel();
                            //如果是SocketChannel对象（还有可能是ServerSocketChannel对象）
                            if(targetChannel instanceof SocketChannel){
                                SocketChannel dest = (SocketChannel)targetChannel;
                                dest.write(charset.encode(content));
                            }
                        }
                    }
                }
            }
        }
    }
}