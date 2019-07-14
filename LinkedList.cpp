#include "LinkedList.h"


LinkedList::Node::Node(int _value) : value(_value), next(nullptr), before(nullptr) {};

LinkedList::LinkedList() : head(nullptr), tail(nullptr), length(0)
{
	std::cout << "created" << '\n';
};


LinkedList::~LinkedList() {
	while (this->head != nullptr)
	{
		Node* temp = this->head->before;
		delete this->head;
		this->head = temp;
	}
	std::cout << "delete" << '\n';
};


void LinkedList::pushHead(int value) {
	Node* cur = new Node(value);

	if (length == 0)
	{
		this->tail = cur;
		this->head = cur;
	}
	else
	{
		cur->before = this->head;
		this->head->next = cur;
		this->head = cur;
	}

	this->length++;
};

void LinkedList::pushTail(int value)
{
	Node* cur = new Node(value);

	if (length == 0)
	{
		this->tail = cur;
		this->head = cur;
	}
	else
	{
		cur->next = this->tail;
		this->tail->before = cur;
		this->tail = cur;
	}

	this->length++;
};

int LinkedList::peekHead()
{
	return this->head == nullptr ? 0 : this->head->value;
};

int LinkedList::peekTail()
{
	return this->tail == nullptr ? 0 : this->tail->value;
};

int LinkedList::popHead()
{
	if (this->head == nullptr)
	{
		return 0;
	}

	int value = this->head->value;
	Node* temp = this->head->before;
	delete this->head;

	if (temp == nullptr)
	{
		this->head = nullptr;
		this->tail = nullptr;
	}
	else
	{
		temp->next = nullptr;
		this->head = temp;
	}


	this->length--;

	return value;
};

int LinkedList::popTail()
{
	if (this->tail == nullptr)
	{
		return 0;
	}

	int value = this->tail->value;
	Node* temp = this->tail->next;
	delete this->tail;

	if (temp == nullptr)
	{
		this->head = nullptr;
		this->tail = nullptr;
	}
	else
	{
		temp->before = nullptr;
		this->tail = temp;
	}

	this->length--;

	return value;
};

int LinkedList::Size()
{
	return this->length;
};

void LinkedList::Print()
{
	Node* cur = this->tail;
	while (cur != nullptr)
	{
		std::cout << cur->value << ' ';
		cur = cur->next;
	}
	std::cout << '\n';
};