#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main()
{
    string str = "";
    int amount = 0;
    ifstream f("file.txt");
    while (!f.eof())
    {
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
