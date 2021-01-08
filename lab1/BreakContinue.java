public class BreakContinue {
    public static void windowPosSum(int[] a, int n) {
      /** your code here */ 
      for (int i=0; i<a.length; i=i+1){
          if (a[i] < 0){
              continue;
          }
          for (int j=i+1; j<=i+n; j=j+1){
            if (j == a.length){
                break;
            }
            a[i] = a[i] + a[j];
          }
      }
    }
  
    public static void main(String[] args) {
    //   int[] a = {1, 2, -3, 4, 5, 4};
      int[] a = {1, -1, -1, 10, 5, -1};
    //   int n = 3;
      int n = 2;
      windowPosSum(a, n);
  
      // Should print 4, 8, -3, 13, 9, 4
      System.out.println(java.util.Arrays.toString(a));

    }
  }