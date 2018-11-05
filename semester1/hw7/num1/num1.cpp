#include <iostream>
#include "bstset.h"
using namespace std;

int main()
{
    Tree *tree = createTree();

    printTree(tree);
    printTreeAscending(tree);
    printTreeDescending(tree);

    addElement(tree, 7);
    addElement(tree, 4);
    addElement(tree, 9);
    addElement(tree, 2);
    addElement(tree, 6);
    addElement(tree, 8);
    addElement(tree, 10);
    addElement(tree, 1);
    addElement(tree, 3);
    addElement(tree, 5);

    printTree(tree);
    printTreeAscending(tree);
    printTreeDescending(tree);

    for (int i = 0; i < 12; i++)
    {
        cout << i << ' ' << (belongs(tree, i) ? "belongs" : "doesn't belong") << " to the set" << endl;
    }

    cout << endl;

    removeElement(tree, 7);
    removeElement(tree, 9);
    removeElement(tree, 6);
    removeElement(tree, 2);

    printTree(tree);
    printTreeAscending(tree);
    printTreeDescending(tree);

    for (int i = 0; i < 12; i++)
    {
        cout << i << ' ' << (belongs(tree, i) ? "belongs" : "doesn't belong")<< " to the set" << endl;
    }

    deleteTree(tree);

    return 0;
}
