package Introduction;

import java.util.Arrays;

public class Matrix_Multiplication_DnQ {

	public static void main(String[] args) {

		int[][] A = { {3, 2, 5, 4, 4, 6, 3, 6},
				{3, 0, 4, 6, 9, 0, 4, 1},
				{4, 6, 3, 6, 3, 0, 4, 6},
				{3, 4, 6, 9, 0, 4, 5, 8},
				{1, 5, 8, 2, 4, 6, 3, 6},
				{3, 6, 8, 4, 3, 0, 4, 6},
				{4, 8, 5, 2, 0, 4, 5, 5},
				{3, 2, 0, 3, 4, 6, 3, 6},
		};
		int[][] B = { {2, 3, 4, 6, 2, 5, 4, 4},
				{9, 0, 4, 1, 3, 0, 4, 6},
				{3, 0, 4, 6, 9, 0, 4, 1},
				{0, 4, 5, 8, 2, 0, 3, 4},
				{1, 5, 8, 2, 4, 6, 3, 6},
				{3, 2, 5, 4, 4, 6, 3, 6},
				{4, 8, 5, 2, 0, 4, 5, 5},
				{4, 6, 3, 6, 3, 0, 4, 6}
		};

		//MATRIX MULTIPLICATION NAIVE O(N3)
		int resA[][]=new int[A.length][B[0].length];
		for(int i=0;i<A.length;i++) {
			for(int j=0;j<B[0].length;j++) {
				for(int k=0;k<B.length;k++) {
					resA[i][j]+= A[i][k]*B[k][j];
				}
			}
		}
		//PRINT THE RESULTING MATRIX
		System.out.println("Resuling matrix: Naive");
		String str=Arrays.deepToString(resA);
		for(int i=0;i<str.length();i++) {
			if(i<str.length()-2 && str.charAt(i)==']') {
				str=str.substring(0,i+1)+'\n'+str.substring(i+3, str.length());
			}
		}
		System.out.println(str);

		//MATRIX MULTIPLICATION DIVIDE AND CONQUER
		System.out.println("\nResuling matrix: D&Q");
		@SuppressWarnings("unused")
		int resultArray[][]=new int[A.length][B[0].length];
		resultArray=MatrixMultiply(A, B, A.length);
		System.out.println(Arrays.deepToString(resultArray));

	}


	@SuppressWarnings("unused")
	public static int[][] MatrixMultiply(int [][]A, int [][]B, int n) {

		int C[][]=new int[n][n];
		if(n==1) {
			C[0][0]=A[0][0]*B[0][0];
			return C;
		}
		else {
			//n/2
			int m=n/2;
			//A11 A12 A21 A22 B11 B12 B21 B22 C11 C12 C21 C22 declaring
			int A11[][]=new int[m][m];int B11[][]=new int[m][m];int C11[][]=new int[m][m];
			int A12[][]=new int[m][m];int B12[][]=new int[m][m];int C12[][]=new int[m][m];
			int A21[][]=new int[m][m];int B21[][]=new int[m][m];int C21[][]=new int[m][m];
			int A22[][]=new int[m][m];int B22[][]=new int[m][m];int C22[][]=new int[m][m];
			//A11 A12 A21 A22 B11 B12 B21 B22 C11 C12 C21 C22 initializing
			for(int i=0;i<m;i++) {
				for(int j=0;j<m;j++) {
					A11[i][j] = A[i][j];	B11[i][j] = B[i][j];
					A12[i][j] = A[i][m+j];	B12[i][j] = B[i][m+j];
					A21[i][j] = A[m+i][j];	B21[i][j] = B[m+i][j];
					A22[i][j] = A[m+i][m+j];B22[i][j] = B[m+i][m+j];
				}
			}


			C11= addMatrix(MatrixMultiply(A11,B11,m),MatrixMultiply(A12,B21,m));
			C12= addMatrix(MatrixMultiply(A11,B12,m),MatrixMultiply(A12,B22,m));
			C21= addMatrix(MatrixMultiply(A21,B11,m),MatrixMultiply(A22,B21,m));
			C22= addMatrix(MatrixMultiply(A21,B12,m),MatrixMultiply(A22,B22,m));

			//compile the array C
			for(int i=0;i<C11.length;i++) {
				for(int j=0;j<C11.length;j++) {
					C[i][j]= C11[i][j];
					C[i][m+j]= C12[i][j];
					C[m+i][j]= C21[i][j];
					C[m+i][m+j]= C22[i][j];
				}
			}

			return C;			
		}
	}

	public static int[][] addMatrix(int a[][],int b[][]){
		int dim=a.length;
		int c[][]=new int[dim][dim];
		for(int i=0;i<dim;i++) {
			for(int j=0;j<dim;j++) {
				c[i][j]=a[i][j]+b[i][j];
			}	
		}
		return c;
	}
}
