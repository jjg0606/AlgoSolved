package Solving04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution3289_D4_서로소_집합 {
	static int[] Varr = new int[1000001];
	
	static void setVarr(int N)
	{
		for(int i=1;i<N;i++)
		{
			Varr[i]=i;
		}
	}
	
	static int getParent(int index)
	{
		if(Varr[index]==index)
		{
			return index;
		}
		else
		{
			Varr[index] = getParent(Varr[index]);
			return Varr[index];
		}
	}
	
	static void setUnion(int op1,int op2)
	{
		int p1 = getParent(op1);
		int p2 = getParent(op2);
		if(p1<p2)
		{
			Varr[p2]=p1;
		}
		else
		{
			Varr[p1]=p2;
		}
	}
	
	static boolean isSameSet(int op1,int op2)
	{
		return getParent(op1)==getParent(op2);
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ansb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=testCase;tc++)
		{
			StringTokenizer stk = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(stk.nextToken());
			int M = Integer.parseInt(stk.nextToken());
			setVarr(N);
			
			ansb.append('#').append(tc).append(' ');
			for(int i=0;i<M;i++)
			{
				stk = new StringTokenizer(br.readLine().trim());
				int kind = Integer.parseInt(stk.nextToken()); // 명령의 종류
				int op1 = Integer.parseInt(stk.nextToken()); // 첫번째 피연산자
				int op2 = Integer.parseInt(stk.nextToken()); // 두번째 피연산자
				if(kind==0)
				{
					setUnion(op1, op2);
				}
				else
				{
					ansb.append(isSameSet(op1, op2) ? '1' : '0');
				}
			}
			ansb.append('\n');		
			
		}// tc loop
		bw.write(ansb.toString());
		bw.flush();
	}
}
