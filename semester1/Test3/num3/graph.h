#pragma once

struct Graph
{
    int topsAmount;
    int edgesAmount;
    int **matrix;
};

Graph *createGraph(int topsAmount, int edgesAmount);
void deleteGraph(Graph *graph);

void addRelation(Graph *graph, int firstTop, int secondTop, int edge);

Graph *readFromFile(const char fileName[]);

void printGraph(Graph *graph);
void printMatrix(Graph *graph);

void printEspecialTops(Graph *graph);
