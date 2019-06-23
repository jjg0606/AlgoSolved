#include <iostream>
#include <vector>
using namespace std;
//15649 N°ú M(1)

int N, M;
int* picked;
int* order;
void combination(int prev,int cur)
{
	if (cur == M)
	{
		for (int i = 0; i < M; i++)
		{
			cout << order[i] << ' ';
		}
		cout << '\n';
		return;
	}

	for (int i = 1; i <= N; i++)
	{
		if (picked[i] == 0)
		{
			picked[i] = 1;
			order[cur] = i;
			combination(i + 1, cur + 1);
			picked[i] = 0;
		}		
	}
}

int main()
{
	cin >> N >> M;
	picked = new int[N+1];
	for (int i = 1; i <= N; i++)
	{
		picked[i] = 0;
	}

	order = new int[M];
	combination(1,0);
	cout.flush();

	delete[] picked,order;
	return 0;
}

