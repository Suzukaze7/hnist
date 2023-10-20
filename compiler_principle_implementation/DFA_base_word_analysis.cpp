#include<iostream>
#include<sstream>
#include<vector>
#include<map>
#include<cstring>

class CharToIdx
{
    int to[INT8_MAX], idx;
    char from[INT8_MAX];

public:
    CharToIdx() : idx(0) { memset(to, -1, sizeof to); }

    void add(char c) { from[idx] = c, to[c] = idx++; }

    int size() { return idx; }

    int get_idx(char c) { return to[c]; }

    char get_char(int i) { return from[i]; }
};

class DFA
{
    int start_status;
    CharToIdx status_set, char_set;
    std::vector<std::vector<int>> trans_matrix;

public:
    void input()
    {
        char c;
        std::string s;
        std::istringstream sin;

        std::cout << "请输入状态集:\n";
        std::getline(std::cin, s);
        sin.clear(), sin.str(s);
        while (sin >> c)
            status_set.add(c);

        std::cout << "请输入输入符号表:\n";
        std::getline(std::cin, s);
        sin.clear(), sin.str(s);
        while (sin >> c)
            char_set.add(c);

        trans_matrix = std::vector<std::vector<int>>(status_set.size(), std::vector<int>(char_set.size() + 1, -1));
        for (auto &v : trans_matrix)
            v.back() = 0;

        std::cout << "请输入起始状态:";
        std::cin >> c;
        start_status = status_set.get_idx(c);

        std::cout << "请输入终止状态集:\n";
        std::getline(std::cin, s);
        std::getline(std::cin, s);
        sin.clear(), sin.str(s);
        while (sin >> c)
            trans_matrix[status_set.get_idx(c)].back() = 1;

        std::cout << "请输入转换函数个数:\n";
        int n;
        std::cin >> n;
        std::cout << "请输入转换函数:\n";
        std::cout << "每行三个字符表示当前状态,输入字符,后继状态\n";
        while (n--)
        {
            char st, wd, ne;
            std::cin >> st >> wd >> ne;
            trans_matrix[status_set.get_idx(st)][char_set.get_idx(wd)] = status_set.get_idx(ne);
        }
    }

    void word_analysis()
    {
        std::cout << "\n词法分析开始(输入ctrl+z终止):\n";
        std::string s;
        while (true)
        {
            std::cout << "\n请输入要分析的单词:\n";
            if (!std::getline(std::cin, s))
                break;

            int cur_status = start_status, is_vaild = 1;
            for (auto c : s)
            {
                int trans_char = char_set.get_idx(c);
                if (trans_char == -1)
                {
                    std::cout << "字符" << c << "不在字符表中\n";
                    is_vaild = 0;
                    break;
                }

                int next_status = trans_matrix[cur_status][char_set.get_idx(c)];
                if (next_status == -1)
                {
                    std::cout << "状态" << status_set.get_char(cur_status) << "不存在通过" << c << "的转换\n";
                    is_vaild = 0;
                    break;
                }

                cur_status = next_status;
            }

            if (is_vaild)
            {
                if (!trans_matrix[cur_status].back())
                    std::cout << "状态" << status_set.get_char(cur_status) << "不是终止状态\n";
                else
                    std::cout << "该单词合法\n";
            }
        }
    }
};

int main(int argc, char **argv)
{
    DFA dfa;
    dfa.input();
    dfa.word_analysis();
}