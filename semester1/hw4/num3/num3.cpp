#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main()
{
    char const fileName[] = {"file.txt"};
    ifstream f(fileName);

    int amount = 0;
    while (!f.eof())
    {
        string str = "";
        getline(f, str);

        bool isEmpty = true;
        for (int i = 0; str[i] != '\0'; i++)
        {
            switch (str[i])
            {
            case ' ':
            case '\t':
            case '\r':
            {
                break;
            }
            default:
            {
                isEmpty = false;
                break;
            }
            }

            if (!isEmpty)
            {
                amount++;
                break;
            }
        }
    }

    f.close();

    cout << "Amount of not empty strings is " << amount;

    return 0;
}
