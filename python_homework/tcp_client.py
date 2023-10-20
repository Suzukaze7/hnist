import socket

tcp_client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcp_client.connect(("127.0.0.1", 11451))

print("tcp client is ready to send")

while True:
    info = input("input info: ")

    if info == "exit":
        tcp_client.close()
        break

    tcp_client.send(info.encode())
    info = tcp_client.recv(1024).decode()
    print(info)
