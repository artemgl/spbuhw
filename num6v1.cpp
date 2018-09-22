#include <iostream>
using namespace std;

int main()
{
    cout << "Enter s1\n";
    string s1;
    getline(cin, s1);

    cout << "Enter s2\n";
    string s2;
    getline(cin, s2);

    int count = 0;
    bool isEqual = true;
    for (size_t i = 0; i <= s1.length() - s2.length(); i++)
    {
        if (s1[i] == s2[0])
        {
            for (size_t j = 0; j < s2.length(); j++)
            {
                if (s1[i + j] != s2[j])
                {
                    isEqual = false;
                }
            }

            if (isEqual)
            {
                count++;
            }
            else
            {
                isEqual = true;
            }
        }
    }

    cout << "Number of occurrences s2 in s1 is: " << count;

    return 0;
}
