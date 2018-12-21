#pragma once

struct Graph;

Graph *createGraph(int topsAmount);
void deleteGraph(Graph *graph);

void addRelation(Graph *graph, int firstTop, int secondTop, int distance);

void addNearest(Graph *graph, int town, bool *visited, bool **states);
