import java.io.*;
import java.net.*;
import java.util.*;

public class Main {

    /**
     * @param url 发送请求的URL
     * @param param 请求参数，多对key=value中间用&隔开
     * @return
     */
    public static String sendGet(String url,String param){
        String result = "";
        String urlName = url + "?" + param;
        try{
            URL realUrl = new URL(urlName);
            URLConnection  conn = realUrl.openConnection();
            //*/*表示能接收所有类型的文件
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            //客户端的类型
            conn.setRequestProperty("user-agent"
                , "Mozilla/4.0(compatible:MSIE 6.0;Windows NT 5.1;SV1)");
            //建立实际连接
            conn.connect();

            //获取所有的响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            for(String key:map.keySet()){
                System.out.println(key + " ------> " + map.get(key));
            }
            try(
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()
                            ,"utf-8"));
                    ){
                String line;
                while((line = in.readLine()) != null){
                    result = "\n" + line;
                }
            }
        } catch (Exception e) {
            System.out.println("发送GET请求时出现异常！");
            e.printStackTrace();
        }
        return result;
    }

    public static String sendPost(String url,String param){
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent"
                    , "Mozilla/4.0(compatible:MSIE 6.0;Windows NT 5.1;SV1)");

            //发送Post请求必须设置以下两行
            conn.setDoInput(true);
            conn.setDoOutput(true);

            try(
                    PrintWriter out = new PrintWriter(conn.getOutputStream());
                    ){
                //发送请求参数
                out.print(param);
                //刷新缓存区
                out.flush();
            }
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()
                            ,"utf-8"));
                    ){
                String line;
                while((line = in.readLine()) != null){
                    result = "\n" + line;
                }
            }
        } catch (Exception e) {
            System.out.println("发送POST请求时出现异常！");
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String g = Main.sendGet("https://www.baidu.com/",null);
        System.out.println(g);
        System.out.println("================");
        String p = Main.sendPost("https://www.baidu.com/",null);
        System.out.println(p);
    }
}
