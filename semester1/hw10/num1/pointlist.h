#pragma once

struct Point;
struct Map;
struct List;

struct PointListElement
{
    Point *point;
    PointListElement *next;
};

struct PointList
{
    PointListElement *first;
};

PointList *createPointList();
void deleteList(PointList *list);
void clearList(PointList *list);

void addElement(PointList *list, Point *point);
void removeElement(PointList *list, Point *point);
void printList(PointList *list);

bool isEmpty(PointList *list);
bool exists(PointList *list, Point *point);

PointList *buildWay(List *way, Map *map);
