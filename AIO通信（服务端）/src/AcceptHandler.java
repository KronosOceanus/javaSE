import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;

/**
 * 包装CompletionHandler（异步IO处理器），传入一个AsynchronousServerSocketChannel
 * 第一个CompletionHandler监听客户端请求
 * 第二个CompletionHandler读取客户端数据
 * 所有AsynchronousSocketChannel中返回Future对象的方法，都需要get方法之后IO操作才真正完成
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel,Object> {

    private AsynchronousServerSocketChannel serverChannel;
    public AcceptHandler(AsynchronousServerSocketChannel sc){
        this.serverChannel = sc;
    }
    ByteBuffer buff = ByteBuffer.allocate(1024);

    @Override
    //实际IO操作完成时触发该方法
    public void completed(final AsynchronousSocketChannel sc, Object attachment) {
        //记录连接进来的Channel
        AIOServer.channelList.add(sc);
        //此时AsynchronousServerSocketChannel空出，为下一次客户端连接做准备
        //this表示当前类的实例
        serverChannel.accept(null,this);
        sc.read(buff, null,
                new CompletionHandler<Integer, Object>() {

                    @Override
                    public void completed(Integer result, Object attachment) {
                        buff.flip();
                        //StandardCharsets标准字符集的常量定义
                        String content = StandardCharsets.UTF_8.decode(buff).toString();
                        for(AsynchronousSocketChannel c : AIOServer.channelList){
                            try{
                                c.write(ByteBuffer.wrap(content.getBytes(AIOServer.UTF_8))).get();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        buff.clear();
                        sc.read(buff,null,this);
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("读取数据是失败 " + exc);
                        AIOServer.channelList.remove(sc);
                    }
                });
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        System.out.println("连接失败 " + exc);
    }
}
