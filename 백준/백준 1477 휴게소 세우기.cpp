#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
//1477 휴게소 세우기



int main()
{
	ios::sync_with_stdio(false); 
	cin.tie(NULL); 
	cout.tie(NULL);
	int N, M, L;
	cin >> N >> M >>  L;
	vector<int> dist(N + 1, 0);
	for (int i = 0; i < N; i++)
	{
		cin >> dist[i];
	}
	dist[N] = L;
	sort(dist.begin(), dist.end());

	int max = 0;
	for (int i = N; i > 0; i--)
	{
		dist[i] -= dist[i - 1];
		max = dist[i] > max ? dist[i] : max;
	}

	max = dist[0] > max ? dist[0] : max;
	int right = max;
	int left = 0;
	while (right - left > 1)
	{
		int mid = (right + left) / 2;
		int remain = M;
		for (int i = 0; i <= N; i++)
		{
			float ratio = dist[i] / (float)mid;
			if (ratio == 0)
			{
				continue;
			}
			remain -= (int)ratio - 1;
			if (ratio!= (int)ratio)
			{
				remain--;
			}
			if (remain < 0)
			{
				break;
			}
		}
		if (remain >= 0) // 가능 
		{
			right = mid;
		}
		else
		{
			left = mid;
		}
	}
	cout << right << endl;
	return 0;
}

