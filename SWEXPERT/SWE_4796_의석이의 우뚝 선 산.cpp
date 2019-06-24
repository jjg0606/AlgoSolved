#include <iostream>
#include <vector>

using namespace std;

int solve()
{
	int mtNum;
	cin >> mtNum;

	//vector<int> hVector(mtNum);
	int* hArr = new int[mtNum];
	for (int i = 0; i < mtNum; i++)
	{
		cin >> hArr[i];
	}
	// input end
	int count = 0;

	for (int i = 1; i < mtNum-1; i++)
	{
		// cant be a thumb
		if (hArr[i - 1] > hArr[i] || hArr[i] < hArr[i + 1])
		{
			continue;
		}

		int left = 1;
		while (i - left - 1 >= 0 && hArr[i - left - 1] < hArr[i - left])
		{
			left++;
		}

		int right = 1;
		while (i + right + 1 < mtNum && hArr[i + right] > hArr[i + right + 1])
		{
			right++;
		}
		
		count += left * right;

		i += right;
	}

	delete[] hArr;
	return count;
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