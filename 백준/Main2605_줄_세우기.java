package Solving04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2605_줄_세우기 {
	public static void main(String[] args) throws Exception{
		ArrayList<Integer> arr = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
		{
			int read = Integer.parseInt(stk.nextToken());
			arr.add(arr.size()-read, i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arr.size();i++)
		{
			sb.append(arr.get(i)+1);
			sb.append(' ');
		}
		System.out.println(sb.toString());
	}
}
