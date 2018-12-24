#pragma once

struct PointList;
struct Graph;

struct Point
{
    int x;
    int y;
    int g;
    int f;
};

struct Map
{
    int rowsAmount;
    int columnsAmount;
    bool **matrix;
};

Map *readMapFromFile(const char fileName[]);
void deleteMap(Map *map);

Point *createPoint(int x, int y);
void deletePoint(Point *point);

int h(Point *start, Point *end);
void updateF(Point *point, Point *end);

int pointHash(Map *map, Point *point);
Point *intHash(Map *map, int hash);

PointList *buildGraph(Map *map, Point *start, Point *end);

void printWay(Map *map, PointList *way);
