import java.net.*;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * 集合储存Socket
 * 创建线程池，包装
 * 创建ServerSocket，绑定IP和端口
 * 接收来自客户端的请求，参数（附加参数，ComplectionHandler（异步IO处理器））
 */
public class AIOServer {

    static final int POST = 30000;
    final static String UTF_8 = "utf-8";
    static List<AsynchronousSocketChannel> channelList = new ArrayList<>();

    public void startListen() throws InterruptedException,Exception{
        ExecutorService executor = Executors.newFixedThreadPool(20);
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executor);
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.
                open(channelGroup).bind(new InetSocketAddress(POST));
        serverChannel.accept(null,new AcceptHandler(serverChannel));
    }
}
