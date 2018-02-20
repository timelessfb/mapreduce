package SerializableInJdkHadoop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class comp implements Comparator<javabean> {

    public int compare(javabean o1, javabean o2) {
        return o1.getSum() > o2.getSum() ? -1 : 1;
    }
}

class comI implements Comparator<Integer> {

    public int compare(Integer o1, Integer o2) {
        return o1 > o2 ? -1 : 1;
    }
}

public class test {
    public static void main(String[] args) {
        javabean jb = new javabean();
        javabean jb1 = new javabean();
        javabean jb2 = new javabean();
        jb.setSum(2);
        jb1.setSum(3);
        jb2.setSum(1);
        System.out.println(jb.compareTo(jb1));
        ArrayList<javabean> javabeans = new ArrayList<javabean>();
        javabeans.add(jb);
        javabeans.add(jb1);
        javabeans.add(jb2);
        System.out.println(javabeans);
//        Collections.sort(javabeans, new comp());
        Collections.sort(javabeans);
        System.out.println(javabeans);
        System.out.println("===================");
        ArrayList<Integer> integers = new ArrayList<Integer>();
        integers.add(7);
        integers.add(9);
        integers.add(8);
        Collections.sort(integers, new comI());
//        Collections.sort(integers);
        System.out.println(integers);
        Collections.sort(integers);
        System.out.println(integers);

    }
}
