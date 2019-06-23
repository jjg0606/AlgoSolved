package Solving04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2577_회전_초밥 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int DishNum = Integer.parseInt(stk.nextToken());
		int Spices = Integer.parseInt(stk.nextToken());
		int Serials = Integer.parseInt(stk.nextToken());
		int couponNum = Integer.parseInt(stk.nextToken());
		
		//
		int[] Dish = new int[DishNum];
		int[] eating = new int[Spices+1];
		for(int i=0;i<DishNum;i++)
		{
			Dish[i] = Integer.parseInt(br.readLine().trim());
		}
		// insertion end
		int max = 0;
		int cur = 0;
		int bonus = 0;
		// 첫 접시 처리
		for(int d=0;d<Serials;d++)
		{
			int next = d%DishNum;
			int get = Dish[next];
			if(eating[get]==0) // 중복이 있을때
			{
				cur++;
			}
			eating[get]++;
		}
		bonus = eating[couponNum] == 0 ? 1 : 0;
		max=cur+bonus;
		
		for(int i=1;i<DishNum;i++)
		{
			int prevFirst =Dish[i-1];
			int curLast = Dish[(i+Serials-1)%DishNum];
			
			eating[prevFirst]--;
			cur-=eating[prevFirst]==0?1:0;
			
			cur+=eating[curLast]==0 ? 1 : 0;
			eating[curLast]++;
			bonus = eating[couponNum] == 0 ? 1 : 0;
			
			max = max < cur+bonus ? cur+bonus : max ; 
		}
		System.out.println(max);
	}	
}
