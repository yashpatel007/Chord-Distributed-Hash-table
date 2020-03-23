import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;

// Generated code
// import tutorial.*;
// import shared.*;

import java.util.HashMap;

public class Server {

	// make a file handler... as shown in tutorial
	private static FileHandler fileHandler;// writing the handler is tricky...haha
	//similar to from tutorial import calculator 
	// making a processor
	private static FileStore.Processor<FileStore.Iface> processor;
	
	private static void startServer(FileStore.Processor<FileStore.Iface> processor,int portNumber) {
		try {
			
			TServerTransport serverTransport = new TServerSocket(portNumber);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
			System.out.println("Staring the file server");
			server.serve();
			serverTransport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]){
		// getting the arguments ... it needs the 
		if(args.length!=1){
			System.out.println("Please enter port number");
			System.exit(0);
		}
		//trimming the args to the widespaces are avoided
		int portNumber = Integer.parseInt(args[0].trim());
		fileHandler = new FileHandler(portNumber);
		processor = new FileStore.Processor<FileStore.Iface>(fileHandler);
		//give file handler to the  server
		startServer(processor,portNumber);
		}//main ends 
		
}
