public class Driver {
    public static void main(String[] args) {
        BaseClass obj1 = new SubClass();
        SubClass obj2 = new SubClass();
        System.out.println("##################");
        obj1.display();
        System.out.println("#######################");
        obj2.display();
    }
}
