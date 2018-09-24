#include <iostream>
using namespace std;

void reverse(int a, int b, int num[]);

int main()
{
    int const m = 8;
    int const n = 5;

    int x[m + n + 1] = {};

    for (int i = 1; i <= m; i++)
    {
        x[i] = i;
        cout << x[i] << ", ";
    }

    for (int i = m + 1; i <= m + n; i++)
    {
        x[i] = 10 + i - m;
        cout << x[i] << ", ";
    }

    cout << "\n";

    reverse(m + 1, m + n, x);
    reverse(1, m, x);
    reverse(1, m + n, x);

    for (int i = 1; i <= m + n; i++)
    {
        cout << x[i] << ", ";
    }

    return 0;
}

void reverse(int a, int b, int x[])
{
    for (int j = a; j <= a - 1 + (b - a + 1) / 2; j++)
    {
        swap(x[j], x[a + b - j]);
    }

}
