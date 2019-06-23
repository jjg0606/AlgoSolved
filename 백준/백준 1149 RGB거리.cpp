#include <iostream>
#include <vector>
using namespace std;
//1149 RGB거리

int main()
{
	int N; //집의 수
	cin >> N;
	vector<vector<int>> vec(N,vector<int>(3,0));
	for (int i = 0; i < N; i++)
	{
		cin >> vec[i][0] >> vec[i][1] >> vec[i][2];
	}
	for (int i = 1; i < N; i++)
	{
		vec[i][0] += vec[i - 1][1] < vec[i - 1][2] ? vec[i - 1][1] : vec[i - 1][2];
		vec[i][1] += vec[i - 1][0] < vec[i - 1][2] ? vec[i - 1][0] : vec[i - 1][2];
		vec[i][2] += vec[i - 1][1] < vec[i - 1][0] ? vec[i - 1][1] : vec[i - 1][0];

	}
	int answer = vec[N - 1][0] < vec[N - 1][1] ? vec[N - 1][0] : vec[N - 1][1];
	answer = answer < vec[N - 1][2] ? answer : vec[N - 1][2];
	cout << answer << endl;
	return 0;
}
