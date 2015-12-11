#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <stack>
#include <algorithm>
#include <ctype.h>
#include <unistd.h>
#include <string>

using namespace std;

bool isOperator(char ch) {
	if (ch == '+' || ch == '-' || ch == '*' || ch == '/')
		return true;
	else
		return false;
}

int performOperation(int op1, int op2, char op) {
	int ans;
	switch (op) {
	case '+':
		ans = op2 + op1;
		break;
	case '-':
		ans = op2 - op1;
		break;
	case '*':
		ans = op2 * op1;
		break;
	case '/':
		ans = op2 / op1;
		break;
	}
	return ans;
}

int main(int argc, char **argv) {
	char buffer[15];
	int i, op1, op2, len, j, x;
	stack<int> s;
	int c;
	bool eflag = 0;
	char* exp = NULL;
	while ((c = getopt(argc, argv, "e:")) != -1)
		switch (c) {
		{
			case 'e':
			eflag = 1;
			exp = optarg;
			break;
		}
		{
			case '?':
			if (optopt == 'e')
				cerr << char(optopt) << " : requires an argument.\n";
			else if (isprint(optopt))
				cerr << "Unknown option : " << char(optopt) << endl;
			else
				cerr << "Unknown option character : " << char(optopt) << endl;
			return -1;
		}
		{
			default:
			abort();
		}
		}
//    printf("Enter a Postfix Expression: ( e.g. 23 34 * )\n");
//    gets(exp);
	bool flag = 0;
	string str = exp;
	len = str.size();
	j = 0;
	for (i = 0; i < len; i++) {
		if(len <= 4){
			cout << "Incorrect input" << endl;
			return -1;
		}
		if(i!=len-1 && exp[i] == '-' && exp[i+1]!=','){
			buffer[j++] = '-';
		}
		else if (exp[i] >= '0' && exp[i] <= '9') {
			buffer[j++] = exp[i];
		} else if (exp[i] == ',') {
			if (j > 0) {
				buffer[j] = '\0';
				x = atoi(buffer);
				s.push(x);
				j = 0;
			}
		}

		else if (isOperator(exp[i])) {
			op1 = s.top();
			s.pop();
			op2 = s.top();
			s.pop();
			s.push(performOperation(op1, op2, exp[i]));
		}
	}

	if(s.size()>1){
		cout << "Incorrect input" << endl;
		return -1;
	}
	else{
	cout << s.top() << endl;
	return 0;
	}
}
