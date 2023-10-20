import os
import sys


def print_dir_tree(rootdir, indent=''):
    """
    输出指定目录的目录树

    参数:
        rootdir (str): 指定目录的路径
        indent (str): 目录树的缩进字符串

    返回:
        无
    """
    if os.path.isfile(rootdir):
        print(indent + '├── ' + os.path.basename(rootdir))
    else:
        print(indent + '└── ' + os.path.basename(rootdir) + '/')

        for i, item in enumerate(sorted(os.listdir(rootdir))):
            subpath = os.path.join(rootdir, item)
            if i == len(os.listdir(rootdir))-1:
                print_dir_tree(subpath, indent + '    ')
            else:
                print_dir_tree(subpath, indent + '│   ')


if __name__ == '__main__':
    if len(sys.argv) == 2:
        if sys.argv[1] == '-h':
            print()
            print("输出指定目录的目录树")
            print("用法: python print_dir_tree.py <目录>")
            print()
            print("————计科21-2BJ 金烜宇")
            print()
            sys.exit()
        else:
            rootdir = sys.argv[1]
            print_dir_tree(rootdir)
    else:
        print()
        print("-h\t: 获取使用方法")
        print()
