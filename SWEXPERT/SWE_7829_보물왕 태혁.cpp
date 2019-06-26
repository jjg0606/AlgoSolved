#include <iostream>
#include <vector>

using namespace std;

int solve()
{
	int numCount;
	cin >> numCount;

	int min;
	cin >> min;
	int max = min;

	int read;
	for (int i = 1; i < numCount; i++)
	{
		cin >> read;
		min = read < min ? read : min;
		max = read > max ? read : max;
	}

	// insertion ends



	return min * max;
}


int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int testCase;
	cin >> testCase;

	for (int tc = 1; tc <= testCase; tc++)
	{
		cout << '#' << tc << ' ' << solve() << '\n';
	}
	cout.flush();


	return 0;
}