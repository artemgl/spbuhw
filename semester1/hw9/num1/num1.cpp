#include <iostream>
#include <fstream>
#include "graph.h"
using namespace std;

bool allVisited(bool *visited, int size);
bool isCapital(int town, bool **states);

int main()
{
    const char fileName[] = "file.txt";

    ifstream fin(fileName);

    int townsAmount = 0;
    fin >> townsAmount;
    Graph *graph = createGraph(townsAmount);

    int roadsAmount = 0;
    fin >> roadsAmount;

    for (int i = 0; i < roadsAmount; i++)
    {
        int firstTop = 0;
        int secondTop = 0;
        int distance = 0;
        fin >> firstTop >> secondTop >> distance;
        addRelation(graph, firstTop, secondTop, distance);
        addRelation(graph, secondTop, firstTop, distance);
    }

    int capitalsAmount = 0;
    fin >> capitalsAmount;

    bool **states = new bool *[townsAmount] {};
    bool *visited = new bool[townsAmount] {};

    int town = 0;
    for (int i = 0; i < capitalsAmount; i++)
    {
        fin >> town;
        visited[town] = true;
        states[town] = new bool[townsAmount] {};
        states[town][town] = true;
    }

    fin.close();

    while (!allVisited(visited, townsAmount))
    {
        for (int i = 0; i < townsAmount; i++)
        {
            if (isCapital(i, states))
            {
                addNearest(graph, i, visited, states);
            }
        }
    }

    int state = 0;
    for (int i = 0; i < townsAmount; i++)
    {
        if (isCapital(i, states))
        {
            cout << state++ << ": ";
            for (int j = 0; j < townsAmount; j++)
            {
                if (states[i][j])
                {
                    cout << j << ' ';
                }
            }
            cout << endl;
        }
    }

    deleteGraph(graph);
    delete[] visited;

    for (int i = 0; i < townsAmount; i++)
    {
        delete[] states[i];
    }
    delete[] states;

    return 0;
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

bool isCapital(int town, bool **states)
{
    return states[town];
}
