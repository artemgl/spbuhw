#include <iostream>
#include "astar.h"
#include "pointlist.h"
using namespace std;

int main()
{
    const char fileName[] = "file.txt";

    Map *map = readMapFromFile(fileName);
    PointList *emptyWay = createPointList();
    printWay(map, emptyWay);
    deleteList(emptyWay);

    cout << "Enter coordinates of start point: ";
    int startX = 0;
    int startY = 0;
    cin >> startX >> startY;
    Point *start = createPoint(startX, startY);

    cout << "Enter coordinates of end point: ";
    int endX = 0;
    int endY = 0;
    cin >> endX >> endY;
    Point *end = createPoint(endX, endY);

    PointList *way = buildGraph(map, start, end);
    printWay(map, way);

    deletePoint(start);
    deletePoint(end);
    deleteList(way);
    deleteMap(map);

    return 0;
}
