import sys
import importlib

def install_module(module_name):
    try:
        importlib.import_module(module_name)
        print(f"{module_name} 模块已安装")
    except ImportError:
        print(f"正在安装 {module_name} 模块...")
        try:
            import pip
            pip.main(['install', module_name])
        except AttributeError:
            import subprocess
            subprocess.check_call([sys.executable, '-m', 'pip', 'install', module_name])
        print(f"{module_name} 模块安装成功")

def download_webpage(url, filename):
    try:
        import requests
        response = requests.get(url)
        response.raise_for_status()  # 检查请求是否成功

        with open(filename, 'w', encoding='utf-8') as file:
            file.write(response.text)
        print("网页源代码已成功下载到文件: " + filename)
    except requests.exceptions.RequestException as e:
        print("下载网页源代码时出现错误: " + str(e))

if __name__ == '__main__':
    if len(sys.argv) != 3:
        print("命令格式错误，请输入：python httpget.py 网址 文件名")
    else:
        install_module('requests')
        url = sys.argv[1]
        filename = sys.argv[2]
        download_webpage(url, filename)
