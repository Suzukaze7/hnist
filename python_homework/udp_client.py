import socket

udp_client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

info = input().encode()
udp_client.sendto(info, ('127.0.0.1', 11451))
info = udp_client.recv(1024)
print(info.decode())
