#include <iostream>
#pragma once
class QuickSort
{
private:
	static void InnerSort(int* arr, int begin, int end);
	static void Swap(int* arr, int index1, int index2);
public :
	static void Sort(int* arr, int length);
	static bool Find(int* arr, int length, int value);
};

