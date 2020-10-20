import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MRAvgMarks {

    // ran below queries in emr to load files in hdfs
	// hdfs dfs -copyFromLocal /home/hadoop/Hadoop_data.txt /user/hadoop/employee/input/
	// hdfs dfs -copyFromLocal /home/hadoop/OtherAssessment_data.txt /user/hadoop/employee/input/
	public static class MapperClass extends
	  Mapper<LongWritable, Text, Text, FloatWritable> {
	 public void map(LongWritable key, Text empRecord, Context con)
	   throws IOException, InterruptedException {
	  String[] row = empRecord.toString().split(",");
	  try {
	   Float salary = Float.parseFloat(row[8]);
	   con.write("Avg_Marks", new FloatWritable(salary));
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }
	}

	public static class ReducerClass extends
	  Reducer<Text, FloatWritable, Text, Text> {
	 public void reduce(Text key, Iterable<FloatWritable> valueList,
	   Context con) throws IOException, InterruptedException {
	  try {
	   Float total = (float) 0;
	   int count = 0;
	   for (FloatWritable var : valueList) {
	    total += var.get();
	    System.out.println("reducer " + var.get());
	    count++;
	   }
	   Float avg = (Float) total / count;
	   String out = "Avg_Marks: " + avg;
	   con.write(key, new Text(out));
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }
	}

	public static void main(String[] args) {
	 Configuration conf = new Configuration();
	 try {
	  Job job = Job.getInstance(conf, "AvgSalary");
	  job.setJarByClass(MRTotalMarks.class);
	  job.setMapperClass(MapperClass.class);
	  job.setReducerClass(ReducerClass.class);
	  job.setOutputKeyClass(Text.class);
	  job.setOutputValueClass(FloatWritable.class);

	  Path pathInput = new Path(
	    "/user/hadoop/employee/input");
	  Path pathOutputDir = new Path(
	    "/user/hadoop/employee/totScore");
	  FileInputFormat.addInputPath(job, pathInput);
	  FileOutputFormat.setOutputPath(job, pathOutputDir);
	  System.exit(job.waitForCompletion(true) ? 0 : 1);
	 } catch (IOException e) {
	  e.printStackTrace();
	 } catch (ClassNotFoundException e) {
	  e.printStackTrace();
	 } catch (InterruptedException e) {
	  e.printStackTrace();
	 }

	}

}
