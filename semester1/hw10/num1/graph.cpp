#include <iostream>
#include "list.h"
#include "graph.h"
using namespace std;

struct Graph
{
    int topsAmount;
    List **tops;
};

Graph *createGraph(int topsAmount)
{
    Graph *newGraph = new Graph {topsAmount, nullptr};

    newGraph->tops = new List *[topsAmount] {};

    return newGraph;
}

void deleteGraph(Graph *graph)
{
    for (int i = 0; i < graph->topsAmount; i++)
    {
        if (graph->tops[i])
        {
            deleteList(graph->tops[i]);
        }
    }

    delete[] graph->tops;
    delete graph;
}

void addRelation(Graph *graph, int firstTop, int secondTop)
{
    for (int i = 0; i < graph->topsAmount; i++)
    {
        if (i == firstTop)
        {
            if (!graph->tops[i])
            {
                graph->tops[i] = createList();
            }

            addElement(graph->tops[i], secondTop);
        }
    }
}

void printGraph(Graph *graph)
{
    for (int i = 0; i < graph->topsAmount; i++)
    {
        if (graph->tops[i])
        {
            cout << i << "-> : ";
            printList(graph->tops[i]);
            cout << endl;
        }
    }
}

List *buildWay(Graph *graph, int start, int end)
{
    if (start == end)
    {
        List *result = createList();
        addElement(result, start);
        return result;
    }

    List *result = buildWay(graph, start, graph->tops[end]->first->top);
    addElement(result, end);

    return result;
}
