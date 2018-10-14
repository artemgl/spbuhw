#include <iostream>
using namespace std;

int main()
{
    char symbol = ' ';
    bool isEmpty = true;
    int amount = 0;
    FILE *f = fopen("file.txt", "r");
    while (!feof(f))
    {
        fscanf(f, "%c", &symbol);
        if (feof(f))
        {
            break;
        }

        switch (symbol)
        {
        case '\n':
        {
            if (!isEmpty)
            {
                amount++;
                isEmpty = true;
            }
            break;
        }
        case ' ':
        {
            break;
        }
        case '\t':
        {
            break;
        }
        default:
        {
            isEmpty *= 0;
            break;
        }
        }
    }

    fclose(f);

    if (symbol != '\n' && isEmpty == false)
    {
        amount++;
    }

    cout << "Amount of not empty strings is " << amount;

    return 0;
}
