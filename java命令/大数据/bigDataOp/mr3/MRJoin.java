package mr;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.GenericOptionsParser;

public class MRJoin {
	public static class MR_Join_Mapper extends Mapper<LongWritable, Text, TextPair, Text> {
		@Override
		protected void map(LongWritable key, Text value, Context context) 
										throws IOException, InterruptedException {
			// ��ȡ�����ļ���ȫ·��������
			String pathName = ((FileSplit) context.getInputSplit()).getPath().toString();
			if (pathName.contains("data.txt")) {
				String values[] = value.toString().split("\t");
				if (values.length < 3) {
					// data���ݸ�ʽ���淶���ֶ�С��3����������
					return;
				} else {
					// ���ݸ�ʽ�淶�����ֱ�ʶΪ1
					TextPair tp = new TextPair(new Text(values[1]), new Text("1"));
					context.write(tp, new Text(values[0] + "\t" + values[2]));
				}
			}
			if (pathName.contains("info.txt")) {
				String values[] = value.toString().split("\t");
				if (values.length < 2) {
					// data���ݸ�ʽ���淶���ֶ�С��2����������
					return;
				} else {
					// ���ݸ�ʽ�淶�����ֱ�ʶΪ0
					TextPair tp = new TextPair(new Text(values[0]), new Text("0"));
					context.write(tp, new Text(values[1]));
				}
			}
		}
	}

	public static class MR_Join_Partitioner extends Partitioner<TextPair, Text> {
		@Override
		public int getPartition(TextPair key, Text value, int numParititon) {
			return Math.abs(key.getFirst().hashCode() * 127) % numParititon;
		}
	}

	public static class MR_Join_Comparator extends WritableComparator {
		public MR_Join_Comparator() {
			super(TextPair.class, true);
		}

		public int compare(WritableComparable a, WritableComparable b) {
			TextPair t1 = (TextPair) a;
			TextPair t2 = (TextPair) b;
			return t1.getFirst().compareTo(t2.getFirst());
		}
	}

	public static class MR_Join_Reduce extends Reducer<TextPair, Text, Text, Text> {
		protected void reduce(TextPair key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			Text pid = key.getFirst();
			String desc = values.iterator().next().toString();
			while (values.iterator().hasNext()) {
				context.write(pid, new Text(values.iterator().next().toString() + "\t" + desc));
			}
		}
	}

	

	public static void main(String agrs[]) 
						throws IOException, InterruptedException, ClassNotFoundException {
		Configuration conf = new Configuration();
		GenericOptionsParser parser = new GenericOptionsParser(conf, agrs);
		String[] otherArgs = parser.getRemainingArgs();
		if (agrs.length < 3) {
			System.err.println("Usage: MRJoin <in_path_one> <in_path_two> <output>");
			System.exit(2);
		}
		
		Job job = new Job(conf, "MRJoin");
		// �������е�job
		job.setJarByClass(MRJoin.class);
		// ����Map�������
		job.setMapperClass(MR_Join_Mapper.class);
		// ����Map�����
		job.setMapOutputKeyClass(TextPair.class);
		job.setMapOutputValueClass(Text.class);
		// ����partition
		job.setPartitionerClass(MR_Join_Partitioner.class);
		// �ڷ���֮����ָ������������
		job.setGroupingComparatorClass(MR_Join_Comparator.class);
		// ����Reduce
		job.setReducerClass(MR_Join_Reduce.class);
		// ����Reduce�����
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		// ��������������Ŀ¼
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileInputFormat.addInputPath(job, new Path(otherArgs[1]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));
		// ִ�У�ֱ���������˳�
		System.exit(job.waitForCompletion(true) ? 0 : 1);
 }
}

class TextPair implements WritableComparable<TextPair> {
	private Text first;
	private Text second;

	public TextPair() {
		set(new Text(), new Text());
	}

	public TextPair(String first, String second) {
		set(new Text(first), new Text(second));
	}

	public TextPair(Text first, Text second) {
		set(first, second);
	}

	public void set(Text first, Text second) {
		this.first = first;
		this.second = second;
	}

	public Text getFirst() {
		return first;
	}

	public Text getSecond() {
		return second;
	}

	public void write(DataOutput out) throws IOException {
		first.write(out);
		second.write(out);
	}

	public void readFields(DataInput in) throws IOException {
		first.readFields(in);
		second.readFields(in);
	}

	public int compareTo(TextPair tp) {
		int cmp = first.compareTo(tp.first);
		if (cmp != 0) {
			return cmp;
		}
		return second.compareTo(tp.second);
	}
}