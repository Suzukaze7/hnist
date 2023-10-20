#include<iostream>
#include<sstream>
#include<set>
#include<map>
#include<queue>
#include<vector>
#include<algorithm>

using set_type = std::set<char>; //状态集合
template<typename T, typename U>
using trans_set_type = std::map<T, std::map<char, U>>; //转换函数集合

template<typename T, typename U>
struct Automata //自动机模板
{
    set_type status_set, char_set;
    trans_set_type<T, U> trans_set;
    char start_status;
    set_type end_status_set;
};

using NFA = Automata<char, std::vector<char>>;
using DFA = Automata<char, char>;
NFA nfa;
DFA dfa, simp_dfa;

void print_dfa(DFA &dfa)
{
    std::cout << "状态集:\n";
    for (auto c : dfa.status_set)
        std::cout << c << " ";
    std::cout << '\n';

    std::cout << "字符表:\n";
    for (auto c : dfa.char_set)
        std::cout << c << " ";
    std::cout << "\n";

    std::cout << "转换函数:\n";
    std::cout << "\t";
    for (auto c : dfa.char_set)
        std::cout << c << "\t";
    std::cout << "\n";
    for (auto last_status : dfa.status_set)
    {
        std::cout << last_status << "\t";
        for (auto trans_char : dfa.char_set)
            std::cout << dfa.trans_set[last_status][trans_char] << "\t";
        std::cout << "\n";
    }

    std::cout << "起始状态:\n";
    std::cout << dfa.start_status << "\n";

    std::cout << "终止状态:\n";
    for (auto end_status : dfa.end_status_set)
        std::cout << end_status << " ";
    std::cout << "\n";
}

void input()
{
    std::cout << "请输入状态集:\n";
    std::string s;
    std::getline(std::cin, s);
    std::istringstream sin(s);
    char c;
    while (sin >> c)
        nfa.status_set.insert(c);

    std::cout << "请输入输入符号表:\n";
    std::getline(std::cin, s);
    sin.clear(), sin.str(s);
    while (sin >> c)
        nfa.char_set.insert(c);

    std::cout << "请输入起始状态:\n";
    std::cin >> nfa.start_status;

    std::cout << "请输入终止状态集:\n";
    std::getline(std::cin, s);
    std::getline(std::cin, s);
    sin.clear(), sin.str(s);
    while (sin >> c)
        nfa.end_status_set.insert(c);

    std::cout << "请输入转换函数:\n";
    std::cout << "每行第一个字符表示当前状态,第二个字符表示输入字符(.代表空字符),之后所有字符表示后继状态\n";
    std::cout << "输入完成后按ctrl+z表示输入结束\n";
    while (std::getline(std::cin, s))
    {
        sin.clear(), sin.str(s);
        char st, cr, ne;
        sin >> st >> cr;
        while (sin >> ne)
            nfa.trans_set[st][cr].push_back(ne);
    }
}

// 将子集对应的空转换加入子集
void add_by_blank(set_type &set)
{
    std::vector<char> new_add(set.begin(), set.end());

    while (new_add.size())
    {
        auto t = new_add.back();
        new_add.pop_back();

        for (auto c : nfa.trans_set[t]['.'])
            if (!set.count(c))
                new_add.push_back(c), set.insert(c);
    }
}

void nfa_to_dfa()
{
    std::map<set_type, char> book; //哪些子集已经出现过
    std::queue<set_type> q; //需要处理子集的队列
    trans_set_type<set_type, set_type> trans_set;

    set_type start_set = { nfa.start_status };
    add_by_blank(start_set);
    q.push(start_set), book.emplace(start_set, 0);

    while (q.size())
    {
        auto last_set = q.front();
        q.pop();

        for (auto trans_word : nfa.char_set)
        {
            set_type next_set;
            for (auto cur : last_set)
                for (auto ne : nfa.trans_set[cur][trans_word])
                    next_set.insert(ne);

            add_by_blank(next_set);

            if (next_set.size() && !book.count(next_set))
                q.push(next_set), book.emplace(next_set, 0);

            trans_set[last_set][trans_word] = next_set;
        }
    }

    // 子集重命名
    book[start_set] = 'S';
    char idx = 'A';
    for (auto &[k, v] : book)
        if (!v)
        {
            v = idx++;
            if (idx == 'S')
                idx++;
        }

    dfa.char_set = nfa.char_set;
    dfa.start_status = book[start_set];
    for (auto &[k, v] : book) // 更新dfa结束状态
    {
        dfa.status_set.insert(v);

        bool flag = false;
        for (auto c : k)
            flag |= nfa.end_status_set.count(c);
        if (flag)
            dfa.end_status_set.insert(v);
    }

    // 更新转换函数
    for (auto &[st, v] : trans_set)
        for (auto &[trans_word, ed] : v)
            dfa.trans_set[book[st]][trans_word] = book[ed];
}

void simplify_dfa()
{
    std::vector<set_type> last_sets, next_sets;

    // 按一致性条件一分为二
    set_type &t1 = dfa.status_set, &t2 = dfa.end_status_set, t3;
    std::set_difference(t1.begin(), t1.end(), t2.begin(), t2.end(), std::inserter(t3, t3.begin()));
    last_sets.push_back(dfa.end_status_set);
    last_sets.push_back(t3);

    // 按蔓延性分割
    while (true)
    {
        // 每个状态对应的集合
        std::map<char, int> to;
        for (int i = 0; i < last_sets.size(); i++)
            for (auto c : last_sets[i])
                to[c] = i;

        bool is_divided = false;
        for (auto trans_char : dfa.char_set) //对每个转换符号尝试分割
        {
            next_sets.clear();
            for (auto &set : last_sets) //对每个集合分割
            {
                std::map<int, set_type> partition; //不同符号放到不同集合
                for (auto last_status : set)
                    partition[to[dfa.trans_set[last_status][trans_char]]].insert(last_status);

                for (auto &[k, part] : partition)
                    next_sets.push_back(part);
            }

            if (next_sets != last_sets) //如果成功分割
            {
                is_divided = true;
                break;
            }
        }

        if (!is_divided) //所有情况都不能分割则结束
            break;

        last_sets = next_sets;
    }

    // 接下来是将集合转换成简化后的DFA
    std::map<char, char> to;
    char idx = 'A';
    for (auto &set : next_sets)
    {
        bool is_start = set.count(dfa.start_status), is_end = false;
        for (auto c : set)
        {
            if (is_start)
                to[c] = 'S';
            else
                to[c] = idx;

            is_end |= dfa.end_status_set.count(c);
        }

        if (!is_start)
        {
            simp_dfa.status_set.insert(idx);
            if (is_end)
                simp_dfa.end_status_set.insert(idx);
            if (++idx == 'S')
                ++idx;
        }
        else
        {
            simp_dfa.status_set.insert('S');
            simp_dfa.start_status = 'S';
            if (is_end)
                simp_dfa.end_status_set.insert('S');
        }
    }
    simp_dfa.char_set = dfa.char_set;

    for (auto &[last_status, v] : dfa.trans_set)
        for (auto &[trans_char, next_status] : v)
            simp_dfa.trans_set[to[last_status]][trans_char] = to[next_status];
}

int main()
{
    input();
    nfa_to_dfa();
    simplify_dfa();

    std::cout << "-----------------\n";
    std::cout << "NFA转化后的DFA:\n";
    print_dfa(dfa);
    std::cout << "-----------------\n";
    std::cout << "简化后的DFA:\n";
    print_dfa(simp_dfa);
}
