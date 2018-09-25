#include <iostream>
using namespace std;

int main()
{
    cout << "Enter the length of your string\n";
    int length = 0;
    cin >> length;

    cout << "Enter the string\n";
    char str[length] = {};

    for (int i = 0; i < length; i++)
    {
        cin >> str[i];
    }

    bool isPalindrome = true;
    for (int i = 0; i < (length - 1) / 2; i++)
    {
        if (str[i] != str[length - 1 - i])
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
