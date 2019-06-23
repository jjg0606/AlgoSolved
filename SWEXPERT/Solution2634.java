package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution2634 {
	
	
	static class Animal
	{
		int x;
		int y;
		public Animal(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int firingnum = Integer.parseInt(stk.nextToken());
		int animalnum  = Integer.parseInt(stk.nextToken());
		int range  = Integer.parseInt(stk.nextToken());
		// first
		//ArrayList<Integer> firingarr = new ArrayList<>();
		int[] firingarr = new int[firingnum];
		stk = new StringTokenizer(br.readLine());
		for(int i=0;i<firingnum;i++)
		{
			firingarr[i]=Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(firingarr);
		//firing sort
		Animal[] animalarr = new Animal[animalnum];
		for(int i=0;i<animalnum;i++)
		{
			stk = new StringTokenizer(br.readLine());
			animalarr[i]=new Animal(Integer.parseInt(stk.nextToken()),Integer.parseInt(stk.nextToken()));
		}
		Arrays.sort(animalarr, new Comparator<Animal>() {
			@Override
			public int compare(Animal o1, Animal o2) {
				// TODO Auto-generated method stub
				
				return o2.x>o1.x?-1:1;
			}
		});
		//third
		//insertion and sorting end
		float[] threshold = new float[firingnum];
		for(int i=0;i<firingnum-1;i++)
		{
			float middle = (firingarr[i]+firingarr[i+1])/2;
			threshold[i]=middle;
		}
		threshold[firingnum-1]=1000000000;
		//threshold setting
		int answer=0;
		int findex=0;
		for(int i=0;i<animalnum;i++)
		{
			while(!(animalarr[i].x<=threshold[findex]))
			{
				findex++;
			}
			if(animalarr[i].y<=range-Math.abs(firingarr[findex]-animalarr[i].x))
			{
				answer++;
			}
		}
		System.out.println(answer);
	}
}
