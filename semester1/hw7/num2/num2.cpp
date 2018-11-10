#include <iostream>
#include "avltreeset.h"
using namespace std;

int main()
{
    Tree *tree = createTree();

    cout << "Enter:" << endl;
    cout << "0 - exit" << endl;
    cout << "1 - add the value" << endl;
    cout << "2 - remove the value" << endl;
    cout << "3 - check if the value belongs to the set" << endl;
    cout << "4 - print the set ascending" << endl;
    cout << "5 - print the set descending" << endl;
    cout << "6 - print the tree in format: (a b c)" << endl;

    int input = 0;
    do
    {
        cin >> input;

        switch (input)
        {
        case 1:
        {
            cout << "Enter the value: ";
            int number = 0;
            cin >> number;

            addElement(tree, number);
            cout << "The value added successfully" << endl;
            break;
        }
        case 2:
        {
            cout << "Enter the value: ";
            int number = 0;
            cin >> number;

            removeElement(tree, number);
            cout << "The value removed successfully" << endl;
            break;
        }
        case 3:
        {
            cout << "Enter the value: ";
            int number = 0;
            cin >> number;

            cout << number << " " << (belongs(tree, number) ? "belongs" : "doesn't belong") << " to the set" << endl;
            break;
        }
        case 4:
        {
            printTreeAscending(tree);
            break;
        }
        case 5:
        {
            printTreeDescending(tree);
            break;
        }
        case 6:
        {
            printTree(tree);
            break;
        }
        }
    }
    while (input);

    deleteTree(tree);

    return 0;
}
