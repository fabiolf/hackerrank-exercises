import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

	private static int calculateHealth(String[] genes, int[] health, int first, int last, String d) {
		// iterate over the d string and consider each char as the beginning of a potential gene
		// try to match valid genes from a sub-array of genes array starting from first to last using the maximum number of chars from string d
		// accumulate the health indicator for each found gene
		int healthScore = 0;
		for (int i = 0; i < d.length(); i++) {
			String substr = d.substring(i, d.length());
			for (int j = first; j <= last; j++) {
				String regex = "^".concat(genes[j]);
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(substr);
				if (m.lookingAt())
					healthScore += health[j];
			}
		}
		return healthScore;
	}

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] genes = new String[n];
        for(int genes_i=0; genes_i < n; genes_i++){
            genes[genes_i] = in.next();
        }
        int[] health = new int[n];
        for(int health_i=0; health_i < n; health_i++){
            health[health_i] = in.nextInt();
        }
        int s = in.nextInt();
        int minHealth = Integer.MAX_VALUE;
        int maxHealth = Integer.MIN_VALUE;
        for(int a0 = 0; a0 < s; a0++){
            int first = in.nextInt();
            int last = in.nextInt();
            String d = in.next();
            int healthScore = calculateHealth(genes, health, first, last, d);
            if (healthScore > maxHealth) maxHealth = healthScore;
            if (healthScore < minHealth) minHealth = healthScore;
        }
        
        System.out.println(Integer.toString(minHealth).concat(" ").concat(Integer.toString(maxHealth)));
    }

}
