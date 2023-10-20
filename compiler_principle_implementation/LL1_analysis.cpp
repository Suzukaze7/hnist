#include<iostream>
#include<sstream>
#include<stack>
#include<cstring>
#include<map>
#include<vector>
#include<algorithm>

//为了将表弄成二维数组，于是设计一个类模板来建立字符与字符串到整数的映射
template<typename T>
class TypeToIdx
{
    std::map<T, int> to;
    std::vector<T> from;

public:
    void add(T t) { to[t] = from.size(), from.push_back(t); }

    int size() { return from.size(); }

    int get_idx(T c) { return to.count(c) ? to[c] : -1; }

    T get_type(int i) { return from[i]; }
};

class LL1
{
    using row_type = std::vector<int>;
    using table_type = std::vector<row_type>;

    TypeToIdx<char> fs_set, nfs_set; //终结符与非终结符集
    TypeToIdx<std::string> right_set; //右部集合
    table_type table; //预测分析表，动态长度的二维数组

public:
    void input()
    {
        char c;
        std::string s;
        std::istringstream sin;

        std::cout << "请输入非终结符集(第一个为起始符):\n";
        std::getline(std::cin, s);
        sin.clear(), sin.str(s);
        while (sin >> c)
            nfs_set.add(c);

        std::cout << "请输入终结符集:\n";
        std::getline(std::cin, s);
        sin.clear(), sin.str(s);
        while (sin >> c)
            fs_set.add(c);
        fs_set.add('#');

        table = table_type(nfs_set.size(), row_type(fs_set.size(), -1));

        std::cout << "请输入产生式与select集(起始一个字符串S->a，后跟空格隔开的select集，输入一行exit表示结束):\n";
        while (std::getline(std::cin, s), s != "exit")
        {
            sin.clear(), sin.str(s);
            sin >> s;
            right_set.add(s.substr(3));
            int nfs_idx = nfs_set.get_idx(s.front()), right_idx = right_set.get_idx(s.substr(3));
            while (sin >> c)
                table[nfs_idx][fs_set.get_idx(c)] = right_idx;
        }
    }

    void word_analysis()
    {
        std::cout << "\n词法分析开始(输入一行exit终止):\n";
        std::string s;
        while (std::cin >> s, s != "exit")
        {
            s += '#';
            std::stack<char> stk;
            stk.push('#'), stk.push(nfs_set.get_type(0)); //初始化栈
            int idx = 0, is_vaild = 1;

            std::cout << "步骤\t分析栈\t输入串\t动作\n";
            for (int step = 1; stk.size(); step++)
            {
                // 记录分析栈，输入串与动作
                std::string stk_str, input_str = s.substr(idx), action;
                for (auto t = stk; t.size(); t.pop())
                    stk_str += t.top();
                std::reverse(stk_str.begin(), stk_str.end());

                //获取栈顶
                char c = stk.top();
                stk.pop();
                int to_idx = nfs_set.get_idx(c);
                if (to_idx != -1) //如果是非终结符
                {
                    //获取待匹配字符对应的下标，如果不存在于终结符集则失败
                    int fs_idx = fs_set.get_idx(s[idx]);
                    if (fs_idx == -1)
                    {
                        is_vaild = 0;
                        std::cout << s[idx] << "不是终结符\n";
                        break;
                    }

                    //获得对应产生式右部，不存在则失败
                    int right_idx = table[to_idx][fs_idx];
                    if (right_idx == -1)
                    {
                        is_vaild = 0;
                        std::cout << c << "的产生式的select集不包含" << s[idx] << "\n";
                        break;
                    }

                    //将右部反向压入栈顶
                    std::string t = right_set.get_type(right_idx);
                    for (int i = t.size() - 1; ~i; i--)
                        stk.push(t[i]);

                    action += c;
                    action += "->";
                    action += t;
                }
                else //否则是终结符
                {
                    if (c == s[idx]) //如果匹配
                    {
                        idx++;
                        if (idx != s.size())
                            action = "匹配";
                        else
                            action = "Accept";
                    }
                    else //反之失败
                    {
                        is_vaild = 0;
                        std::cout << c << "与" << s[idx] << "匹配失败\n";
                        break;
                    }
                }

                // 每次操作的输出
                std::cout << step << "\t" << stk_str << "\t" << input_str << "\t" << action << "\n";
            }

            if (is_vaild && idx == s.size())
            {
                s.pop_back();
                std::cout << s << "是其句子\n";
            }
            std::cout << "------------------------\n";
        }
    }
};

int main()
{
    LL1 ll1;
    ll1.input();
    ll1.word_analysis();
}