public class SubClass extends BaseClass {
    public int i;


    public void diss() {
    }

    public static void display() {
        System.out.println("i am subclass!");
        SubClass subClass = new SubClass();
        subClass.diss();

    }


}
