package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main11508_2더하기1_세일 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Num = Integer.parseInt(br.readLine().trim());
		int[] arr = new int[Num];
		
		int sum = 0;
		for(int i=0;i<Num;i++)
		{
			int read = Integer.parseInt(br.readLine().trim());
			sum+=read;
			arr[i]=read;
		}
		int index = arr.length-3;
		Arrays.sort(arr);
		while(index>=0)
		{
			sum-=arr[index];
			index-=3;
		}
		System.out.println(sum);
	}
}
