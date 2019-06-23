#include <iostream>
#include <vector>
#include <memory.h>
using namespace std;


int main()
{
	int X;
	cin >> X;
	int* dp = new int[X + 1];
	/*for (int i = 0; i < X + 1; i++)
	{
		dp[i] = 0;
	}*/
	memset(dp, 0, sizeof(int)*(X+1));
	dp[X] = 0;

	for (int i = X; i > 1; i--)
	{

		if ((i & 1) == 0) // 2·Î ³ª´©¾î ¶³¾îÁü
		{
			int next = i >> 1;
			if (dp[next] == 0 || dp[next] > dp[i] + 1)
			{
				dp[next] = dp[i] + 1;
			}
		}

		if (i % 3 == 0)
		{
			int next = i / 3;
			if (dp[next] == 0 || dp[next] > dp[i] + 1)
			{
				dp[next] = dp[i] + 1;
			}
		}
		
		if (dp[i - 1] == 0 || dp[i - 1] > dp[i] + 1)
		{
			dp[i - 1] = dp[i] + 1;
		}
	}
	cout << dp[1] << endl;

	delete[] dp;
	return 0;
}
