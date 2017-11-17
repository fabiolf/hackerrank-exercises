import java.util.Scanner;

public class Solution {

	private static void rotate(int[] array, int begin) {
		int aux = array[begin+2];
		array[begin+2] = array[begin];
		array[begin] = array[begin+1];
		array[begin+1] = aux;
	}
	
	public static void main(String[] args) {
		System.out.println("Begin!");
		Scanner in = new Scanner(System.in);
		// reading from StdIn
		int arrays[][];
		int numberOfArrays = in.nextInt();

		arrays = new int[numberOfArrays][];
		for (int i = 0; i < numberOfArrays; i++) {
			int size = in.nextInt();
			arrays[i] = new int[size];
			for (int j = 0; j < size; j++) {
				arrays[i][j] = in.nextInt();
			}
		}
		in.close();

		// checking each array to see if it can be ordered by the robot
		// by brute force
		for (int[] innerArray : arrays) {
			int beginRotation = -1;
			int countRotations = 0;
			boolean success = true;
			for (int i = 1; i < innerArray.length; i++) {
				if (innerArray[i] < innerArray[i-1]) {
					if (i-2 >= 0 && innerArray[i] < innerArray[i-2]) {
						if (beginRotation != i-2) {
							beginRotation = i-2;
							countRotations = 1;
						} else {
							countRotations++;
							if (countRotations == 3) {
								System.out.println("NO");
								success = false;
								break;
							}
						}
						rotate(innerArray, i-2);
						i -= 2;
					} else if (i+1 < innerArray.length) {
						rotate(innerArray, i-1);
						i--;
					} else {
						System.out.println("NO");
						success = false;
						break;
					}
				}
			}
			if (success)
				System.out.println("YES");
		}
	}

}
