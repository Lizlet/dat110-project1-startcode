package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public Connection(Socket socket) {

		try {
//			System.out.println(socket.getPort());

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		// COMPLETED
		// encapsulate the data contained in the message and write to the output stream
		// Hint: use the encapsulate method on the message
		
		//=================================================================
		
		try {
			byte[] messageData = message.encapsulate();
			outStream.write(messageData);
		} catch (IOException e) {
			System.out.println("Connection: " + e.getMessage());
			e.printStackTrace();
		}
		
		//=================================================================
	}

	public Message receive() {

		Message message = new Message();
		byte[] recvbuf;

		// COMPLETED
		// read a segment (128 bytes) from the input stream and decapsulate into message
		// Hint: create a new Message object and use the decapsulate method
		
		//=================================================================
		
		try {
			recvbuf = inStream.readNBytes(MessageConfig.SEGMENTSIZE);
			message.decapsulate(recvbuf);
		} catch (IOException e) {
			System.out.println("Connection: " + e.getMessage());
			e.printStackTrace();
		}
		
		
		//=================================================================
		
		return message;
	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}