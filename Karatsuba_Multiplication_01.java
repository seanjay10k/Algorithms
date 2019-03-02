package Introduction;

public class Karatsuba_Multiplication_01 {

	public static void main(String[] args) {

		int answer=mult(1234,5678);
		System.out.println(" NON-REC :"+answer);
		int recAnswer=recMult(1234,5678);
		System.out.println(" REC :"+recAnswer);

	}
	private static int getNumDigits(int num) {
		int digits=0;
		while(num>0) {
			num=num/10;
			digits++;
		}
		return digits;
	}

	private static int getMSBs(int num, int places) {
		int count=0;
		while(count<places) {
			num=num/10;
			count++;
		}
		System.out.println(num);
		return num;

	}


	private static  int recMult(int x,int y) {
		if(x<10 || y<10) {
			return x*y;
		}
		int n1=1+(int) Math.log10(x);
		int n2=1+(int) Math.log10(y);


		int a=getMSBs(x,n1/2);int b=x-(int)(a*Math.pow(10, n1/2));
		int c=getMSBs(y,n2/2);int d=y-(int)(c*Math.pow(10, n2/2));

		int ac=recMult(a,c);
		int bd=recMult(b, d);
		int abcd=recMult(a+b,c+d);
		return (int) (Math.pow(10,n1)*ac+ Math.pow(10, n1/2)*(abcd-bd-ac)+ bd );
	}

	//KARATSUBA NON RECURSIVE 0(N^LOG3)
	private static int mult(int x, int y) {
		//count the number of digits
		int n1=getNumDigits(x);
		int n2=getNumDigits(y);
		//divide into a,b,c,d
		int a=getMSBs(x,n1/2);
		int b=(int) (x-a*(Math.pow(10, n1/2)));
		int c=getMSBs(y,n2/2);
		int d=(int) (y-c*(Math.pow(10, n2/2)));

		int e=a*c;
		int f=b*d;
		int g=(a+b)*(c+d)-e-f;
		int result= (int) (Math.pow(10,n1)*e + Math.pow(10,n1/2)*g + f);

		System.out.println(1234*5678+"  "+result);		
		return result;
	}

}
