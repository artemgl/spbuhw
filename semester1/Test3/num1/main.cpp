#include <iostream>
#include "list.h"
using namespace std;

int main()
{
    int input = 1;

    List *list = createList();

    cout << "Enter the number: ";
    cin >> input;

    do
    {
        addElement(list, input);

        cout << "Enter the number: ";
        cin >> input;
    }
    while (input);

    printList(list);

    deleteList(list);

    return 0;
}
