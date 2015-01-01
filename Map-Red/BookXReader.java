
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.util.Iterator;

public class BookXReader {

public static class BookXMapper extends MapReduceBase implements
                org.apache.hadoop.mapred.Mapper<LongWritable, Text, Text, IntWritable> {
        private final IntWritable one = new IntWritable(1);

       
        @Override
        public void map(LongWritable _key, Text value,
                        OutputCollector<Text, IntWritable> output, Reporter reporter)
                        throws IOException {

                String TempString = value.toString();
                String[] SingleBookData = TempString.split("\";\"");
                output.collect(new Text(SingleBookData[3]), one);
        }
}

public static class BookXReducer extends MapReduceBase implements org.apache.hadoop.mapred.Reducer<Text, IntWritable, Text, IntWritable> {

                @Override
        public void reduce(Text _key,
                        Iterator<IntWritable> values,
                        OutputCollector<Text,IntWritable> output, 
                        Reporter reporter)
                        throws IOException {
                Text key = _key;
                int frequencyForYear = 0;
                while (values.hasNext()) {
                        
                        IntWritable value = (IntWritable) values.next();
                        frequencyForYear += value.get();
                        
                }
                output.collect(key, new IntWritable(frequencyForYear));
        }
}




        public static void main(String[] args) {
                JobClient client = new JobClient();
                
                JobConf conf = new JobConf(BookXReader.class);
                
                
                conf.setJobName("BookCrossing1.0");
                
                
                conf.setOutputKeyClass(Text.class);
                conf.setOutputValueClass(IntWritable.class);
                
                
                conf.setMapperClass(BookXMapper.class);
                conf.setReducerClass(BookXReducer.class);

                
                conf.setInputFormat(TextInputFormat.class);
                conf.setOutputFormat(TextOutputFormat.class);
                
                
                FileInputFormat.setInputPaths(conf, new Path(args[0]));
                FileOutputFormat.setOutputPath(conf, new Path(args[1]));
                
                client.setConf(conf);
                try {
                        
                        JobClient.runJob(conf);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}