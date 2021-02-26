public class TestBody {
    public static void main(String[] args){
        Body a = new Body(0, 0, 1, 1, 0.8, "test1.png");
        Body b = new Body(2, 3, 1, 1, 0.6, "test2.png");
        double F = b.calcForceExertedBy(a);
        System.out.println(F);
    }
}
