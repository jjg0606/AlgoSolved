package Solving;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;


public class Solution3752 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=testCase;tc++)
		{
			int testNum = Integer.parseInt(br.readLine().trim());
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int[] score = new int[testNum];
			for(int i=0;i<testNum;i++)
			{
				score[i] = Integer.parseInt(stk.nextToken());
			}
			
			int[] dp = new int[10001];
			ArrayList<Integer> al = new ArrayList<>();
			al.add(0);
			dp[0]=1;
			
			for(int i=0;i<testNum;i++)
			{
				int cur = score[i];
				int cursize = al.size();
				for(int j=0;j<cursize;j++)
				{
					if(dp[cur+al.get(j)]==0)
					{
						dp[cur+al.get(j)]=1;
						al.add(cur+al.get(j));
					}
				}
			}
			
			System.out.println("#"+tc+" "+al.size());
		}//tc loop
	}
}
