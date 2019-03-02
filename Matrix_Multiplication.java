package Introduction;

import java.util.Arrays;

public class Matrix_Multiplication {

	public static void main(String[] args) {



		int [][]L= {{2,4,6},{3,5,8}};
		int [][]R= {{3,4,6,7,},{3,5,6,7},{5,7,9,4}};
		int [][]resultArray= new int [L.length][R[0].length];

		//Multiply
		int mults=L[0].length;//k=R.length; both works since 2*3 * 3*4  =>2*4 and each resulting index has 3 multiplications

		for(int i=0;i<L.length;i++) { //row of first array
			for(int j=0;j<R[0].length;j++) { //columns of second array
				for(int k=0;k<mults;k++) {
					resultArray[i][j] += L[i][k]*R[k][j];
				}
			}
		}
		//print the result
		System.out.println(Arrays.deepToString(resultArray));


	}






}
