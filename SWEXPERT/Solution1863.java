package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution1863 {
	static int[] disjoincent;
	

	static int findSet(int x)
	{
		if(disjoincent[x]==x)
		{
			return x;
		}
		else
		{
			disjoincent[x]=findSet(disjoincent[x]);
			return disjoincent[x];
		}
	}
	
	static void union(int i,int j)
	{
		int root1 = findSet(i);
		int root2 = findSet(j);
		if(root1==root2)
		{
			return;
		}
		if(root1<root2)
		{
			disjoincent[root2] = root1;
		}
		else
		{
			disjoincent[root1]=root2;
		}	
	}


	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stk.nextToken());
		
		int M = Integer.parseInt(stk.nextToken());
		
		disjoincent = new int[N];
		
		for(int i=0;i<N;i++)
		{
			disjoincent[i] = i;
		}
		
		for(int i=0;i<M;i++)
		{
			stk = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(stk.nextToken());
			int second = Integer.parseInt(stk.nextToken());
			union(first-1,second-1);
		}
		
		for(int i=0;i<N;i++)
		{
			findSet(i);
		}
		TreeSet<Integer> set = new TreeSet<>();
		for(int i=0;i<N;i++)
		{
			set.add(disjoincent[i]);
		}
		System.out.println(set.size());
	}//main loop
}
