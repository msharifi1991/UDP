import java.io.*; 
	import java.net.*; 
import java.util.*;
	
public class UDPclient {
	
	 private InetAddress BCAddress;
	 boolean done;
	 boolean keepGoing;

	 public UDPclient()
	  {
	   String s1;
	   
	   try {    
	        BCAddress = InetAddress.getByName("255.255.255.255");
	        
	       }
	   catch (UnknownHostException ex) 
	       { 
	        System.err.println(ex);
	        System.exit(1);
	       }
	  
	   try{
	       DatagramSocket clientSocket = new DatagramSocket();  // Used to send packet to the server (or for broadcasting) 
	      
	       byte[] sendData = new byte[1024]; 
	       s1 = "hello'\'Saeed'\'192.168.1.82'\'9090";     // the message that the client wanted to send to the server to register
	       sendData = s1.getBytes();         

	       System.out.println ("Sending data to " + sendData.length + 
	                               " bytes to server from line " );
	       DatagramPacket sendPacket = 
	              new DatagramPacket(sendData, sendData.length, BCAddress, 9876); 
	         
           clientSocket.send(sendPacket); 	          

	       byte[] receiveData = new byte[1024]; 

	       keepGoing = true;

	       DatagramPacket receivePacket = 
	            new DatagramPacket(receiveData, receiveData.length); 
	  
	       System.out.println ("Waiting for return packet");
	       clientSocket.setSoTimeout(10000);

	       while (keepGoing)
	          {
	           try {
	              clientSocket.receive(receivePacket); 
	              String modifiedSentence = 
	                  new String(receivePacket.getData()); 
	              modifiedSentence = modifiedSentence.substring(0,5);
	  
	              InetAddress returnIPAddress = receivePacket.getAddress();
	        
	              int port = receivePacket.getPort();

	              System.out.println ("From server at: " + returnIPAddress + 
	                                  ":" + port);
	              System.out.println("I am client");
		          if(modifiedSentence.equals("Hello")){
		        	  
		        	  System.out.println("************");
		        	  
		          }
		          
		          else {}
	              System.out.println("Message: " + modifiedSentence); 

	             }
	         catch (SocketTimeoutException ste)
	             {
	              System.out.println ("Timeout Occurred: Packet assumed lost");
	              if (done)
	                keepGoing = false;
	             }
	  
	          }
	       clientSocket.close(); 
	      }
	  catch (IOException ex)
	     {
	      System.err.println(ex);
	     }
	  } 


	 public static void main(String args[]) throws Exception 
	   { 
	    String serverHostname = new String ("127.0.0.1");

	    if (args.length > 0)
	       serverHostname = args[0];
	  
	    new UDPclient ();

	   }
	

}
