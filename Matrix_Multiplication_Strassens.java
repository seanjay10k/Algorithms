package Introduction;

import java.util.Arrays;

public class Matrix_Multiplication_Strassens {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		int[][] A = {
				{3, 2, 5, 4, 4, 6, 3, 6},
				{3, 0, 4, 6, 9, 0, 4, 1},
				{4, 6, 3, 6, 3, 0, 4, 6},
				{3, 4, 6, 9, 0, 4, 5, 8},
				{1, 5, 8, 2, 4, 6, 3, 6},
				{3, 6, 8, 4, 3, 0, 4, 6},
				{4, 8, 5, 2, 0, 4, 5, 5},
				{3, 2, 0, 3, 4, 6, 3, 6},
		};
		int[][] B = {
				{2, 3, 4, 6, 2, 5, 4, 4},
				{9, 0, 4, 1, 3, 0, 4, 6},
				{3, 0, 4, 6, 9, 0, 4, 1},
				{0, 4, 5, 8, 2, 0, 3, 4},
				{1, 5, 8, 2, 4, 6, 3, 6},
				{3, 2, 5, 4, 4, 6, 3, 6},
				{4, 8, 5, 2, 0, 4, 5, 5},
				{4, 6, 3, 6, 3, 0, 4, 6}
		};
		int C[][]= Mult_Strassens(A, B, A.length);
		//  	System.out.println(Arrays.deepToString(C));
		String resArr=Arrays.deepToString(C);
		for(int i=0;i<resArr.length();i++) {
			if(resArr.charAt(i)==']' && i!=resArr.length()-1) {
				resArr=resArr.substring(0, i)+"\n"+resArr.substring(i+1,resArr.length());i++;
			}

		}
		System.out.println(resArr);

	}

	@SuppressWarnings("unused")
	public static int[][] Mult_Strassens(int X[][],int Y[][],int n){
		int R[][]= new int [n][n];
		if(n==1) { 

			R[0][0]=X[0][0]*Y[0][0];

			return R; 
		}
		else {
			int m=n/2;
			//Declare the Arrays
			int P1[][]=new int[m][m];int P2[][]=new int[m][m];int P3[][]=new int[m][m];int P4[][]=new int[m][m];
			int P5[][]=new int[m][m];int P6[][]=new int[m][m];int P7[][]=new int[m][m];
			int A[][]= new int[m][m];int B[][]= new int[m][m];int C[][]= new int[m][m];int D[][]= new int[m][m];
			int E[][]= new int[m][m];int F[][]= new int[m][m];int G[][]= new int[m][m];int H[][]= new int[m][m];

			//Fill the arrays
			for(int i=0;i<m;i++) {
				for(int j=0;j<m;j++) {

					A[i][j]=X[i][j];	 	E[i][j]=Y[i][j];
					B[i][j]=X[i][m+j];		F[i][j]=Y[i][m+j];
					C[i][j]=X[m+i][j];		G[i][j]=Y[m+i][j];
					D[i][j]=X[m+i][m+j];	H[i][j]=Y[m+i][m+j];

				}	
			}

			P1=Mult_Strassens(A, addMatrix(F,H,"-"), m);
			P2=Mult_Strassens(addMatrix(A,B,"+"), H, m);
			P3=Mult_Strassens(addMatrix(C,D,"+"), E, m);
			P4=Mult_Strassens(D, addMatrix(G,E,"-"), m);
			P5=Mult_Strassens(addMatrix(A,D,"+"),addMatrix(E,H,"+"), m);
			P6=Mult_Strassens(addMatrix(B,D,"-"), addMatrix(G,H,"+"), m);
			P7=Mult_Strassens(addMatrix(A,C,"-"), addMatrix(F,E,"+"), m);

			int Q1[][]=new int[m][m];int Q2[][]=new int[m][m];
			int Q3[][]=new int[m][m];int Q4[][]=new int[m][m];

			for(int i=0;i<m;i++) {
				for(int j=0;j<m;j++) {

					Q1[i][j]=P5[i][j]+P4[i][j]-P2[i][j]+P6[i][j];
					Q2[i][j]=P1[i][j]+P2[i][j];
					Q3[i][j]=P3[i][j]+P4[i][j];
					Q4[i][j]=P1[i][j]+P5[i][j]-P3[i][j]-P7[i][j];

				}
			}

			for(int i=0;i<m;i++) {
				for(int j=0;j<m;j++) {			
					R[i][j]=Q1[i][j];
					R[i][m+j]=Q2[i][j];
					R[m+i][j]=Q3[i][j];
					R[m+i][m+j]=Q4[i][j];				
				}
			}
			return R;
		}

	}

	//
	public static int[][] addMatrix(int X[][], int Y[][],String op){
		//		System.out.println("X: \n"+Arrays.deepToString(X));
		//		System.out.println("Y: \n"+Arrays.deepToString(Y));
		int m=X.length;
		int Arr[][]=new int[m][m];
		if(m==1) {
			if(op=="+") {

				Arr[0][0]=X[0][0]+Y[0][0];
			}
			else {
				Arr[0][0]=X[0][0]-Y[0][0];
			}
			return Arr;
		}

		for(int i=0;i<m;i++) {
			for(int j=0;j<m;j++) {

				if(op=="+") {

					Arr[i][j]=X[i][j]+Y[i][j];
				}
				else {
					Arr[i][j]=X[i][j]-Y[i][j];
				}

			}
		}

		return Arr;
	}

}
