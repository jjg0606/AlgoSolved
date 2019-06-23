package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution6485 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			int N = Integer.parseInt(br.readLine().trim());
			int[] Aarr = new int[N];
			int[] Barr = new int[N];
			for(int i=0;i<N;i++)
			{
				stk=new StringTokenizer(br.readLine());
				Aarr[i] = Integer.parseInt(stk.nextToken());
				Barr[i] = Integer.parseInt(stk.nextToken());
			}
			int P = Integer.parseInt(br.readLine().trim());
			int[] Parr = new int[P];
			for(int i=0;i<P;i++)
			{
				Parr[i]=Integer.parseInt(br.readLine().trim());
			}
			// insertion end

			int[] busstop = new int[5001];
			for(int i=0;i<N;i++)
			{
				for(int j=Aarr[i];j<=Barr[i];j++)
				{
					busstop[j]++;
				}
			}
			// 계산
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<P;i++)
			{
				sb.append(busstop[Parr[i]]);
				sb.append(' ');
			}
			
			//
			System.out.println("#"+tc+" "+sb.toString());
		}//tc loop
	}
}
