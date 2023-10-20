import re
import smtplib
from email.mime.text import MIMEText
from tkinter import *

smtp_server = "smtp.qq.com"
smtp_port = 465
sender_email = "1027743497@qq.com"
sender_password = "jwpmacdfhnwybdch"

window = Tk()
window.geometry("300x200")
receiver_email = StringVar()
subject = StringVar()
body = StringVar()
error_msg = StringVar()

def send_email():
    pattern = r"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
    if not re.match(pattern, receiver_email.get()):
        error_msg.set("请输入有效的电子邮件地址！")
        return

    message = MIMEText(body.get(), "plain", "utf-8")
    message["From"] = "suzukaze <" + sender_email + ">"
    message["To"] = receiver_email.get()
    message["Subject"] = subject.get()

    try:
        with smtplib.SMTP_SSL(smtp_server, smtp_port) as server:
            server.login(sender_email, sender_password)
            server.sendmail(sender_email, receiver_email.get(), message.as_string())
                
            error_msg.set("")
    except Exception as e:
        error_msg.set("电子邮件发送失败：" + str(e))

Label(window, text="电子邮箱地址").pack()
Entry(window, textvariable=receiver_email).pack()
Label(window, text="邮件标题").pack()
Entry(window, textvariable=subject).pack()
Label(window, text="邮件正文").pack()
Entry(window, textvariable=body).pack()
Label(window, textvariable=error_msg, fg="red").pack()
Button(window, text="发送邮件", command=send_email).pack()

window.mainloop()