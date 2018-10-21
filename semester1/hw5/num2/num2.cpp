#include <iostream>
using namespace std;

int main()
{
    FILE *f = fopen("file.txt", "r");

    char currentSymbol = '\0';

    if (isalpha(currentSymbol = fgetc(f)))
    {
        cout << currentSymbol;
    }

    char previousSymbol = currentSymbol;

    while ((currentSymbol = fgetc(f)) != EOF)
    {
        if (isalpha(currentSymbol))
        {
            if (isalpha(previousSymbol))
            {
                if (currentSymbol != tolower(previousSymbol) && currentSymbol != toupper(previousSymbol))
                {
                    cout << currentSymbol;
                }
            }
            else
            {
                cout << " " << currentSymbol;
            }
        }

        previousSymbol = currentSymbol;
    }

    fclose(f);

    return 0;
}
