#include <iostream>
#include <vector>
using namespace std;
//���� 11047 ����0


int main()
{
	int N;
	int K;	
	cin >> N >> K;	
	vector<int> coin(N, 0);
	for (int i = 0; i < N; i++)
	{
		cin >> coin[i];
	}
	int index = N - 1;
	int answer = 0;
	while (K > 0)
	{
		answer += K / coin[index];
		K %= coin[index];
		index--;
	}

	cout << answer << endl;
	return 0;
}
