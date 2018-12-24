#pragma once

struct List;
struct ListElement;
struct Graph;

Graph *createGraph(int topsAmount);
void deleteGraph(Graph *graph);

void addRelation(Graph *graph, int firstTop, int secondTop);

void printGraph(Graph *graph);

List *buildWay(Graph *graph, int start, int end);
