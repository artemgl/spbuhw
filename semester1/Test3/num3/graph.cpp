#include <iostream>
#include <fstream>
#include "graph.h"
using namespace std;

Graph *createGraph(int topsAmount, int edgesAmount)
{
    Graph *newGraph = new Graph {topsAmount, edgesAmount, nullptr};
    newGraph->matrix = new int *[topsAmount] {};
    for (int i = 0; i < topsAmount; i++)
    {
        newGraph->matrix[i] = new int[edgesAmount] {};
    }

    return newGraph;
}

void deleteGraph(Graph *graph)
{
    for (int i = 0; i < graph->topsAmount; i++)
    {
        delete[] graph->matrix[i];
    }
    delete[] graph->matrix;

    delete graph;
}

void addRelation(Graph *graph, int firstTop, int secondTop, int edge)
{
    graph->matrix[firstTop][edge] = 1;
    graph->matrix[secondTop][edge] = -1;
}

Graph *readFromFile(const char fileName[])
{
    ifstream fin(fileName);

    int topsAmount = 0;
    fin >> topsAmount;
    int edgesAmount = 0;
    fin >> edgesAmount;

    int **numbers = new int *[topsAmount] {};
    for (int i = 0; i < topsAmount; i++)
    {
        numbers[i] = new int[edgesAmount] {};
    }

    for (int i = 0; i < topsAmount; i++)
    {
        for (int j = 0; j < edgesAmount; j++)
        {
            fin >> numbers[i][j];
        }
    }

    fin.close();

    return new Graph {topsAmount, edgesAmount, numbers};
}

bool allVisited(Graph *graph, int top, bool *visited)
{
    for (int i = 0; i < graph->topsAmount; i++)
    {
        if (i != top && visited[i] == false)
        {
            return false;
        }
    }

    return true;
}

void printEspecialTops(Graph *graph, int top, bool *visited)
{
    for (int i = 0; i < graph->edgesAmount; i++)
    {
        if (graph->matrix[top][i] == 1)
        {
            for (int j = 0; j < graph->topsAmount; j++)
            {
                if (graph->matrix[j][i] == -1)
                {
                    visited[j] = true;
                    printEspecialTops(graph, j, visited);
                }
            }
        }
    }
}

void printEspecialTops(Graph *graph)
{
    bool *visited = new bool[graph->topsAmount] {};
    for (int i = 0; i < graph->topsAmount; i++)
    {
        printEspecialTops(graph, i, visited);
        if (allVisited(graph, i, visited))
        {
            cout << i << endl;
        }

        for (int i = 0; i < graph->topsAmount; i++)
        {
            visited[i] = false;
        }

    }
    delete[] visited;
}
