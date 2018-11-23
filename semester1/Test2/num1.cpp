#include <iostream>
using namespace std;

int main()
{
    cout << "Enter the index of Fibonacci number" << endl;
    int index = 0;
    cin >> index;

    int current = 0;
    int next = 1;

    for (int i = 0; i < index; i++)
    {
        current += next;
        swap(current, next);
    }

    cout << index << "th number is ";
    cout << current << endl;

    return 0;
}
