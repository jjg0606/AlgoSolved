package Solving;

import java.util.Scanner;

public class Main1053_피보나치 {
	static class matrix
	{
		int a11;
		int a12;
		int a21;
		int a22;
		
		void divideby(int div)
		{
			a11%=div;
			a12%=div;
			a21%=div;
			a22%=div;
		}
		
		public matrix()
		{
			a11=1;
			a12=1;
			a21=1;
			a22=0;
		}
		
		void multiple()
		{
			int temp11=a11+a12;
			int temp12=a11;
			int temp21=a21+a22;
			int temp22=a21;
		}
		
		void multiple(matrix m)
		{
			int temp11=a11*m.a11+a12*m.a21;
			int temp12=a11*m.a12+a12*m.a22;
			int temp21=a21*m.a11+a22*m.a21;
			int temp22=a21*m.a12+a22*m.a22;
			a11=temp11;
			a12=temp12;
			a21=temp21;
			a22=temp22;
		}
	}
	
	public static int recursivefibonachi(int n)
	{
		if(n==0)
		{
			return 0;
		}
		matrix mul=new matrix();
		matrix ans=new matrix();
		n--;
		while(n>0)
		{
			if(n%2==1)
			{
				ans.multiple(mul);
				ans.divideby(10000);
			}
			mul.multiple(mul);
			mul.divideby(10000);
			n/=2;
		}
		return ans.a12;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			int num = sc.nextInt();
			if(num<0)
			{
				break;
			}
			long start = System.currentTimeMillis();
			System.out.println(recursivefibonachi(num)+" "+(System.currentTimeMillis()-start)+"ms taken ");
			
		}
	}
}
