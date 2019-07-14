#include <iostream>
#include <memory>
#include <vector>
#include "LinkedList.h"
#include "HeapTree.h"
#include "Table.h"
#include "QuickSort.h"
using namespace std;

int main()
{
	//LinkedList ll;
	//ll.pushTail(3);
	//ll.pushTail(2);
	//ll.pushTail(1);
	//ll.pushHead(2);
	//ll.pushHead(3);
	//ll.Print();
	//cout << "pop tail " << ll.popTail() << '\n';
	//ll.Print();
	//cout << "pop tail " << ll.popTail() << '\n';
	//ll.Print();
	//cout << "pop tail " << ll.popTail() << '\n';
	//ll.Print();
	//cout << "pop tail " << ll.popTail() << '\n';
	//ll.Print();
	//cout << "pop tail " << ll.popTail() << '\n';
	//ll.Print();
	//cout << "pop tail " << ll.popTail() << '\n';
	//ll.Print();

	//HeapTree ht;
	//ht.Print();
	//ht.push(99);
	//ht.push(10);
	//ht.push(99);
	//ht.Print();
	//cout << "pop " << ht.pop() << '\n';
	//ht.Print();
	//cout << "pop " << ht.pop() << '\n';
	//ht.Print();
	//cout << "pop " << ht.pop() << '\n';
	//ht.Print();

	//Table table;
	//table.Add("1", 1);
	//table["1"] = 2;
	//std::cout << table["1"] << ' '<< table["2"] <<'\n';
	//table.Remove("1");
	//std::cout << table["1"] << '\n';

	int arr[10] = {5,4,3,2,1,7,6,7,8,9};
	QuickSort::Sort(arr, 10);
	for (int i = 0; i < 10; i++)
	{
		cout << arr[i] << ' ';
	}
	cout << '\n';
	cout << "find 10 " << QuickSort::Find(arr, 10, 10) << '\n';
	cout << "find 5 " << QuickSort::Find(arr, 10, 5) << '\n';
	cout << "find 1 " << QuickSort::Find(arr, 10, 1) << '\n';

	return 0;
}

