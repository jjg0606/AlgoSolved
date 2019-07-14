#include <iostream>
#pragma once

class HeapTree
{
private :
	int* innerArr;

	int innerSize;

	int maxIndex;

	void swap(int index1, int index2);

	int getSmallerChild(int parent);

	static constexpr int Empty = 0;

	static int getParent(int index);

	static int getRightChild(int index);

	static int getLeftChild(int index);

public :
	HeapTree(int depth);

	HeapTree();

	~HeapTree();

	bool push(int value);

	int pop();

	int Peek();

	int getSize();

	void Print();
};

