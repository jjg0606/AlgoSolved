#include <iostream>
#include <vector>

using namespace std;

int get(long long a,int b,int c)
{
	long long reval = 1;
	while (b > 0)
	{
		if (b % 2 == 1)
		{
			reval *= a;
			reval %= c;
		}
		a *= a;
		a %= c;
		b /= 2;
	}

	return reval;
}

int main()
{
	long long a;
	int b;
	int c;
	cin >> a >> b >> c;
	a %= c;
	cout << get(a, b, c);
	return 0;
}