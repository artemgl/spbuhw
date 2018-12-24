#include <iostream>
#include "astar.h"
#include "list.h"
#include "pointlist.h"
using namespace std;

PointList *createPointList()
{
    return new PointList {nullptr};
}

void deleteList(PointList *list)
{
    if (!list)
    {
        return;
    }

    PointListElement *current = list->first;
    while (current)
    {
        deletePoint(current->point);

        list->first = current->next;
        delete current;
        current = list->first;
    }

    delete list;
}

void clearList(PointList *list)
{
    PointListElement *current = list->first;
    while (current)
    {
        list->first = current->next;
        delete current;
        current = list->first;
    }

    delete list;
}

void addElement(PointListElement *&current, Point *point)
{
    if (current)
    {
        addElement(current->next, point);
        return;
    }

    current = new PointListElement {point, nullptr};
}

void addElement(PointList *list, Point *point)
{
    addElement(list->first, point);
}

void removeElement(PointListElement *&listElement, Point *point)
{
    if (!listElement)
    {
        return;
    }

    if (listElement->point->x == point->x && listElement->point->y == point->y)
    {
        PointListElement *extra = listElement->next;
        delete listElement;
        listElement = extra;
        return;
    }

    removeElement(listElement->next, point);
}

void removeElement(PointList *list, Point *point)
{
    removeElement(list->first, point);
}

void printList(PointList *list)
{
    PointListElement *current = list->first;
    while (current)
    {
        cout << current->point->x << ' ' << current->point->y << endl;
        current = current->next;
    }
}

bool isEmpty(PointList *list)
{
    return !(list->first);
}

bool exists(PointList *list, Point *point)
{
    PointListElement *current = list->first;

    while (current)
    {
        if (current->point->x == point->x && current->point->y == point->y)
        {
            return true;
        }

        current = current->next;
    }

    return false;
}

PointList *buildWay(List *way, Map *map)
{
    ListElement *current = way->first;

    PointList *result = createPointList();

    while (current)
    {
        addElement(result, intHash(map, current->top));
        current = current->next;
    }

    return result;
}
