import java.io.*; 
import java.net.*; 
	  
public class UDPserver {
	 String[][] client_list = new String[100][4];
	 int client_num;
	 
	 public UDPserver(){
		try
	     { 
	      DatagramSocket serverSocket = new DatagramSocket(9876); 
	  
	      byte[] receiveData = new byte[1024]; 
	      byte[] sendData  = new byte[1024]; 
	      
	      while(true) 
	        { 
	  
	          receiveData = new byte[1024]; 

	          DatagramPacket receivePacket = 
	             new DatagramPacket(receiveData, receiveData.length); 

	          System.out.println ("I am Server");

	          serverSocket.receive(receivePacket); 

	          String sentence = new String(receivePacket.getData()).trim();
	          
	          processMsg(sentence);       //***********************
	         
	          InetAddress IPAddress = receivePacket.getAddress(); 
	  
	          int port = receivePacket.getPort(); 
	  
	          System.out.println ("From: " + IPAddress + ":" + port);
	          System.out.println ("Message: " + sentence);
	          
	          
	          String capitalizedSentence = sentence.toUpperCase(); 

	          sendData = capitalizedSentence.getBytes(); 
	  
	          DatagramPacket sendPacket = 
	             new DatagramPacket(sendData, sendData.length, IPAddress, 
	                               port); 
	  
	          serverSocket.send(sendPacket); 

	        } 

	     }
	      catch (SocketException ex) {
	        System.out.println("UDP Port 9876 is occupied.");
	        System.exit(1);
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}
	
	public void processMsg(String sentence){
		int index1 = 0;
		int index2 = sentence.indexOf("'\'");
		
		String command = sentence.substring(index1, index2); 
		
		if (command.equals("hello")){
			index1 = index2 + 1;
			index2 = sentence.indexOf("'\'", index1);
			String user = sentence.substring(index1, index2); 
			
			index1 = index2 + 1;
			index2 = sentence.indexOf("'\'", index1);
			String clientIP = sentence.substring(index1, index2); 
			
			index1 = index2 + 1;
			String clientPort = sentence.substring(index1);
			
			saveUser(user, clientIP, clientPort);
			sendClientList("hello",clientIP,clientPort);
			
			System.out.println("command: " + command + ", User: " + user + ", IP: "+ clientIP+ ", Port: " + clientPort);
		}
		else if (command .equals("check")){
			index1 = index2 + 1;
			index2 = sentence.indexOf("'\'", index1);
			String clientIP = sentence.substring(index1, index2); 
			
			index1 = index2 + 1;
			String clientPort = sentence.substring(index1);
			
			sendClientList("check",clientIP,clientPort);
			System.out.println("command: " + command + ", IP: "+ clientIP+ ", Port: " + clientPort);

		}
	  }

	public void sendClientList(String MsgType,String clientIP, String clientPort){
		System.out.println("the list:"+MsgType+"/"+clientIP+"/"+clientPort); 
		
	}
	
	public void saveUser(String user, String clientIP, String clientPort){
		client_list[client_num][0]=user;
		client_list[client_num][1]=clientIP;
		client_list[client_num][2]=clientPort;
		client_num++;
	}

	public static void main(String args[]) throws Exception{ 
	     UDPserver udps = new UDPserver();
	}
	  
	  
	  
}
