#include <iostream>
using namespace std;

int main()
{
    cout << "Enter line\n";
    string line;
    getline(cin, line);

    bool isPalindrome = true;
    for (size_t i = 0; i < (line.length() - 1) / 2; i++)
    {
        if (line[i] != line[line.length() - 1 - i])
        {
            isPalindrome = false;
        }
    }

    if (isPalindrome)
    {
        cout << "It's a palindrome";
    }
    else
    {
        cout << "It isn't a palindrome";
    }

    return 0;
}
