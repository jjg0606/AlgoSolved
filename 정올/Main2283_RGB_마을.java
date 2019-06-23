package Solving;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2283_RGB_마을 {
	
	static class Cost
	{
		int r;
		int g;
		int b;
		public Cost(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
		public int Get(int c)
		{
			switch(c)
			{
			case 0:
				return this.r;
			case 1:
				return this.g;
			case 2:
				return this.b;
			default:
				return 0;
			}
		}
	}
	// 0은 R, 1은 G, 2는 B
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("INPUT.TXT")));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("OUTPUT.TXT")));
		int homeNum = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		
		Cost[] costarr = new Cost[homeNum];
		for(int i=0;i<homeNum;i++)
		{
			stk = new StringTokenizer(br.readLine());
			costarr[i] = new Cost(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
		}		
		// insertion end
		for(int i=1;i<homeNum;i++)
		{
			costarr[i].r+=Math.min(costarr[i-1].g, costarr[i-1].b);
			costarr[i].g+=Math.min(costarr[i-1].r, costarr[i-1].b);
			costarr[i].b+=Math.min(costarr[i-1].r, costarr[i-1].g);
		}
		int answer = Math.min(costarr[homeNum-1].r, Math.min(costarr[homeNum-1].g, costarr[homeNum-1].b));
		System.out.println(answer);
	}
}
