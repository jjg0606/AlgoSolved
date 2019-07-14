#include <iostream>
#pragma once

class LinkedList //act like linked que
{
	class Node
	{
	public:
		int value;
		Node* next;
		Node* before;
		Node(int _value);
	};


private:
	Node* head;
	Node* tail;
	int length;
public:

	LinkedList();
	// HEAD (old one) ----- tail(new one)
	~LinkedList();


	void pushHead(int value);

	void pushTail(int value);

	int peekHead();

	int peekTail();

	int popHead();

	int popTail();

	int Size();

	void Print();


};