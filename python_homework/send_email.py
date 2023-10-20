import re
import smtplib
from email.mime.text import MIMEText

def send_email():
    # 获取用户输入的电子邮件地址
    receiver_email = input("请输入电子邮件地址：")
    
    # 使用正则表达式验证电子邮件地址格式
    pattern = r'^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$'
    if not re.match(pattern, receiver_email):
        print("请输入有效的电子邮件地址！")
        return
    
    # 获取邮件标题和正文
    subject = input("请输入邮件标题：")
    body = input("请输入邮件正文：")
    
    smtp_server = 'smtp.qq.com'
    smtp_port = 465
    sender_email = '1027743497@qq.com'
    sender_password = 'jwpmacdfhnwybdch'

    # 构造邮件内容
    message = MIMEText(body, 'plain', 'utf-8')
    message['From'] = 'suzukaze <' + sender_email + '>'
    message['To'] = receiver_email
    message['Subject'] = subject

    # 发送电子邮件
    try:
        with smtplib.SMTP_SSL(smtp_server, smtp_port) as server:
            server.login(sender_email, sender_password)
            server.sendmail(sender_email, receiver_email, message.as_string())
                
            print("电子邮件发送成功！")
    except Exception as e:
        print("电子邮件发送失败：", str(e))

# 调用函数发送电子邮件
send_email()