#include <iostream>
#include <fstream>
#include "tree.h"
using namespace std;

int main()
{
    const char fileForReadingName[] = "input.txt";
    const char fileForWritingName[] = "output.txt";

    Tree *tree = buildTreeFromFile(fileForReadingName);

    decipherInFile(tree, fileForReadingName, fileForWritingName);

    deleteTree(tree);

    return 0;
}
