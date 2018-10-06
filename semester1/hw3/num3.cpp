#include <iostream>
using namespace std;

int raiseToPower(int &a, int y);

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

int raiseToPower(int &a, int y)
{
    if (y == 0 || a == 1)
    {
        return 1;
    }

    if (y == 1)
    {
        return a;
    }

    int k = 2;
    int logK = 1;
    while (k < y)
    {
        k *= 2;
        logK++;
    }

    bool isSmall = false;
    if (y - k / 2 <= k - y)
    {
        isSmall = true;
    }

    if (isSmall)
    {
        k /= 2;
        logK--;
    }

    int result = a;
    for (int j = 1; j <= logK; j++)
    {
        result *= result;
    }

    if (isSmall)
    {
        return result * raiseToPower(a, y - k);
    }
    else
    {
        return result / raiseToPower(a, k - y);
    }
}
