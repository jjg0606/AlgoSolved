package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution7206 {
	static int[] dp;
	
	static void init()
	{		
		dp = new int[100000];
		for(int i=0;i<10;i++)
		{
			dp[i]=0;
		}
		for(int i=10;i<100000;i++)
		{
			dp[i] = getDividearr(i)+1;
		}
	}

	static int getDividearr(int num) // num 원본숫자 p 자릿수
	{
		String tos = Integer.toString(num); // 개별숫자화 하기위해
		int p = tos.length();
		int max= (1<<p-1);
		int dpre = 0;
		for(int i=1;i<max;i++) // 숫자를 어떻게 쪼갤것인지
		{
			int past=0;
			int multiple = 1;
			for(int j=0;j<p;j++)
			{
				past*=10;
				past+=tos.charAt(j)-'0';
				if((i&(1<<j))>0) // 현재 위치에서 잘라야 함
				{
					multiple*=past;
					past = 0;
				}
			}
			multiple*=past; // 마지막에 안잘리기 때문에
			dpre = Math.max(dp[multiple], dpre);
		}		
		return dpre;
	}

	public static void main(String[] args) throws Exception{
		init();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=testCase;tc++)
		{
			int insert = Integer.parseInt(br.readLine().trim());
			//insertion end				
			
			
			
			System.out.println("#"+tc+" "+dp[insert]);
		}//tc loop
	}
}
