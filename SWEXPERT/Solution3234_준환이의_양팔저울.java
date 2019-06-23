package Solving04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution3234_준환이의_양팔저울 {
	static int[] Weight=new int[9];
	static int[] factorial = new int[10];
	static int N;
	static int answer=0;
	
	static void init()
	{
		factorial[0]=1;
		for(int i=1;i<10;i++)
		{
			factorial[i]=i*factorial[i-1];
		}
	}
	
	static boolean[] picked = new boolean[9];
	static Stack<Integer> pickstack=  new Stack<>();
	static int leftsum = 0;
	static int curpicked = 0;
	static int anstack = 1;

	//N개를 골라서 
	static void getRightPick(int left,int picks,int prv)
	{
		if(leftsum<0)
		{
			return;
		}
		if(picks==left)
		{
			curpicked+=picks;
			int ansbefore = anstack;
			anstack*=factorial[picks];
			if(curpicked==N)
			{
				answer+=anstack;
			}
			else
			{
				for(int i=1;i<=N-curpicked;i++)
				{
					getLeftPick(i,0,0);
				}
			}
			anstack=ansbefore;
			curpicked-=picks;
			return;
		}
		
		for(int i=prv;i<N-left+picks+1;i++)
		{
			if(picked[i])
			{
				continue;
			}
			picked[i]=true;
			leftsum-=Weight[i];
			getRightPick(left, picks+1, i+1);
			leftsum+=Weight[i];
			picked[i]=false;
		}
	}
	
	
	static void getLeftPick(int left,int picks,int prv)
	{
		if(picks==left)
		{
			curpicked+=picks;
			int ansbefore = anstack;
			anstack*=factorial[picks];
			if(curpicked==N)
			{
				answer+=anstack;
			}
			else
			{
				for(int i=1;i<=N-curpicked;i++)
				{
					getRightPick(i,0,0);
				}
			}
			anstack=ansbefore;
			curpicked-=picks;
			return;
		}
		
		for(int i=prv;i<N-left+picks+1;i++)
		{
			if(picked[i])
			{
				continue;
			}
			picked[i]=true;
			leftsum+=Weight[i];
			getLeftPick(left, picks+1, i+1);
			leftsum-=Weight[i];
			picked[i]=false;
		}
	}

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ansb = new StringBuilder();
		init();
		StringTokenizer stk;
		int testCase = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=testCase;tc++)
		{
			N=Integer.parseInt(br.readLine());
			stk = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++)
			{
				Weight[i] = Integer.parseInt(stk.nextToken());
			}
			answer=0;
			Arrays.fill(picked, false);
			pickstack.clear();
			leftsum = 0;
			curpicked = 0;
			anstack=1;
			// insertion and static initialization
			for(int i=1;i<=N;i++)
			{
				getLeftPick(i, 0, 0);
			}
			ansb.append('#').append(tc).append(' ').append(answer).append('\n');
		}// tc loop
		bw.write(ansb.toString());
		bw.flush();
	}
}
