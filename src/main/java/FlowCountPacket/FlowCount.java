package FlowCountPacket;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowCount {
    static class FlowCountMap extends Mapper<LongWritable, Text, Text, FlowBean> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String s = value.toString();
            String[] split = s.split("\t");
            String phonenumber = split[1];
            int len = split.length;
            Long downFlow = Long.parseLong(split[len - 2]);
            Long upFlow = Long.parseLong(split[len - 3]);
            FlowBean flowBean = new FlowBean();
            flowBean.setDownFlow(downFlow);
            flowBean.setUpFlow(upFlow);
            flowBean.setSumFlow(downFlow + upFlow);
            context.write(new Text(phonenumber), flowBean);
        }
    }

    static class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
        @Override
        protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
            FlowBean resultBean = new FlowBean();
            Long downFlow = 0L;
            Long upFlow = 0L;
            Long sumFlow = 0L;
            for (FlowBean flowBean : values) {
                downFlow += flowBean.getDownFlow();
                upFlow += flowBean.getUpFlow();
            }
            resultBean.setDownFlow(downFlow);
            resultBean.setUpFlow(upFlow);
            resultBean.setSumFlow(downFlow + upFlow);
            context.write(key, resultBean);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(FlowCount.class);
        job.setMapperClass(FlowCountMap.class);
        job.setReducerClass(FlowCountReducer.class);
        //指定mapper输出数据的kv类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FlowBean.class);

		//指定最终输出的数据的kv类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);

		//指定job的输入原始文件所在目录
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		//指定job的输出结果所在目录
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		//将job中配置的相关参数，以及job所用的java类所在的jar包，提交给yarn去运行
		/*job.submit();*/
		boolean res = job.waitForCompletion(true);
		System.exit(res?0:1);
    }
}
