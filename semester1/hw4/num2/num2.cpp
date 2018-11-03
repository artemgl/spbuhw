#include <iostream>
#include <fstream>
#include "list.h"
#include "phonebook.h"
using namespace std;

void fillPhoneBook(List *list, char const fileName[]);

int main()
{
    char const fileName[] = {"file.txt"};

    cout << "Enter:" << endl;
    cout << "0 - exit" << endl;
    cout << "1 - add note" << endl;
    cout << "2 - find number" << endl;
    cout << "3 - find name" << endl;
    cout << "4 - save in the file" << endl;

    List *fileList = createList();
    fillPhoneBook(fileList, fileName);

    List *extraList = createList();

    int input = 0;
    do
    {
        cin >> input;

        switch (input)
        {
            case 1:
            {
                cout << "Enter the name to add" << endl;
                char name[maxLength] = "";
                cin >> name;

                cout << "Enter the number to add" << endl;
                char number[maxLength] = "";
                cin >> number;

                addNote(extraList, name, number);

                cout << "The note added successfully" << endl;
                break;
            }
            case 2:
            {
                cout << "Enter the name to find a number" << endl;
                char name[maxLength] = "";
                cin >> name;

                printNumber(fileList, name);
                break;
            }
            case 3:
            {
                cout << "Enter the number to find a name" << endl;
                char number[maxLength] = "";
                cin >> number;

                printName(fileList, number);
                break;
            }
            case 4:
            {
                saveInFile(extraList, fileName);
                mergeLists(fileList, extraList);

                cout << "Data saved successfully" << endl;
                break;
            }
        }
    }
    while (input);

    deleteList(fileList);
    deleteList(extraList);

    return 0;
}

void fillPhoneBook(List *list, char const fileName[])
{
    ifstream fin(fileName);

    char name[maxLength] = "";
    char number[maxLength] = "";

    char currentSymbol = '\0';
    fin.get(currentSymbol);
    while (!fin.eof())
    {
        for (int i = 0; i < maxLength; i++)
        {
            name[i] = '\0';
            number[i] = '\0';
        }

        currentSymbol = '\0';
        int i = 0;
        while (currentSymbol != '-')
        {
            fin.get(currentSymbol);
            name[i++] = currentSymbol;
        }
        name[i - 1] = '\0';

        currentSymbol = '\0';
        i = 0;
        while (currentSymbol != ';')
        {
            fin.get(currentSymbol);
            number[i++] = currentSymbol;
        }
        number[i - 1] = '\0';

        addNote(list, name, number);

        fin.get(currentSymbol);
    }

    fin.close();
}
