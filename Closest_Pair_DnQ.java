package Introduction;

import java.util.Arrays;
import java.util.Comparator;

public class Closest_Pair_DnQ {
	public static double closestPoints(int [][]Px, int lo, int hi ){
		//base case: for less than 4 points brute-force
		if((hi-lo)<4) {
			for(int i=lo;i<=hi;i++) {
				System.out.print(Arrays.toString(Px[i])+"..");
				System.out.println();
			}
			double distance=Double.MAX_VALUE;
			double dist=0;
			for(int i=lo;i<=hi;i++) {
				for(int j=i+1;j<=hi;j++) {
					int x1=Px[i][0]; int x2=Px[j][0];
					int y1=Px[i][1]; int y2=Px[j][1];		 
					dist=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
					if(dist<distance) {
						distance=dist;	 
					}						 
				}
			}
			System.out.println("Base case distance is: "+distance);
			System.out.println();
			return distance;
		}
		//the mid index of array
		int mid=(hi-lo)/2;
		//imp!: use divider "x" value to create Sy later
		int divider= Px[lo+mid][0];

		System.out.println("The dividing line is: "+divider);

		//recursively call both left and right halves
		double d1=closestPoints(Px, lo, lo+mid);
		double d2=closestPoints(Px, lo+mid+1, hi);

		//shortest distance from both half
		double d= d1<d2? d1:d2;
		System.out.println("The shortest distance between sides is: "+d);

		//count number of entries for Sy
		int entryCount=0;
		for(int i=lo;i<=hi;i++) {
			int x=Px[i][0];
			if(Math.abs(divider-x)<d) {
				entryCount++;
			}
		}
		// create middle strip using the entryCount
		int [][] Strip=new int[entryCount][2];

		//fill the array with points +-d from divider
		int m=0;
		for(int i=lo;i<=hi;i++) {
			int x=Px[i][0];
			if(Math.abs(divider-x)<d) {
				Strip[m][0]=x;
				Strip[m++][1]=Px[i][1];
			}
		}

		//sort Strip by y
		Arrays.sort(Strip, new Comparator <int[]> () {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]>o2[1]) {
					return 1;
				}
				return -1;
			}	
		});
		System.out.print("Split array is: ");
		for(int [] arr: Strip) {
			System.out.print(Arrays.toString(arr)+" ");
		}
		System.out.println();

		//pass this strip to stripMerge method to get the minimum distance 
		double shortestDistance= StripMerge(Strip,d);

		System.out.println("THIS ITERATION SHORTEST DISTANCE IS: "+shortestDistance);
		System.out.println();
		return shortestDistance;
	}

	public static double StripMerge(int [][] Strip, double d) {

		for(int i=0;i<Strip.length;i++) {
			for(int j=i+1;j<Strip.length && (Strip[j][1]-Strip[i][1]<d );j++) {
				int x1=Strip[i][0]; int x2=Strip[j][0];
				int y1=Strip[i][1]; int y2=Strip[j][1];		 
				double dist=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
				if(dist<d) {
					d=dist;	 
				}

			}
		}
		System.out.println("StripMerge distance is: "+d);
		return d;
	}



	public static void main(String[] args) {

		//the points
		int [][] points= 
				new int[][]{{1,19},{3,7},{7,9},{4,30},{2,1},{11,3},{15,17},{18,5},{14,8},{19,2},{6,26},{9,13},{12,4},{17,11},{21,27},{23,15},{25,22},{27,32},{26,18},{28,28},{30,14},{32,21},{33,35},{34,29},{35,16}};

				//comparator to sort the array based on x
				Comparator C= new Comparator<int[]>() {

					@Override
					public int compare(int[] o1, int[] o2) {
						if(o1[0]>o2[0]) {
							//1 means swap position
							return 1;
						}
						return -1;
					}};
					//sort the array
					Arrays.sort(points,C);

					//print check
					for(int[] a:points) {
						System.out.print(Arrays.toString(a)+"");
					}
					double d=closestPoints(points,0,points.length-1);
					System.out.println("THE CLOSEST PAIR DISTANCE IS: "+d);

	}

}
