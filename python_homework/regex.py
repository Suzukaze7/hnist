import re

phone = r"(?:^|[^\d])(1\d{10})(?:$|[^\d])"
email = r"[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(?:\.[a-zA-Z0-9_-]+)"

with open("D:/phone.txt", 'r', encoding="utf-8") as fin:
    info = fin.read()

    print("手机号：")
    print("\n".join(re.findall(phone, info)))
    print("\n邮箱：")
    print("\n".join(re.findall(email, info)))
