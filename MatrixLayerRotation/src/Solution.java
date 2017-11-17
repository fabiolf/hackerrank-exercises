import java.util.Scanner;

public class Solution {

	public static class Matrix {
		private int[] m_array;
		private int m_lines, m_columns;
		
		public Matrix(int lines, int columns) {
			m_lines = lines;
			m_columns = columns;
			m_array = new int[lines*columns];
		}
		
		public int convertIJtoIndex(int i, int j) {
			return((i-1)*m_columns + (j-1));
		}
		
		public void setElement(int i, int j, int value) {
			m_array[convertIJtoIndex(i, j)] = value;
		}
		
		public int getElement(int i, int j) {
			return(m_array[convertIJtoIndex(i, j)]);
		}
		
		public void print() {
			for (int i = 1; i <= m_lines; i++) {
				String msg = "";
				for (int j = 1; j <= m_columns; j++)
					msg = msg.concat(Integer.toString(getElement(i, j))).concat(" ");
				System.out.println(msg);
			}
		}
		
		public void rotate(int r) {
			int minCol = 1, maxCol = m_columns;
			int minLine = 1, maxLine = m_lines;
			while(minCol < maxCol && minLine < maxLine) {
				// after rotating X times the matrix comes back to the original form
				// so we can avoid unnecessary rotations
				int usefulRotations = r % (2*(maxCol-minCol+1) + ((maxLine-minLine+1)-2)*2);
				for (int rot = 0; rot < usefulRotations; rot++) {
					int aux = getElement(minLine, minCol);
					for (int i = minLine; i <= maxLine; i++) {
						if (i == minLine) { // first line, just shift all elements left
							for (int j = minCol; j <= maxCol; j++)
								if (j != minCol) // first element of first line
									setElement(i, j-1, getElement(i, j));
						} else if (i == maxLine) { // last line, just shift all elements right
							for (int j = maxCol; j >= minCol; j--)
								if (j == maxCol) // last element of last line
									setElement(i-1, j, getElement(i, j));
								else
									setElement(i, j+1, getElement(i, j));
							setElement(i, minCol, aux);
						} else { // middle lines
							int aux2 = getElement(i, minCol);
							setElement(i, minCol, aux);
							aux = aux2;
							setElement(i-1, maxCol, getElement(i, maxCol));
						}
					}
				}					
				minCol++;
				maxCol--;
				minLine++;
				maxLine--;
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// reading from StdIn
		int lines = in.nextInt();
		int columns = in.nextInt();
		int rotations = in.nextInt();
		
		Matrix matrix = new Matrix(lines, columns);
		
		for (int i = 1; i <= lines; i++)
			for (int j = 1; j <= columns; j++)
				matrix.setElement(i, j, in.nextInt());
		in.close();
		
		matrix.rotate(rotations);
		matrix.print();
	}
}
