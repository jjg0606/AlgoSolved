package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4408 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=testCase;tc++)
		{
			int stuNum = Integer.parseInt(br.readLine().trim());
			StringTokenizer stk;
			int[] ail = new int[201];
			for(int i=0;i<stuNum;i++)
			{
				stk=new StringTokenizer(br.readLine().trim());
				int firstroom =Integer.parseInt(stk.nextToken()); 
				int lastroom =Integer.parseInt(stk.nextToken());
				
				if(firstroom==lastroom)
				{
					continue;
				
				}
				
				if(firstroom > lastroom)
				{
					int temp=firstroom;
					firstroom=lastroom;
					lastroom = temp;
				}
				int start = (firstroom+1)/2;
				int end = (lastroom+1)/2;
				for(int j=start;j<=end;j++)
				{
					ail[j]++;
				}				
			}
			// insertion end
			int answer = 0;
			for(int i=0;i<201;i++)
			{
				answer = Math.max(ail[i], answer);
			}
					
			System.out.println("#"+tc+" "+answer);
		}//tc loop
	}
}
