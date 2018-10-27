#include <iostream>
#include <fstream>
using namespace std;

void addNote(char const fileBufferName[]);
void find(string currentLine, string inputLine, bool isFindNumber);
void findNumber(char const fileName[]);
void findName(char const fileName[]);
void save(char const fileName[], char const fileBufferName[]);

int main()
{
    char const fileName[] = {"file.txt"};
    char const fileBufferName[] = {"buffer.txt"};

    int input = 0;
    do
    {
        cin >> input;

        switch (input)
        {
            case 1:
            {
                addNote(fileBufferName);
                break;
            }
            case 2:
            {
                findNumber(fileName);
                break;
            }
            case 3:
            {
                findName(fileName);
                break;
            }
            case 4:
            {
                save(fileName, fileBufferName);
                break;
            }
        }
    }
    while (input);

    remove(fileBufferName);

    return 0;
}

void addNote(char const fileBufferName[])
{
    cout << "Enter note through hyphen in this way: <name>-<number>" << endl;

    string inputLine = " ";
    cin >> inputLine;

    ofstream foutBuf(fileBufferName, ios::app);

    for (int i = 0; inputLine[i] != '\0'; i++)
    {
        foutBuf << inputLine[i];
    }
    foutBuf << "\n";

    foutBuf.close();
}

void find(string currentLine, string inputLine, bool isFindNumber)
{
    size_t hyphenIndex = 0;
    for (size_t i = 0; i < currentLine.length(); i++)
    {
        if (currentLine[i] == '-')
        {
            hyphenIndex = i;
            break;
        }
    }

    size_t beginComparison = hyphenIndex + 1;
    size_t endComparison = currentLine.length() - 1;
    size_t beginOutput = 0;
    size_t endOutput = hyphenIndex - 1;
    if (isFindNumber)
    {
        swap(beginComparison, beginOutput);
        swap(endComparison, endOutput);
    }

    size_t comparisonLineLength = hyphenIndex;
    if (!isFindNumber)
    {
        comparisonLineLength = 0;
        for (size_t i = hyphenIndex + 1; i < currentLine.length(); i++)
        {
            comparisonLineLength++;
        }
    }
    bool isEqual = true;
    if (inputLine.length() != comparisonLineLength)
    {
        isEqual = false;
    }
    else
    {
        for (size_t i = beginComparison; i <= endComparison; i++)
        {
            if (currentLine[i] != inputLine[i - beginComparison])
            {
                isEqual = false;
                break;
            }
        }
    }

    if (isEqual)
    {
        for (size_t i = beginOutput; i <= endOutput; i++)
        {
            cout << currentLine[i];
        }
        cout << endl;
    }
}

void findNumber(char const fileName[])
{
    cout << "Enter the name" << endl;

    string inputLine = " ";
    cin >> inputLine;

    ifstream fin(fileName);

    string currentLine = "";
    while (getline(fin, currentLine))
    {
        find(currentLine, inputLine, true);
    }

    fin.close();
}

void findName(char const fileName[])
{
    cout << "Enter the number" << endl;

    string inputLine = " ";
    cin >> inputLine;

    ifstream fin(fileName);

    string currentLine = "";
    while (getline(fin, currentLine))
    {
        find(currentLine, inputLine, false);
    }

    fin.close();
}

void save(char const fileName[], char const fileBufferName[])
{
    ifstream finBuf(fileBufferName);
    if (!finBuf.is_open())
    {
        finBuf.close();
        return;
    }

    ofstream fout(fileName, ios::app);

    string currentLine = "";
    while (getline(finBuf, currentLine))
    {
        fout << currentLine << endl;
    }

    finBuf.close();
    fout.close();
}
