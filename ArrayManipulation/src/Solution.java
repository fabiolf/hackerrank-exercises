import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] array = new long[n];
        int m = in.nextInt();
        long max = 0;
        for(int a0 = 0; a0 < m; a0++){
            int a = in.nextInt();
            int b = in.nextInt();
            int k = in.nextInt();
            for (int i = a-1; i < b; i++) {
            	array[i] += k;
            	if (array[i] > max)
            		max = array[i];
            }
        }
        in.close();
        System.out.println(max);
    }
}
