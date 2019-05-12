import java.io.*;
import java.net.HttpURLConnection;
import java.net.*;

/**
 * 初始化信息
 * 建立连接，设置连接属性
 * 获取文件信息，启动线程下载（传入下载起始位置，内容大小，RandomAccessFile输入流（
 *      读写，已经移动到起始位置））
 * 获取当前下载百分比的方法
 * 用conn得到输入流，读取内容并写入文件
 * 启动新线程来获取当前下载百分比
 */
public class Main {

    //下载路径，保存位置，线程数
    private String path;
    private String targetFile;
    private int threadNum;
    private DownThread[] threads;
    private int fileSize;

    public Main(String path,String targetFile,int threadNum){
        this.path = path;
        this.threadNum = threadNum;
        //初始化线程数组
        threads = new DownThread[threadNum];
        this.targetFile = targetFile;
    }

    //根据URL创建连接，并设置连接属性
    private HttpURLConnection setConnProps(URL url) throws Exception{
        //建立URL与HTTP之间的连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置链接超时
        conn.setConnectTimeout(5 * 1000);
        //设置请求方式
        conn.setRequestMethod("GET");
        //设置通用的请求属性
        conn.setRequestProperty("Accept",
                "image/gif,image/jpeg,image/pjpeg,image/pjpeg,"
                        + "application/x-shockwave-flash,application/xaml+xml,"
                        + "application/vnd.ms-xpsdocument,application/x-ms-xbap,"
                        + "application/x-ms-application,application/vnd.ms-excel,"
                        + "application/vnd.ms-powerpoint,application/msword,*/*");
        conn.setRequestProperty("Accept-Language","zh-CN");
        conn.setRequestProperty("CharSet","UTF-8");
        return conn;
    }

    public void download() throws Exception{

        URL url = new URL(path);
        //建立URL与HTTP之间的连接
        HttpURLConnection conn = setConnProps(url);
        //设置该连接一直存在
        conn.setRequestProperty("Connection","Keep-Alive");

        //得到文件大小
        fileSize = conn.getContentLength();
        //表示一段时间不会向服务器发出请求
        conn.disconnect();

        //分成 线程数 + 1 等份（主线程也要下载）
        int currentPartSize = fileSize / threadNum + 1;
        RandomAccessFile file = new RandomAccessFile(targetFile,"rw");

        //设置本地文件大小
        file.setLength(fileSize);
        file.close();
        for (int i=0;i<threadNum;i++){

            //计算每个线程下载开始位置
            int startPos = i * currentPartSize;
            //每个线程使用一个RandomAccessFile进行下载
            RandomAccessFile currentPart = new RandomAccessFile(targetFile,"rw");
            //定义该线程的下载位置
            currentPart.seek(startPos);
            //创建并启动下载线程
            threads[i] = new DownThread(startPos,currentPartSize,currentPart);
            threads[i].start();
        }
    }

    //获得下载完成的百分比
    public double getCompleteRate()
    {
        int sumSize = 0;
        for (int i=0;i<threadNum;i++){
            sumSize += threads[i].length;
        }
        return sumSize * 1.0 / fileSize;
    }

    private class DownThread extends Thread{

        private int startPos;
        private int currentPartSize;
        //当前线程需要下载的文件块
        private RandomAccessFile currentPart;
        //当前线程已下载字节数（记录下载百分比）
        private int length;

        public DownThread(int startPos,int currentPartSize,RandomAccessFile currentPart){
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }

        public void run(){
            try{
                URL url = new URL(path);
                HttpURLConnection conn = setConnProps(url);

                //使用缓存提升性能
                BufferedInputStream bInStream = new BufferedInputStream(conn.getInputStream());
                //各线程负责自己的部分
                bInStream.skip(this.startPos);
                byte[] buffer = new byte[1024];
                int hasRead = 0;

                //读取数据网络，写入本地文件
                while((length < currentPartSize)
                            && ((hasRead = bInStream.read(buffer)) != -1))
                {
                    currentPart.write(buffer);
                    length += hasRead;
                }
                currentPart.close();
                bInStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws Exception {

        //设置资源地址，储存文件，下载线程数
        final Main m = new Main("http://issuecdn.baidupcs.com/issue/netdisk/" +
                "yunguanjia/BaiduNetdisk_6.7.2.16.exe"
            ,"bdwp.exe",10);
        //开始下载
        m.download();
        //启动一个线程用来显示当前下载进度，速度
        new Thread(() -> {
            while(m.getCompleteRate() < 1){
                System.out.println("进度：" + m.getCompleteRate());
                try {
                    Thread.sleep(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println("下载完成！");
        }).start();
    }
}
