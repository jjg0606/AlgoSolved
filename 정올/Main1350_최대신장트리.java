package Solving04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1350_최대신장트리 {
	static int[] Varr;
	
	static class Edge
	{
		int from;
		int to;
		int cost;
		
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}		
	}
	
	public static int getParent(int v)
	{
		if(Varr[v]==v)
		{
			return v;
		}
		else 
		{
			Varr[v] = getParent(Varr[v]);
			return Varr[v];
		}
	}
	
	public static void setParent(int v1,int v2)
	{
		int min = v1 < v2 ? v1 : v2;
		int max = v1 < v2 ? v2 : v1;
		Varr[getParent(max)]=min;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int Vertex = Integer.parseInt(stk.nextToken());
		int EdgeNum = Integer.parseInt(stk.nextToken());
		// first input
		Varr = new int[Vertex+1];
		for(int i=1;i<=Vertex;i++)
		{
			Varr[i]=i;
		}
		// set disjoincent set
		Edge[] Earr = new Edge[EdgeNum];
		for(int i=0;i<EdgeNum;i++)
		{
			stk = new StringTokenizer(br.readLine());
			Earr[i] = new Edge(Integer.parseInt(stk.nextToken()),Integer.parseInt(stk.nextToken()),Integer.parseInt(stk.nextToken()));
		}
		Arrays.sort(Earr,new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return o2.cost-o1.cost;
			}			
		});
		// set Edge
		int answer = 0;
		for(int i=0;i<EdgeNum;i++)
		{
			if(getParent(Earr[i].from)!=getParent(Earr[i].to))
			{
				setParent(Earr[i].from, Earr[i].to);
				answer+=Earr[i].cost;
			}
		}
		System.out.println(answer);
	}//main
}
