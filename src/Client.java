//hello
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import java.io.UnsupportedEncodingException;

public class Client {

	public static void main(String[] args)throws TException {
		if (args.length != 2) {
			System.out.println("Incorrect number of arguments! Client is terminating...");
			System.exit(0);
		}
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		TTransport transport = new TSocket(host, port);
		
		int pt = 9505;
		System.out.println("PORT USED FOR KEY IS:"+pt);
		String val = host +":"+String.valueOf(pt);
		String mykey = getSHA256(val);
		System.out.println(mykey);

		try {
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			FileStore.Client client = new FileStore.Client(protocol);
			System.out.println("my node succ is :"+client.getNodeSucc());
			System.out.println("find Pred of key:"+mykey+":"+pt+"and is -->"+client.findPred(mykey));
			System.out.println("find Succ:"+client.findSucc(mykey));
			test(client);


		} catch (TException e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(0);
		}
	}

	public static void test(FileStore.Client client) throws TException{

	
		String filename = "example.txt";
		
		System.out.println("Writing the file: "+ filename);
		testWrite(client);
		System.out.println("Write PASSED\n");

		 System.out.println("Reading the file: "+filename);
		 testRead(client);
		 System.out.println("READ PASSED\n");

	}

	public static void testWrite(FileStore.Client client)throws TException{
	      String fileN = "example.txt";
	      String value = fileN;
	      
	      String fileId = getSHA256(value);
        
	      NodeID serverOwner = client.findSucc(fileId);
	      System.out.println("Server ID which owns given file is -> " + serverOwner);
        	
	      //code to call write 
        try{	
	       RFile file = new RFile();
        RFileMetadata metadata = new RFileMetadata();
    		   
    	metadata.setFilename(fileN);
       	file.setContent("This is new file1");
        file.setMeta(metadata);
        System.out.println("Starting write file");
       	client.writeFile(file);

       	}catch (TException e) {
			System.err.println("Error occured while executing write request: " + e.getMessage());
			System.exit(0);
		}
	}

	public static void testRead(FileStore.Client client)throws TException{
			try{
			RFile file1 = null;
        	System.out.println("Call to example.txt");
        	file1 = client.readFile("example.txt");
        	System.out.println("version-> "+ file1.getMeta().getVersion());
        	System.out.println("filename-> "+ file1.getMeta().getFilename());
        	System.out.println("content-> "+file1.getContent());
        	}catch(TException e){
        		System.err.println("Error occured while executing write request: " + e.getMessage());
				System.exit(0);
        	}
	}

	// got from stack overflow
	public static String getSHA256(String key) {
		StringBuilder result = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(key.getBytes());
			byte[] data = md.digest();
			for (int i = 0; i < data.length; i++) {
				result.append(String.format("%02x", data[i]));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}

