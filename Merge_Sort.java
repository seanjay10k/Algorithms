package Introduction;
import java.util.Arrays;

public class Merge_Sort {

	public static void main(String[] args) {
		//Create the array
		int arr[]= new int[10];	
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random()*123);
		}
		System.out.println(Arrays.toString(arr));

		//Pass the array to sort
		MergeSort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
	}

	public static void MergeSort(int []A,int begin, int end) {
		System.out.println(Arrays.toString(A));
		//base
		if(begin==end) {
			return;
		}

		//partition the array
		int endL=(begin+end)/2;
		int beginR=endL+1;

		//left
		System.out.println(Arrays.toString(A)+" Begin: "+begin+" EndL: "+endL);
		MergeSort(A, begin,endL );
		System.out.println(Arrays.toString(A)+" BeginR: "+beginR+" End: "+end);
		MergeSort(A, beginR, end);

		Merge(A,begin,endL,beginR,end);

	}

	private static void Merge(int[] A, int begin, int endL, int beginR, int end) {
		int []L= new int[endL-begin+1];
		int []R= new int[end-beginR+1];
		System.out.println("testing "+L.length+" "+R.length+" "+begin+" "+ endL+" "+beginR+" "+end);
		int j=0;int k=0;
		for(int i=begin;i<endL+1;i++) {
			L[j++]=A[i];
		}
		for(int i=beginR;i<end+1;i++) {
			R[k++]=A[i];
		}
		System.out.println("testing arrays: "+ Arrays.toString(L)+" "+Arrays.toString(R));

		j=0;k=0;
		for(int i=begin;i<end+1;i++) {
			if(j>=L.length ) {
				for(int m=i;m<end+1;m++) {
					A[m]=R[k];
					k++;
				}
				return;
			}
			if(k>=R.length ) {
				for(int m=i;m<end+1;m++) {
					A[m]=L[j];
					j++;
				}
				return;
			}

			if(L[j]<R[k]) {
				A[i]=L[j];
				j++;
			}
			else if(R[k]<L[j]) {
				A[i]=R[k];
				k++;
			}
			else if(R[k]==L[j]) {
				A[i]=R[k];
				A[i++]=L[j];
				j++;
				k++;
			}

		}

	}


}
