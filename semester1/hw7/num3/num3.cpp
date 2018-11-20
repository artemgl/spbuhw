#include <iostream>
#include "ast.h"
using namespace std;

int main()
{
    char const fileName[] = "file.txt";

    Tree *tree = createTree();

    copyTreeFromFile(tree, fileName);

    printTree(tree);
    cout << "Answer is: " << calculate(tree);

    deleteTree(tree);

    return 0;
}
