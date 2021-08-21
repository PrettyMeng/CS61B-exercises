package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int N = oomages.size();
        int[] backetCounts = new int[M];
        int backetNum;
        for (Oomage o : oomages) {
            backetNum = (o.hashCode() & 0x7FFFFFFF) % M;
            backetCounts[backetNum]++;
        }
        for (int backetCount : backetCounts) {
            if (backetCount < N / 50 || backetCount > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
