package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main2458_키_순서 {

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int stuNum = Integer.parseInt(stk.nextToken());
		int edgeNum = Integer.parseInt(stk.nextToken());
		TreeSet<Integer>[] goingbig = new TreeSet[stuNum+1];
		TreeSet<Integer>[] goingsmall = new TreeSet[stuNum+1];
		for(int i=1;i<=stuNum;i++)
		{
			goingbig[i]=new TreeSet<>();
			goingsmall[i]=new TreeSet<>();
		}
		
		for(int i=0;i<edgeNum;i++)
		{
			stk=new StringTokenizer(br.readLine());
			int small = Integer.parseInt(stk.nextToken());
			int big = Integer.parseInt(stk.nextToken());
			goingbig[small].add(big);
			goingsmall[big].add(small);
		}
		// insertion end
		// going big
		int[] visited = new int[stuNum+1];
		LinkedList<Integer> trace=new LinkedList<>();
		for(int i=1;i<=stuNum;i++)
		{
			if(visited[i]==1)
			{
				continue;
			}
			int[] tempvisit = new int[stuNum+1];
			visited[i]=1;
			tempvisit[i]=1;
			trace.add(i);
			outer : while(!trace.isEmpty())
			{
				int cur = trace.peekLast();
				Iterator<Integer> it = goingbig[cur].iterator();
				while(it.hasNext())
				{
					int next = it.next();
					if(tempvisit[next]==1)
					{
						continue;
					}
					tempvisit[next]=1;	
					visited[next]=1;
					Iterator<Integer> ittr = trace.iterator();
					while(ittr.hasNext())
					{
						int inside = ittr.next();
						goingbig[inside].add(next);
					}
					trace.add(next);
					continue outer;	
				}
				trace.pollLast();
			}// 
		}//going big loop
		Arrays.fill(visited, 0);
		for(int i=1;i<=stuNum;i++)
		{
			if(visited[i]==1)
			{
				continue;
			}
			visited[i]=1;
			int[] tempvisit = new int[stuNum+1];
			tempvisit[i]=1;
			trace.add(i);
			outer2 : while(!trace.isEmpty())
			{
				int cur = trace.peekLast();
				Iterator<Integer> it = goingsmall[cur].iterator();
				while(it.hasNext())
				{
					int next = it.next();
					if(tempvisit[next]==1)
					{
						continue;
					}
					tempvisit[next]=1;	
					visited[next]=1;
					Iterator<Integer> ittr = trace.iterator();
					while(ittr.hasNext())
					{
						int inside = ittr.next();
						goingsmall[inside].add(next);
					}
					trace.add(next);
					continue outer2;	
				}
				trace.pollLast();
			}// 
		}//going small
		int answer =0;
		for(int i=1;i<=stuNum;i++)
		{
			if(goingbig[i].size()+goingsmall[i].size()==stuNum-1)
			{
				answer++;
			}
		}
		System.out.println(answer);
	}
}
