import java.util.Scanner;

public class Solution {

	// reverse order of a sub-array within an array (O(N))
	private static void reverse(int[] array, int begin, int end) {
		for (int i = begin; i <= (begin+(end-begin)/2); i++) {
			if (i != end-(i-begin)) {
				int aux = array[i];
				array[i] = array[end-(i-begin)];
				array[end-(i-begin)] = aux;
			}
		}
	}
	
	// verify if the two elements can be swapped
	private boolean trySwap(int[] array, int i1, int i2) {
		boolean canSwap = true;

		// if i1 is the first element, we have to check just if i2 < i1+1
		if (i1 == 0) //the first element of the array
			canSwap &= (array[i2] < array[i1+1]);
		// otherwise we also need to check if i2 > i1-1
		if (canSwap) canSwap &= (array[i2] > array[i1-1]);

		// if i2 is the last element, we have to check just if i1 > i2-1
		if (canSwap && i2 == array.length-1)
			canSwap &= (array[i1] > array[i2-1]);
		// otherwise we also need to check if i1 < i2+1
		if (canSwap) canSwap &= (array[i1] < array[i2+1]);
		
		return canSwap;
	}
	// swap two elements of the array (O(1))
	private static void swap(int[] array, int i1, int i2) {
		int aux = array[i1];
		array[i1] = array[i2];
		array[i2] = aux;
	}
	
	// find the element that is out of order starting at a specific element
	// if the array is sorted, returns 0
	private static int findElementOutOfOrder(int[] array, int from) {
		for (int i = from; i < array.length; i++) {
			if (array[i] < array[i-1])
				return i;
		}
		return 0;
	}
	
    public static void main(String[] args) {
		// reading from StdIn
    	Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int array[] = new int[size];

		for (int j = 0; j < size; j++) {
			array[j] = in.nextInt();
		}
		in.close();

		// starting brute force solution
		boolean sorted = true;
		String operation = "";
   		int index = findElementOutOfOrder(array, 1);
   		if (index != 0) {
   			int index2 = index;
   			int runs = 0;
   			int oldIndex = -1;
   			while (index2 != 0) {
   				oldIndex = index2;
   	   			index2 = findElementOutOfOrder(array, index2+1);
   	   			runs++;
   			}
   			index2 = oldIndex;
   			
   			if (runs == 1) { // that means the rest of the array is sorted, so either we swap index-1 and index or we cannot sort the array
   				if (index+1 >= array.length) { //then index is the last element of the array
   					if (array[index] > array[index-2]) {
	   					swap(array, index-1, index);
	   					operation = "swap ".concat(Integer.toString(index)).concat(" ").concat(Integer.toString(index+1));
   					} else
   						sorted = false;
   				} else if (array[index-1] < array[index+1] && 
   							array[index] > array[index-2]) {
	   					swap(array, index-1, index);
	   					operation = "swap ".concat(Integer.toString(index)).concat(" ").concat(Integer.toString(index+1));
   				} else
   					sorted = false;
   			} else {
   				if (runs == 2) { // that means it may be possible to swap index-1 and index2 if array is sorted after that
   					if (index-2 < 0) { //then, index is the second element of the array and we cannot go index-2
   	   					if (array[index2] < array[index] && 
   	   							array[index-1] > array[index2-1]) {
   	   						if (index2 != array.length-1) { //index2 is not the last element
   	   							if (array[index-1] < array[index2+1]) {
   	   		   						swap(array, index-1, index2);
   	   		   	   					operation = "swap ".concat(Integer.toString(index)).concat(" ").concat(Integer.toString(index2+1));
   	   							} else
   	   								sorted = false;
   	   						} else {
   		   						swap(array, index-1, index2);
   		   	   					operation = "swap ".concat(Integer.toString(index)).concat(" ").concat(Integer.toString(index2+1));
   	   						}
   	   					} else
   	   						sorted = false;
   					} else
	   					if (array[index2] < array[index] && 
	   							array[index2] > array[index-2] &&
	   							array[index-1] > array[index2-1]) {
	   						if (index2 != array.length-1) { //index2 is not the last element
	   							if (array[index-1] < array[index2+1]) {
	   		   						swap(array, index-1, index2);
	   		   	   					operation = "swap ".concat(Integer.toString(index)).concat(" ").concat(Integer.toString(index2+1));
	   							} else
	   								sorted = false;
	   						} else {
		   						swap(array, index-1, index2);
		   	   					operation = "swap ".concat(Integer.toString(index)).concat(" ").concat(Integer.toString(index2+1));
	   						}
	   					} else
	   						sorted = false;
   				} else { // more than 2 runs means either we can sort by reversing or we cannot sort at all
   					reverse(array, index-1, index2);
   					if (findElementOutOfOrder(array, 1) == 0) {
   						operation = "reverse ".concat(Integer.toString(index)).concat(" ").concat(Integer.toString(index2+1));
   					} else
   						sorted = false;
   				}
   			}
   		}
   			
    	if (sorted) {
    		System.out.println("yes");
    		if (operation.length() > 0)
    			System.out.println(operation);
    	} else
    		System.out.println("no");
    	
    }
}