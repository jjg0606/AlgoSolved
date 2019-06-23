package Solving;

import java.util.Scanner;

public class Solution1828 {
	static int[] temperature;
	static int lowest;
	static int highst;
	
	static class chemi
	{
		int lowerTemp;
		int highTemp;
		public chemi(int lower,int high)
		{
			lowerTemp=lower;
			highTemp = high;
		}	
		
		public void check()
		{
			int start = lowerTemp-lowest;
			int end = highTemp-lowest;
			for(int i=start;i<=end;i++)
			{
				temperature[i]++;
			}
		}
		
		public boolean isInside(int temp)
		{
			int start = lowerTemp-lowest;
			int end = highTemp-lowest;
			if(temp>=start&&temp<=end)
			{
				return true;
			}			
			return false;
		}
		
		public void uncheck()
		{
			int start = lowerTemp-lowest;
			int end = highTemp-lowest;
			for(int i=start;i<=end;i++)
			{
				temperature[i]--;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int chmicalNum = sc.nextInt();
		chemi[] arr = new chemi[chmicalNum];
		
		lowest = 10000000;
		highst = -10000000;
		
		for(int i=0;i<chmicalNum;i++)
		{
			int lowt = sc.nextInt();
			int higt = sc.nextInt();
			arr[i] = new chemi(lowt,higt);
			if(lowt<lowest)
			{
				lowest = lowt;
			}
			if(higt > highst)
			{
				highst = higt;
			}
		}
		//insertion end
		
		temperature=new int[highst-lowest+1];
		
		for(int i=0;i<chmicalNum;i++)
		{
			arr[i].check();
		}
		int answer = 0; // 냉장고 숫자
		int Left = arr.length;
		while(Left>0)
		{
			int max = 0;
			int maxtemp = 0;
			for(int i=0;i<temperature.length;i++)
			{
				if(temperature[i]>max)
				{
					max = temperature[i];
					maxtemp = i;
				}
			}
			answer++;
			for(int i=0;i<arr.length;i++)
			{
				if(arr[i]==null||!arr[i].isInside(maxtemp))
				{
					continue;
				}
				arr[i].uncheck();
				arr[i]=null;
				Left--;
			}			
		}
		
		System.out.println(answer);
	}
}
