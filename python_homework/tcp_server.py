import socket

tcp_server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcp_server.bind(("127.0.0.1", 11451))
tcp_server.listen(1)

print("tcp server is ready to receive")

while True:
    connect_sock, addr = tcp_server.accept()

    while True:
        info = connect_sock.recv(1024)

        if info == b"":
            connect_sock.close()
            print("connect close")
            break

        info = info.decode()
        print("receive info: " + info)
        connect_sock.send(info.upper().encode())
