import random
import string

last_name = "王张李刘陈杨黄周胡赵林邱蔡冯"
first_name = "国瑞美玉怡乐俊民海来国伟志文家伟美玲怡婷彦霖佳勋冠杰佳雨一忠威强"
email = ["@qq.com", "@163.com", "@126.com", "@139.com", "@sina.com.cn", "@vip.sina.com",
         "@sohu.com", "@mail.yahoo.com.cn", "@gmail.com", "@live.com", "@tom.com"]

n = 20
with open("phone.txt", "w", encoding="utf-8") as fout:
    for i in range(n):
        res = random.choice(last_name) + \
            random.choice(first_name) + random.choice(first_name)
        res += " 1" + str(random.randint(0, 9999999999)).zfill(10) + " "
        res += "".join(random.choice(string.ascii_letters)
                       for _ in range(random.randint(7, 14))) + random.choice(email)

        fout.write(res + "\n")
