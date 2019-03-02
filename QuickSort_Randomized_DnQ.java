package Introduction;


public class QuickSort_Randomized_DnQ {

	public static void main(String[] args) {

		//Create the array
		int arr[]= new int[80];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random()*12345);
		}
		//Print the un-sorted array
		System.out.print("Unsorted:\n[");
		for(int i = 0; i < arr.length; i++ ) {
			System.out.print(arr[i]);
			if(i!=arr.length-1) {
				System.out.print(", ");
			}
			if(i!=0) {
				if(i%20==0) {
					System.out.println();
				}
			}
		}
		System.out.println("]\n");
	

		//call quicksort 
		QuickSort(arr,0,arr.length-1);
		//Print the sorted array
		System.out.print("Sorted:\n[");
		for(int i = 0; i < arr.length; i++ ) {
			System.out.print(arr[i]);
			if(i!=arr.length-1) {
				System.out.print(", ");
			}
			if(i!=0) {
				if(i%20==0) {
					System.out.println();
				}
			}
		}
		System.out.println("]\n");
	
	}

	//quicksort method
	public static void QuickSort(int []A, int l, int r) {
		if(r<=l) {
			return;
		}
		int p= PartitionArray(A,l,r);
		QuickSort(A, l, p-1);
		QuickSort(A, p+1, r);

		return;
	}

	//partition and return the pivot
	public static int PartitionArray(int []A, int l,int r) {
		//get the random pivot between l and r, pick between 25 to 75 % of n
		int p=(r-l)/4;
		int randNum=l+(int) (Math.random()*((r-p)-(l+p)));
		int tempVar=A[randNum];
		A[randNum]=A[l];
		A[l]=tempVar;


		int i=l+1;int j=l+1;
		int pivot=A[l];
		//System.out.println("Pivot is: "+pivot);
		//O(n) to put pivot in place	
		for(;j<=r;j++) {
			//if j index has element smaller than i index, swap and increase i and j is automatically increased
			if(A[j]<pivot) {
				int tmp=A[j];
				A[j]=A[i];
				A[i]= tmp;
				i++;
			}

			//if j does not find smaller item, do nth, loop will increment j
		}

		//in the end put pivot in correct hole
		i--;
		int t=A[i];
		A[i]=A[l];
		A[l]= t;

		return i;
	}



}
