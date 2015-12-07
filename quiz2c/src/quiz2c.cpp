//============================================================================
// Name        : quiz2c.cpp
// Author      : cbb
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <vector>
using namespace std;

int main() {

	vector<int> v;
	v.reserve(65);
	v[2] = 112;
	for(int i=0;i<11;i++){
	cout << v[i]<< "\n";
	}
	return 0;
}
