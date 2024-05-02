import socket

HOST = '127.0.0.1'
PORT = 7000

# socket.socket takes two inputs -> (address_family, socket_type)
#address_family -> AF_INET (IPv4), AF_INET6 (IPv6)
#socket_type -> protocol; SOCK_STREAM -> TCP, 
                         #SOCK_DGRAM -> UDP

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    #binds the HOST and PORT to the socket
    s.bind((HOST, PORT))

    #listening
    s.listen()

    print(f"\nserver listening at {HOST}:{PORT}")

    #accept connections (BLOCKING function)
    conn, addr = s.accept()

    #conn object above is socket used to communicate with the client
    #it is different from socket 's' used for listening to incoming connections

    with conn:
        print(f'\nconnected by {addr}')

        #keep listening to calls until this connection 'conn' is alive
        while True:
            #read atmost 1024 bytes from receiver
            recv_data = conn.recv(1024)
            decoded_data = recv_data.decode("utf-8")

            #if empty object is received, the client is closed
            if not decoded_data:
                print('\nclient closed connection')
                break
            
            #print received message
            print(decoded_data)
            
            #send message
            send_data = input("\nsend message: ")
            encoded_data = bytes(send_data, "utf-8")
            conn.sendall(encoded_data)

