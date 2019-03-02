package Introduction;

import java.util.Arrays;

public class Array_Inversions_DnQ {

	public static void main(String[] args) {
		int arr[]= new int[10];	
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random()*123);
		}

		//System.out.println(Arrays.toString(arr));


		int arr1[]= {88, 20, 68, 86, 19, 73, 45, 69, 70, 106};
		System.out.println(Arrays.toString(arr1));
		int acc[]= {0};
		int numInversions=count_inversions(arr1,0,arr1.length-1,acc);
		System.out.println(Arrays.toString(arr1)+ "   "+numInversions);
	}

	public static int count_inversions(int []A,int p, int r,int []acc) {

		if(p<r) {	

			int q=(p+r)/2;
			count_inversions(A,p,q,acc);
			count_inversions(A, q+1,r,acc);
			count_merge(A,p,q,r,acc);
			System.out.println("acc is now "+acc[0]);
		}
		return acc[0];
	}

	public static int count_merge(int []A, int p, int q, int r,int []acc) {


		int []L= new int[q-p+1];//..
		int []R= new int[r-q];//..

		for(int i=0;i<L.length;i++) {//..
			L[i]=A[p+i];
		}
		for(int i=0;i<R.length;i++) {//..
			R[i]=A[q+i+1];
		}
		System.out.println("L: "+Arrays.toString(L));
		System.out.println("R: "+Arrays.toString(R));
		if(p==r) {
			return acc[0];
		}

		int i=0;int j=0; int index=p;
		while(index<=r) {

			if(i==L.length) {
				for(int n=j;n<R.length;n++) {
					A[index]=R[n];	
					index++;
				}
				return acc[0];
			}
			if(j==R.length) {
				for(int n=i;n<L.length;n++) {
					A[index]=L[n];	
					index++;
				}
				return acc[0];
			}

			if(L[i]<R[j]) {
				A[index]=L[i];
				i++;
			}
			else if(R[j]<L[i]) {
				//				A[index]=R[j];
				for(int m=i;m<L.length;m++) {
					acc[0]=acc[0]+1;
					System.out.println("inversions= "+" "+A[m]+"  :"+acc[0]);
				}
				A[index]=R[j];
				j++;
			}

			index++;
		}

		return acc[0];



	}

}
