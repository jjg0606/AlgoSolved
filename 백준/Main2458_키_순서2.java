package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main2458_키_순서2 {

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int stuNum = Integer.parseInt(stk.nextToken());
		int edgeNum = Integer.parseInt(stk.nextToken());		
		
		int[][] map = new int[stuNum][stuNum];
		
		for(int i=0;i<edgeNum;i++)
		{
			stk=new StringTokenizer(br.readLine());
			int small = Integer.parseInt(stk.nextToken())-1;
			int big = Integer.parseInt(stk.nextToken())-1;
			map[small][big]=1;
		}
		// insertion end
		//find big
		for(int k=0;k<stuNum;k++)
		{
			for(int i=0;i<stuNum;i++)
			{
				if(i==k||map[k][i]==0)
				{
					continue;
				}

				for(int j=0;j<stuNum;j++)
				{
					if(j==i||j==k||map[i][j]==0)
					{
						continue;
					}
					map[k][j]=1;
				}
			}
		}//find big end
		
		//find small
		for(int k=0;k<stuNum;k++)
		{
			for(int i=0;i<stuNum;i++)
			{
				if(i==k||map[i][k]==0)
				{
					continue;
				}

				for(int j=0;j<stuNum;j++)
				{
					if(j==i||j==k||map[j][i]==0)
					{
						continue;
					}
					map[j][k]=1;
				}
			}
		}//find small end
		int answer = 0;
		for(int i=0;i<stuNum;i++)
		{
			int count=0;
			for(int j=0;j<stuNum;j++)
			{
				count+=map[i][j]+map[j][i];
			}
			if(count==stuNum-1)
			{
				answer++;
			}
		}
		System.out.println(answer);
	}
}
