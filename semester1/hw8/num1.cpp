#include <iostream>
using namespace std;

int main()
{
    const int amountOfVariants = 3;

    cout << "Enter amount of students: ";
    int amountOfStudents = 0;
    cin >> amountOfStudents;

    int *numbersOfCopiedWork = new int[amountOfStudents] {};

    for (int i = 0; i < amountOfStudents; i++)
    {
        cout << "Number of student: ";
        int firstNumber = 0;
        cin >> firstNumber;

        cout << "Number of student, who let him copy his work: ";
        int secondNumber = 0;
        cin >> secondNumber;

        cout << endl;

        numbersOfCopiedWork[firstNumber - 1] = secondNumber;
    }

    cout << "Student | Variant" << endl;

    for (int i = 0; i < amountOfStudents; i++)
    {
        int variant = i + 1;

        do
        {
            variant = numbersOfCopiedWork[variant - 1];
        }
        while (variant > amountOfVariants);

        cout << i + 1 << ' ' << variant << endl;
    }

    return 0;
}
