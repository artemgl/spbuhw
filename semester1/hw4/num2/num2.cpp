#include <stdio.h>
#include <iostream>
using namespace std;

void addNote(FILE *fBuf);
void findNumber(FILE *f);
void findName(FILE *f);
void save(FILE *fBuf, FILE *f);

int length(string str);

int main()
{
    FILE *f = nullptr;
    FILE *fBuf = nullptr;

    int input = 0;
    do
    {
        cin >> input;

        switch (input)
        {
            case 1:
            {
                addNote(fBuf);
                break;
            }
            case 2:
            {
                findNumber(f);
                break;
            }
            case 3:
            {
                findName(f);
                break;
            }
            case 4:
            {
                save(fBuf, f);
                break;
            }
        }
    }
    while (input);

    if ((f = fopen("buffer.txt", "r")))
    {
        fclose(f);
        remove("buffer.txt");
    }

    return 0;
}

int length(string str)
{
    char currentSymbol = '\0';
    int result = 0;
    do
    {
        currentSymbol = str[result++];
    }
    while (currentSymbol != '\0');

    return result - 1;
}

void addNote(FILE *fBuf)
{
    cout << "Enter note through hyphen in this way: <name>-<number>" << endl;

    string str = " ";
    cin >> str;

    fBuf = fopen("buffer.txt", "a");

    for (int i = 0; i < length(str); i++)
    {
        fprintf(fBuf, "%c", str[i]);
    }
    fprintf(fBuf, "%c", ';');

    fclose(fBuf);
}

void findNumber(FILE *f)
{
    cout << "Enter the name" << endl;

    string str = " ";
    cin >> str;

    f = fopen("file.txt", "r");

    char currentSymbol = fgetc(f);

    while ((currentSymbol = fgetc(f)) != EOF)
    {
        while (currentSymbol != str[0] && currentSymbol != EOF)
        {
            do
            {
                currentSymbol = fgetc(f);
            }
            while (currentSymbol != ';');

            currentSymbol = fgetc(f);
        }

        if (currentSymbol == EOF)
        {
            break;
        }

        bool isEqual = true;
        for (int i = 1; i < length(str); i++)
        {
            currentSymbol = fgetc(f);
            if (str[i] != currentSymbol)
            {
                isEqual = false;
                break;
            }
        }

        if (fgetc(f) != '-')
        {
            isEqual = false;
        }

        if (isEqual)
        {
            currentSymbol = fgetc(f);
            do
            {
                cout << currentSymbol;
                currentSymbol = fgetc(f);
            }
            while (currentSymbol != ';');
            cout << endl;
        }
        else
        {
            while (currentSymbol != ';')
            {
                currentSymbol = fgetc(f);
            }
        }
    }

    fclose(f);
}

void findName(FILE *f)
{
    cout << "Enter the number" << endl;

    string str = " ";
    cin >> str;

    f = fopen("file.txt", "r");

    char currentSymbol = '\0';

    while (currentSymbol != '-')
    {
        currentSymbol = fgetc(f);
    }

    while ((currentSymbol = fgetc(f)) != EOF)
    {
        while (currentSymbol != str[0] && currentSymbol != EOF)
        {
            do
            {
                currentSymbol = fgetc(f);
            }
            while (currentSymbol != '-' && currentSymbol != EOF);

            currentSymbol = fgetc(f);
        }

        if (currentSymbol == EOF)
        {
            break;
        }

        bool isEqual = true;
        for (int i = 1; i < length(str); i++)
        {
            currentSymbol = fgetc(f);
            if (str[i] != currentSymbol)
            {
                isEqual = false;
                break;
            }
        }

        currentSymbol = fgetc(f);

        if (currentSymbol == EOF)
        {
            break;
        }

        if (currentSymbol != ';')
        {
            isEqual = false;
        }

        if (isEqual)
        {
            do
            {
                fseek(f, -2, SEEK_CUR);
            }
            while (fgetc(f) != ';');

            currentSymbol = fgetc(f);
            do
            {
                cout << currentSymbol;
                currentSymbol = fgetc(f);
            }
            while (currentSymbol != '-');
            cout << endl;

            do
            {
                currentSymbol = fgetc(f);
            }
            while (currentSymbol != '-' && currentSymbol != EOF);

            if (currentSymbol == EOF)
            {
                break;
            }
        }
        else
        {
            while (currentSymbol != '-' && currentSymbol != EOF)
            {
                currentSymbol = fgetc(f);
            }

            if (currentSymbol == EOF)
            {
                break;
            }
        }
    }

    fclose(f);
}

void save(FILE *fBuf, FILE *f)
{
    if (!(fBuf = fopen("buffer.txt", "r")))
    {
        fclose(fBuf);
        return;
    }
    f = fopen("file.txt", "r");
    if (fgetc(f) == EOF)
    {
        fclose(f);
        f = fopen("file.txt", "w");
        fprintf(f, "%c", ';');
        fclose(f);
    }

    f = fopen("file.txt", "a");

    char currentSymbol = '\0';
    while (!feof(fBuf))
    {
        currentSymbol = fgetc(fBuf);
        if (currentSymbol != EOF)
        {
            fputc(currentSymbol, f);
        }
    }

    fclose(fBuf);
    fclose(f);

    remove("buffer.txt");
}
