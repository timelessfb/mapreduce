package SerializableInJdkHadoop;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class javabean implements WritableComparable<javabean> {
    Integer sum;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public int compareTo(javabean o) {
        return sum > o.getSum() ? 1 : -1;
    }

    public void write(DataOutput dataOutput) throws IOException {

    }

    public void readFields(DataInput dataInput) throws IOException {

    }

    @Override
    public String toString() {
        return "javabean{" +
                "sum=" + sum +
                '}';
    }
}
