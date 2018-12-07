#include <iostream>
#include "list.h"
using namespace std;

int main()
{
    cout << "Enter:" << endl;
    cout << "0 - exit" << endl;
    cout << "1 - add element to list" << endl;
    cout << "2 - sort list" << endl;
    cout << "3 - print list" << endl;

    List *list = createList();

    int input = 0;

    do
    {
        cin >> input;

        switch (input)
        {
        case 1:
        {
            cout << "Enter the value: ";
            int inputNumber = 0;
            cin >> inputNumber;

            addElement(list, inputNumber);

            break;
        }
        case 2:
        {
            sortList(list);
            cout << "The list sorted successfully" << endl;

            break;
        }
        case 3:
        {
            printList(list);
            cout << endl;

            break;
        }
        }
    }
    while (input);

    deleteList(list);

    return 0;
}
