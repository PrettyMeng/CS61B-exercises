public class triangle {
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        drawTriangle(N);
    }
    
    public static void drawTriangle(int N){
        int i = 0;
        String a = "*";
        while (i < N){
            System.out.println(a);
            a += "*";
            i += 1;
        }
    }
}

