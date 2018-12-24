#include <iostream>
#include <fstream>
#include <cmath>
#include "astar.h"
#include "pointlist.h"
#include "list.h"
#include "graph.h"
using namespace std;

Map *readMapFromFile(const char fileName[])
{
    Map *newMap = new Map {};

    ifstream fin(fileName);

    fin >> newMap->rowsAmount;
    fin >> newMap->columnsAmount;

    newMap->matrix = new bool *[newMap->columnsAmount] {};
    for (int i = 0; i < newMap->columnsAmount; i++)
    {
        newMap->matrix[i] = new bool[newMap->rowsAmount] {};
    }

    for (int i = 0; i < newMap->rowsAmount; i++)
    {
        for (int j = 0; j < newMap->columnsAmount; j++)
        {
            fin >> newMap->matrix[i][j];
        }
    }

    fin.close();

    return newMap;
}

void deleteMap(Map *map)
{
    for (int i = 0; i < map->columnsAmount; i++)
    {
        delete[] map->matrix[i];
    }
    delete[] map->matrix;
    delete map;
}

Point *createPoint(int x, int y)
{
    return new Point {x, y, 0, 0};
}

void deletePoint(Point *point)
{
    delete point;
}

int h(Point *start, Point *end)
{
    double result = sqrt((start->x - end->x) * (start->x - end->x) + (start->y - end->y) * (start->y - end->y));
    return (int)(result + 0.5);
}

void updateF(Point *point, Point *end)
{
    point->f = point->g + h(point, end);
}

Point *minF(PointList *opened)
{
    Point *result = nullptr;
    int minimumF = 0x7FFFFFFF;

    PointListElement *current = opened->first;
    while (current)
    {
        if (current->point->f <= minimumF)
        {
            minimumF = current->point->f;
            result = current->point;
        }
        current = current->next;
    }

    return result;
}

int pointHash(Map *map, Point *point)
{
    return point->x + point->y * (map->rowsAmount);
}

Point *intHash(Map *map, int hash)
{
    int x = hash % map->rowsAmount;
    return new Point {x, (hash - x) / map->rowsAmount, 0, 0};
}

void fillPointListWithNeighbours(PointList *&neighbours, PointList *closed, Point *currentPoint, Map *map)
{
    for (int i = -1; i < 2; i++)
    {
        for (int j = -1; j < 2; j++)
        {
            if (abs(i + j) == 1)
            {
                int currX = currentPoint->x + i;
                int currY = currentPoint->y + j;
                if (currX < map->rowsAmount && currX >= 0 && currY < map->columnsAmount && currY >= 0)
                {
                    Point *currentNeighbour = createPoint(currX, currY);
                    if (!exists(closed, currentNeighbour) && !map->matrix[currentNeighbour->x][currentNeighbour->y])
                    {
                        addElement(neighbours, currentNeighbour);
                    }
                    else
                    {
                        deletePoint(currentNeighbour);
                    }
                }
            }
        }
    }
}

void processNeighbours(PointList *neighbours, PointList *opened, Point *currentPoint, Point *end, Graph *ways, Map *map)
{
    PointListElement *currentListElement = neighbours->first;

    int tempG = currentPoint->g + 1;
    while (currentListElement)
    {
        if (!exists(opened, currentListElement->point) || tempG < currentListElement->point->g)
        {
            addRelation(ways, pointHash(map, currentListElement->point), pointHash(map, currentPoint));
            currentListElement->point->g = tempG;
            updateF(currentListElement->point, end);
        }

        if (!exists(opened, currentListElement->point))
        {
            addElement(opened, currentListElement->point);
        }

        currentListElement = currentListElement->next;
    }

    clearList(neighbours);
}

PointList *buildGraph(Map *map, Point *start, Point *end)
{
    if (map->matrix[end->x][end->y] || map->matrix[start->x][start->y])
    {
        return nullptr;
    }

    PointList *opened = createPointList();
    PointList *closed = createPointList();
    Graph *ways = createGraph(map->columnsAmount * map->rowsAmount);

    addElement(opened, start);

    start->g = 0;
    updateF(start, end);

    while (!isEmpty(opened))
    {
        Point *currentPoint = minF(opened);
        if (currentPoint->x == end->x && currentPoint->y == end->y)
        {
            break;
        }

        removeElement(opened, currentPoint);
        addElement(closed, currentPoint);

        PointList *neighbours = createPointList();

        fillPointListWithNeighbours(neighbours, closed, currentPoint, map);
        processNeighbours(neighbours, opened, currentPoint, end, ways, map);
    }

    List *way = buildWay(ways, pointHash(map, start), pointHash(map, end));
    if (!way)
    {
        return nullptr;
    }

    PointList *result = buildWay(way, map);

    removeElement(closed, start);
    deleteList(way);
    deleteList(opened);
    deleteList(closed);
    deleteGraph(ways);

    return result;
}

void printWay(Map *map, PointList *way)
{
    if (!way)
    {
        cout << "There is no way" << endl;
        return;
    }

    for (int i = 0; i < map->rowsAmount; i++)
    {
        for (int j = 0; j < map->columnsAmount; j++)
        {
            Point *currentPoint = createPoint(i, j);
            if (exists(way, currentPoint))
            {
                cout << ". ";
            }
            else
            {
                cout << (map->matrix[i][j] ? '#' : ' ') << ' ';
            }
            deletePoint(currentPoint);
        }
        cout << endl;
    }
}
