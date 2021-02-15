import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

    private final static String url = "http://top.news.sina.com.cn/ws/GetTopDataList.php?top_type=day&top_cat=www_www_all_suda_suda&top_time=20200101&top_show_num=100&top_order=DESC&js_var=all_1_data01";

    //根据时间字符串列表查询所有新闻
    private static void queryAndWrite(List<String> timeList) {
        Document document = null;
        String u = null;    //与时间拼接后的 url
        File filename = new File("out.txt");    //要写入的文件

        try (
                BufferedWriter out = new BufferedWriter(new FileWriter(filename));      //字符缓冲流，自动关闭
                ){
            for (String s : timeList){      //遍历时间列表
                u = url.replaceAll("\\d{8}",s);     //拼接 url

                try {
                    document = Jsoup.connect(u).get();      //根据 url 得到页面 document
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                String body = document.select("body").html();
                String subString = body.substring(19, body.length()-1);
                JSONObject jsonObject = JSON.parseObject(subString);
                String data = jsonObject.getString("data");
                JSONArray jsonArray = JSON.parseArray(data);

                for (Object obj : jsonArray) {      //遍历 json 数组，每个内容作为一个 json 对象看待，寻找目标字段
                    JSONObject j = (JSONObject) obj;
                    out.write(j.getString("create_date")+"\t"+j.getString("title")+"\n");   //找到目标字段，写入文件
                    out.flush();    //刷新缓冲区
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //得到时间字符串列表（格式yyyyMMdd）
    private static List<String> getTimeList() throws ParseException {
        List<String> time = new LinkedList<>();
        String dateStart="2020-01-01";      //起止时间
        String dateEnd="2020-09-17";
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
        long startTime = date.parse(dateStart).getTime();
        long endTime = date.parse(dateEnd).getTime();
        long day=1000*60*60*24;     //一天时长（毫秒）
        for(long i=startTime;i<=endTime;i+=day) {
            time.add(date.format(new Date(i)).replaceAll("-",""));      //转换格式
        }
        return time;
    }

    public static void main(String[] args) throws ParseException {
        List<String> timeList = getTimeList();
        queryAndWrite(timeList);
    }
}
