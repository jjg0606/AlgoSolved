#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
//2651 자동차 경주 대회



int main()
{
	ios::sync_with_stdio(false); 
	cin.tie(NULL); 
	cout.tie(NULL);
	int drivemax, stationN;
	cin >> drivemax>>stationN;
	vector<int> dist(stationN);
	for (int i = 0; i < stationN; i++)
	{
		cin >> dist[i];
	}
	vector<int> cost(stationN);
	for (int i = 1; i < stationN-1; i++)
	{
		cin >> cost[i];
	}
	/// insertion end
	vector<int> dprepair(stationN,0);
	vector<int> dpnorepair(stationN,0);
	int left = drivemax;
	for (int i = 0; i < stationN; i++)
	{
		if (left >= dist[i])
		{
			left -= dist[i];
			if (i > 0)
			{
				dpnorepair[i] = dpnorepair[i - 1] < dprepair[i - 1] ? dpnorepair[i - 1] : dprepair[i - 1];
				dprepair[i] = dpnorepair[i - 1] < dprepair[i - 1] ? dpnorepair[i - 1] : dprepair[i - 1];
			}
			dprepair[i] += cost[i];
		}
		else
		{
			left = drivemax - dist[i];
			dpnorepair[i] = dprepair[i - 1];
			dprepair[i] = dprepair[i - 1]+cost[i];
		}
	}

	return 0;
}

