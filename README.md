# UDP Client-Server Interaction

This project demonstrates a basic UDP client-server interaction in Java.

## UDPclient Class

- Sends a registration message (username, IP, port) to a server at the broadcast address 255.255.255.255 and port 9876.
- Waits for a server response, prints server details, and processes the received message.

## UDPserver Class

- Listens on port 9876 for client messages.
- Processes "hello" messages, updates a client list, and responds with the list.
- Prints details of received messages and maintains a client list.
