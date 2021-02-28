import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

import java.io.IOException;
import java.util.StringTokenizer;


public class WordCount {
    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();
        //map方法，划分一行文本，读一个单词写出一个&amp;lt;单词,1&amp;gt;
        public void map(Object key, Text value, Context context)throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
	//写出&amp;lt;单词,1&amp;gt;
            }
        }
    }
    //定义reduce类，对相同的单词，把它们中的VList值全部相加
    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
        private IntWritable result = new IntWritable();
        public void reduce(Text key, Iterable<IntWritable> values,Context context)
                throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
	//相当于&amp;lt;Hello,1&amp;gt;&amp;lt;Hello,1&amp;gt;，将两个1相加
            }
            result.set(sum);
            context.write(key, result);//写出这个单词，和这个单词出现次数&amp;lt;单词，单词出现次数&amp;gt;
        }
    }
    public static void main(String[] args) throws Exception {//主方法，函数入口
        Configuration conf = new Configuration();           //实例化配置文件类
        Job job = new Job(conf, "WordCount");             //实例化Job类
        job.setInputFormatClass(TextInputFormat.class);     //指定使用默认输入格式类
        TextInputFormat.setInputPaths(job, args[0]);      //设置待处理文件的位置
        job.setJarByClass(WordCount.class);               //设置主类名
        job.setMapperClass(TokenizerMapper.class);        //指定使用上述自定义Map类
        job.setCombinerClass(IntSumReducer.class);        //指定开启Combiner函数
        job.setMapOutputKeyClass(Text.class);            //指定Map类输出的，K类型
        job.setMapOutputValueClass(IntWritable.class);     //指定Map类输出的，V类型
        job.setPartitionerClass(HashPartitioner.class);       //指定使用默认的HashPartitioner类
        job.setReducerClass(IntSumReducer.class);         //指定使用上述自定义Reduce类
        job.setNumReduceTasks(Integer.parseInt(args[2]));  //指定Reduce个数
        job.setOutputKeyClass(Text.class);                //指定Reduce类输出的,K类型
        job.setOutputValueClass(Text.class);               //指定Reduce类输出的,V类型
        job.setOutputFormatClass(TextOutputFormat.class);  //指定使用默认输出格式类
        TextOutputFormat.setOutputPath(job, new Path(args[1]));    //设置输出结果文件位置
        System.exit(job.waitForCompletion(true) ? 0 : 1);    //提交任务并监控任务状态
    }
}
