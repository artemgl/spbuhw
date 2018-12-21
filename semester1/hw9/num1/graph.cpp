#include <iostream>
#include "graph.h"
#include "list.h"
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

void addRelation(Graph *graph, int firstTop, int secondTop, int distance)
{
    for (int i = 0; i < graph->topsAmount; i++)
    {
        if (i == firstTop)
        {
            if (!graph->tops[i])
            {
                graph->tops[i] = createList();
            }

            addElement(graph->tops[i], secondTop, distance);
        }
    }
}

void addNearest(Graph *graph, int town, bool *visited, bool **states)
{
    int minDistance = 0x7FFFFFFF;
    int nextTopToAdd = -1;

    for (int i = 0; i < graph->topsAmount; i++)
    {
        if (states[town][i])
        {
            ListElement *current = graph->tops[i]->first;

            while (current)
            {
                if (!visited[current->top])
                {
                    if (current->distance < minDistance)
                    {
                        minDistance = current->distance;
                        nextTopToAdd = current->top;
                    }
                    break;
                }

                current = current->next;
            }
        }
    }

    if (nextTopToAdd != -1)
    {
        visited[nextTopToAdd] = true;
        states[town][nextTopToAdd] = true;
    }
}
