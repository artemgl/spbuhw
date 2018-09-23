#include <iostream>
using namespace std;

int raiseToPower(int, int);

int main()
{
    cout << "Enter a\n";
    int a = 0;
    cin >> a;

    cout << "Enter n\n";
    int n = 0;
    cin >> n;

    cout << a << " ^ " << n << " = ";
    cout << raiseToPower(a, n);

    return 0;
}

int raiseToPower(int x, int y)
{
    int result = 1;

    if (y > 0)
    {
        result = raiseToPower(x, y - 1) * x;
    }

    return result;
}
