package FlowCountPartitionerPacket;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

public class ProvincePartitioner extends Partitioner<Text, FlowBean> {
    static HashMap<String, Integer> provinceMap = new HashMap<String, Integer>();

    static {
        provinceMap.put("130", 0);
        provinceMap.put("131", 1);
        provinceMap.put("132", 2);
        provinceMap.put("133", 3);
        provinceMap.put("134", 4);
        provinceMap.put("135", 5);
    }

    @Override
    public int getPartition(Text text, FlowBean flowBean, int i) {
        Integer partition = provinceMap.get(text.toString().substring(0, 3));
        return partition == null ? 6 : partition;
    }
}
