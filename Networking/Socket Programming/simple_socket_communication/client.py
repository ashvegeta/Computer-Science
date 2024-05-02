import socket

HOST = "127.0.0.1"  # The server's hostname or IP address
PORT = 7000  # The port used by the server

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    #connect to server
    s.connect((HOST, PORT))
    
    print(f"\nconnected to {HOST}:{PORT} successfully")
    while True:
        #send data to server
        send_data = input("\nEnter message:")
        encoded_data = bytes(send_data, "utf-8")
        s.sendall(encoded_data)
        
        #receive data from server
        recv_data = s.recv(1024)
        decoded_data = recv_data.decode("utf-8")
        print(f"\nServer: {decoded_data}")

