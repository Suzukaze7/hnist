import socket

udp_server = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
udp_server.bind(("127.0.0.1", 11451))

print("server start listen")

while True:
    info, addr = udp_server.recvfrom(1024)

    info = info.decode()
    print("receive string: " + info)
    udp_server.sendto(info.upper().encode(), addr)
