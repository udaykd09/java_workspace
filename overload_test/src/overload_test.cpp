//============================================================================
// Name        : overload_test.cpp
// Author      : cbb
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
using namespace std;

int a(int x){
	return 1;
}

string a(int x){
	return "hello";
}

int a(int x, bool y = false){
	return 2;
}

int main() {
	int i=1;
	cout << a(i) ;
	return 0;
}
