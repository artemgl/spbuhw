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

void printGraph(Graph *graph)
{
    for (int i = 0; i < graph->edgesAmount; i++)
    {
        for (int j = 0; j < graph->topsAmount; j++)
        {
            if (graph->matrix[j][i] > 0)
            {
                cout << j;
            }
        }
        cout << "->";
        for (int j = 0; j < graph->topsAmount; j++)
        {
            if (graph->matrix[j][i] < 0)
            {
                cout << j << endl;
            }
        }
    }
}

void printMatrix(Graph *graph)
{
    for (int i = 0; i < graph->topsAmount; i++)
    {
        for (int j = 0; j < graph->edgesAmount; j++)
        {
            cout << graph->matrix[i][j] << ' ';
        }
        cout << endl;
    }
}

bool allVisited(bool *visited, int size)
{
    for (int i = 0; i < size; i++)
    {
        if (!visited[i])
        {
            return false;
        }
    }

    return true;
}

void nullArray(bool *visited, int size)
{
    for (int i = 0; i < size; i++)
    {
        visited[i] = false;
    }
}

void pointOutTops(Graph *graph, int top, bool *visited)
{
    for (int i = 0; i < graph->edgesAmount; i++)
    {
        if (graph->matrix[top][i] < 0)
        {
            for (int j = 0; j < graph->topsAmount; j++)
            {
                if (graph->matrix[j][i] > 0 && !visited[j])
                {
                    visited[j] = true;
                    pointOutTops(graph, j, visited);
                    break;
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
        visited[i] = true;
        pointOutTops(graph, i, visited);
        if (allVisited(visited, graph->topsAmount))
        {
            cout << i << endl;
        }
        nullArray(visited, graph->topsAmount);
    }

    delete[] visited;
}
