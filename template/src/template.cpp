#include <iostream>
using namespace std;

template < class T, class U>
T cast (U a) {
  return (T)a;
}

template <class T>
class D{
public:
	D();
	void my_set(T n);
	T *my_get();
	void my_show();
private:
	T *a;
	int current_length;
	int max_length;
};

template <class T>
D<T>::D(){
max_length = 5;
current_length = 0;
 a = new T[max_length];
}

template <class T>
void D<T>::my_set(T n){
if(current_length == max_length){
	max_length += 5;
	T* anew = new T[max_length];
	for(int i=0; i<max_length; i++) anew[i] = a[i];

	delete [] a;
	a = anew;
}
else{
	a[current_length] = n;
	current_length += 1;
}
}
template <class T>
void D<T>::my_show(){
for(int i = 0; i < 5; i++) cout << a[i] << endl;
}
int main () {
	unsigned char l='d';

	cout.setf(ios::fixed);
	cout.setf(ios::showpoint);
	cout.precision(4);
 //cout<< cast< double>(l) << l;

	D<string> d;
	d.my_set("hello");
	d.my_set("z");
d.my_show();

	return 0;
}
