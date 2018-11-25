#include <iostream>
#include "hashtable.h"
using namespace std;

int main()
{
    const int numberOfVariants = 3;

    HashTable *table = createHashTable();

    cout << "Enter amount of students" << endl;
    int numberOfStudents = 0;
    cin >> numberOfStudents;

    for (int i = 0; i < numberOfStudents; i++)
    {
        cout << "1st number: ";
        int firstNumber = 0;
        cin >> firstNumber;

        cout << "2nd number: ";
        int secondNumber = 0;
        cin >> secondNumber;

        cout << endl;

        addElement(table, firstNumber, secondNumber);
    }

    for (int i = 0; i < numberOfStudents; i++)
    {
        int studentNumber = i + 1;
        int variantNumber = returnSecondNumber(table, studentNumber);

        while (variantNumber > numberOfVariants)
        {
            variantNumber = returnSecondNumber(table, variantNumber);
        }

        cout << studentNumber << ' ' << variantNumber << endl;
    }

    deleteHashTable(table);

    return 0;
}
