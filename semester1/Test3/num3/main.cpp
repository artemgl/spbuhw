#include <iostream>
#include "graph.h"
using namespace std;

int main()
{
    const char fileName[] = "file.txt";

    Graph *graph = readFromFile(fileName);

    printEspecialTops(graph);

    deleteGraph(graph);

    return 0;
}
