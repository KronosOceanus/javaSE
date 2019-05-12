import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class AIOClient {

    final static int POST = 30000;
    final static String UTF_8 = "utf-8";
    AsynchronousSocketChannel clientChannel;
    //Swing
    JFrame mainWin = new JFrame("多人聊天");
    JTextArea jta = new JTextArea(16,48);
    JTextField jtf = new JTextField(40);
    JButton sendBn = new JButton("发送");

    public void init(){

        mainWin.setLayout(new BorderLayout());
        jta.setEditable(false);
        mainWin.add(new JScrollPane(jta),BorderLayout.CENTER);
        JPanel jp = new JPanel();
        jp.add(jtf);
        jp.add(sendBn);
        Action sendAction = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = jtf.getText();
                //trim方法去掉字符串前后的空白
                if(content.trim().length() > 0){
                    try{
                        //wrap方法吧内容写入缓冲区
                        clientChannel.write(ByteBuffer.wrap(content.trim().getBytes(UTF_8))).get();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                jtf.setText("");
            }
        };
        sendBn.addActionListener(sendAction);
        jtf.getInputMap().put(KeyStroke.getKeyStroke('\n', InputEvent.CTRL_MASK),
                "send");
        jtf.getActionMap().put("send",sendAction);
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.add(jp,BorderLayout.SOUTH);
        mainWin.pack();
        mainWin.setVisible(true);
    }

    public void connect() throws Exception{
        final ByteBuffer buff = ByteBuffer.allocate(1024);
        ExecutorService executor = Executors.newFixedThreadPool(80);
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executor);
        clientChannel = AsynchronousSocketChannel.open(channelGroup);
        //用connect指定连接IP与端口（与服务器Channel不同的地方）
        clientChannel.connect(new InetSocketAddress("127.0.0.1",POST)).get();

        jta.append("---与服务器连接成功---");

        buff.clear();
        clientChannel.read(buff, null,
            new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    buff.flip();
                    //标准字符集
                    String content = StandardCharsets.UTF_8.decode(buff).toString();
                    jta.append("某人说：" + content);
                    buff.clear();
                    clientChannel.read(buff,null,this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("读取数据失败 " + exc);
                }
            });
    }
}
