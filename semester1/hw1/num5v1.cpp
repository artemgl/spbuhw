#include <iostream>
using namespace std;

int main()
{
    cout << "Enter string\n";
    string line;
    getline(cin, line);

    int count = 0;
    for (size_t i = 0; i <= line.length(); i++)
    {
        if (line[i] == '(')
        {
            count++;
        }

        if (line[i] == ')')
        {
            count--;
        }

        if (count < 0)
        {
            break;
        }
    }

    if (count == 0)
    {
        cout << "Balance of the parentheses isn't broken";
    }
    else
    {
        cout << "Balance of the parentheses is broken";
    }

    return 0;
}
