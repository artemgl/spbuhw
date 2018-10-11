#include <iostream>
#include "cycliclist.h"
using namespace std;

int main()
{
    CyclicList *cyclicList = createCyclicList();

    int n = 0;
    cout << "Enter n\n";
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        addToEnd(cyclicList, i);
    }

    int m = 0;
    cout << "Enter m\n";
    cin >> m;

    int index = 1;
    while (n > 1)
    {
        index += m - 1;
        while (index > n)
        {
            index -= n;
        }
        removeElement(cyclicList, index);
        n--;
    }

    cout << "Value of k is: " << returnElement(cyclicList, 1);

    deleteCyclicList(cyclicList);

    return 0;
}
