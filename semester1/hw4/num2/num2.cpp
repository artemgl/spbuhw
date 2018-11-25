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

    List *list = createList();
    copyFromFile(list, fileName);

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

                addNote(list, name, number);

                cout << "The note added successfully" << endl;
                break;
            }
            case 2:
            {
                cout << "Enter the name to find a number" << endl;
                char name[maxLength] = "";
                cin >> name;

                printNumber(list, name);
                break;
            }
            case 3:
            {
                cout << "Enter the number to find a name" << endl;
                char number[maxLength] = "";
                cin >> number;

                printName(list, number);
                break;
            }
            case 4:
            {
                saveInFile(list, fileName);

                cout << "Data saved successfully" << endl;
                break;
            }
        }
    }
    while (input);

    deleteList(list);

    return 0;
}
