import java.util.*;
import java.security.MessageDigest;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.omg.PortableServer.ThreadPolicyOperations;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransportException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import javafx.scene.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

public class FileHandler implements FileStore.Iface{

	private static List<NodeID> nodeList;
	private int currentNodePortNumber = 0;
	private String currentIpAddress;
	private static Map<String, RFile> fileSystem;
	
	public FileHandler(int port){
		// constructor for the class
		// port number is passed when constructing the object... so basically in Server.java
		currentNodePortNumber = port;
		nodeList = new ArrayList<NodeID>();
		fileSystem = new HashMap<String, RFile>();
	}


	@Override
	public void writeFile(RFile rf) throws TException{
		// function to write file to the server
		// in order to write the file we need to find the node which owns the file and then writite the file
		// both class in gen-java
		RFile servRFile = null;
		RFileMetadata servFileMetadata = null;
		// check if the rf is null
		if(rf != null){

			try{
			// beware it may throw the exception
			currentIpAddress = InetAddress.getLocalHost().getHostAddress();
			}catch(UnknownHostException uhe){
				uhe.printStackTrace();
			}
			String curripPort = currentIpAddress + ":" + Integer.toString(currentNodePortNumber);
			// we will need to find sucessor with file id and also need to implement the SHA for current node key
			String currNodeKeyvalue = getSHA256Hash(curripPort);

			RFileMetadata filemeta = rf.getMeta();
			String fname = filemeta.getFilename();
			//System.out.println("WF: file name is "+fname);
			String content = rf.getContent();
			String value = fname;
			String fId = getSHA256Hash(value);
			
			NodeID fileSucc = findSucc(fId);
			//System.out.println("WF: file succ is :"+fileSucc);
			
			if(fileSucc.getId().compareTo(currNodeKeyvalue) == 0){
				// check if the current node has the given file or not
				//System.out.println("WF: current node have the file");
				if(fileSystem.containsKey(fId)){
					//System.out.println("WF: file already present so incresing the version and setting contents");
					int version = fileSystem.get(fId).getMeta().getVersion();
					// increment the version 					
					fileSystem.get(fId).getMeta().setVersion(version+1);
					//overwrite the contents
					fileSystem.get(fId).setContent(content);
					
				}else{
					//System.out.println("WF: do not have file so creating one and then write");
					// if does not contains the key
					servFileMetadata = new RFileMetadata();
					servRFile = new RFile();
					// essentially we are creatinga file in the server here
					servFileMetadata.setFilename(fname);
					// set new file version to zero
					servFileMetadata.setVersion(0);
					
					try{

					File file = new File(fname);
					FileWriter fileWriter = new FileWriter(file);
					//write the content ot the file 
					fileWriter.write(content);
					fileWriter.close();
					}catch(IOException ioe){
						ioe.printStackTrace();
						System.exit(0);
					}
					String contentHashCode = getSHA256Hash(content);
					servFileMetadata.setContentHash(contentHashCode);
					
					servRFile.setMeta(servFileMetadata);
					servRFile.setContent(content);
					
					
					fileSystem.put(fId, servRFile);
				}
				
			}else{
				// system exception if server does not own the given file id
				SystemException e = new SystemException();
				e.setMessage("Exception: Server does not own given file ID");
				throw e;
			}
			
		}

	}

	@Override
	public RFile readFile(String filename) throws TException{
		// reading the file from server

		RFile serverRFile = null;
		RFileMetadata serverFileMetadata = null;
		SystemException exception = null;

		try {
			currentIpAddress = InetAddress.getLocalHost().getHostAddress();
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String val = currentIpAddress + ":" + Integer.toString(currentNodePortNumber);
		String currentNodeKey = getSHA256Hash(val);
		
		String value = filename;
		String fileId = getSHA256Hash(value);
		
		NodeID fileSucc = findSucc(fileId);
		//System.out.println("WF: file succ is :"+fileSucc);

		//Check if current node owns given file ID
		if(fileSucc.getId().compareTo(currentNodeKey) == 0) {
			// if yes then check if file arcenal have the key
			if(fileSystem.containsKey(fileId)) {
				serverRFile = fileSystem.get(fileId);
			}else {
				exception = new SystemException();
				exception.setMessage("File with given filename= "+filename+ " is not present on Server");
				throw exception;
			}

		}else {
			exception = new SystemException();
			exception.setMessage("Exception: Server does not own given file ID");
			throw exception;
		}
			
		return serverRFile;
	}

	@Override
	public void setFingertable(List<NodeID> nodelist) throws TException {
		// setting up the finger table
		for(NodeID node : nodelist) {
			nodeList.add(node);
		}
		// just printing the node list
		//System.out.println(nodeList.size()-1);
		// for(int i=nodeList.size()-1; i>=0;i--){
		// System.out.println("nl:"+nodeList.get(i).getPort());
		// }
	}

	@Override
	public NodeID findSucc(String key) throws SystemException,TException{
	
		NodeID currNode = null;
		currNode = new NodeID();
		String currIP = null;
		String port = null;
		String currKey = null;

		try{
			InetAddress ip = InetAddress.getLocalHost();
			currIP = ip.getHostAddress();
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		currKey = getSHA256Hash(currIP+":"+Integer.toString(currentNodePortNumber));

		currNode.setId(currKey);
		currNode.setIp(currIP);
		currNode.setPort(currentNodePortNumber);
		
		NodeID succNode = null, predNode = null;
		int compare = currKey.compareTo(key);
		if(compare == 0){
			succNode = currNode.deepCopy();
		}
		else{
			predNode = findPred(key);
			try{
				
				if(predNode == null){
					SystemException  exception = new SystemException();
					exception.setMessage("No Predecessor");			
					throw exception;
				}
				} catch (SystemException e) {
					System.out.println(e.getMessage());
				}
			if(predNode != null){
				String hostIP = predNode.getIp();
				int portNo = predNode.getPort();
				if(portNo == currentNodePortNumber){
					succNode = this.getNodeSucc();
				}
				else{
					try {
						TTransport transport;
						transport  = new TSocket(hostIP,portNo);
						transport.open();

						TProtocol protocol = new  TBinaryProtocol(transport);
						protocol = new TBinaryProtocol(transport);
						FileStore.Client client = new FileStore.Client(protocol);
						try {
							succNode=client.getNodeSucc();
							transport.close();
						} 
						catch (SystemException e) {
							e.printStackTrace();
						} 
						catch (TException e) {
							e.printStackTrace();
						}
					} catch (TTransportException e) {
						e.printStackTrace();
						System.exit(0);
					}
				}
			}
		}
		return succNode;
	}

	@Override
	public NodeID findPred(String key)  throws SystemException,TException{

		NodeID predNode = null;
		NodeID currNode = null;
		NodeID targetNode = null;
		currNode = new NodeID();
		
		String currIP = null;
		String port = null;
		String currKey = null;
		String targetKey = null;
		String firstKey = null;

		try{
			InetAddress ip = InetAddress.getLocalHost();
			currIP = ip.getHostAddress();
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		currKey = getSHA256Hash(currIP+":"+Integer.toString(currentNodePortNumber));

		currNode.setId(currKey);
		currNode.setIp(currIP);
		currNode.setPort(currentNodePortNumber);

		//System.out.println("FP: curr node is"+currNode.getPort());
		
		if(nodeList.size() == 0){
			try{
				SystemException exp = new SystemException();
				exp.setMessage("Exception: No Fingertable exist at the Node");
				throw exp;
			}
			
			catch(SystemException e){
				System.out.println(e.getMessage());
			}
		}
		// if size>0 means nodelist exists
		if(nodeList.size() > 0){
			// if size is more then zero proceed
			//set temp node as node succ
			NodeID tempNode = nodeList.get(0);

			//System.out.println("FP: temp node is->"+tempNode.getPort());

			firstKey = tempNode.getId();

			int value = currKey.compareTo(firstKey);//if the node succ is curr node itself
			int tempvalue1 = key.compareTo(firstKey);// compare key to first key
			int tempvalue2 = key.compareTo(currKey);//  compare key to current key
			
			//System.out.println("value curr|tempnode"+value+"\ntempvalue1 inpkey|tempnode"+tempvalue1+"\n tempvalue2 inpkey|currKey"+tempvalue2);

			if(value == 0){
				// as we are the pred node

				predNode = currNode;
				//System.out.println("FP: val is 0 RET pred node as ->"+predNode.getPort());
				return predNode;
			}
			else if(value >0){
				
				if(tempvalue1 <= 0 || tempvalue2 >=0){
					predNode = currNode;
					//System.out.println("value > 0 -- pred node = curr node"+predNode.getPort());
				}
				else{
					targetNode = nextNode(key);
					//System.out.println("FP: (val>0) targetnode set as making RPC to target"+targetNode.getPort());
					predNode = makeRPCCalltoNode(targetNode,key);
					//System.out.println("FP: prednode is"+predNode.getPort());
				}
			}else{
				if(tempvalue2 > 0 && tempvalue1 <= 0){
					predNode = currNode;
				}
				else{
					targetNode = nextNode(key);
					//System.out.println("FP: last else making RPC to target"+targetNode.getPort());
					predNode = makeRPCCalltoNode(targetNode,key);
					//System.out.println("FP: prednode is"+predNode.getPort());
				}
			}
		}
		return predNode;
	}

	
	private boolean inBet(String key, String id1, String id2) {
		if ((key.compareTo(id1) > 0) && (key.compareTo(id2) < 0))
			return true;
		else if ((id1.compareTo(id2) > 0)) {
			if ((key.compareTo(id1) < 0) && (key.compareTo(id2) < 0)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}


	@Override
	public NodeID getNodeSucc()  throws TException{
		// get current node successor
		SystemException exception = null;
		NodeID mysuccNode = null;
		if(nodeList.size()>0){
			// first entry has the sucessor node in the finger table 
			mysuccNode = nodeList.get(0);
		}else {
			exception = new SystemException();
			exception.setMessage("Exception: No finger table at the Node");
			throw exception;
		}
		return mysuccNode;
	}

    // taken the refrence from geeks for geeks
	private String getSHA256Hash(String str) {
		
		try{
			MessageDigest  digest = MessageDigest.getInstance("SHA-256");
		
			byte[] encodedhash = digest.digest(str.getBytes("UTF-8"));
			
			StringBuffer hexString = new StringBuffer();
			 
			for (int i = 0; i < encodedhash.length; i++) {	
		    		String hex = Integer.toHexString(0xff & encodedhash[i]);
		    
		    		if(hex.length() == 1)
		    			hexString.append('0');
		        
		    		hexString.append(hex);
		    }
		    
		    return hexString.toString();
	
		} catch(NoSuchAlgorithmException e){
			e.printStackTrace();	    
			throw new RuntimeException(e);
		}catch (UnsupportedEncodingException exception){
			exception.printStackTrace();
			throw new RuntimeException(exception);
		}
	}

	public NodeID makeRPCCalltoNode(NodeID node, String key) {

		NodeID predNode=null;
		
		if(currentNodePortNumber == node.getPort()){
			try {
				SystemException  exception = new SystemException();
				exception.setMessage("same port requested");			
				throw exception;
			} catch (SystemException e) {
				System.out.println(e.getMessage());
			}
		}

		String hostIP = node.getIp();
		int portNo = node.getPort();
		
		try {
			TTransport transport;
			transport  = new TSocket(hostIP,portNo);
			transport.open();
			TProtocol protocol = new  TBinaryProtocol(transport);
			protocol = new TBinaryProtocol(transport);
			FileStore.Client client = new FileStore.Client(protocol);
			try {
				predNode=client.findPred(key);
				transport.close();
			} 
			catch (SystemException e) {
				e.printStackTrace();
			} 
			catch (TException e) {
				e.printStackTrace();
			}
			
		} catch (TTransportException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return predNode;
		
		
	}
	

	public NodeID nextNode(java.lang.String key){
		
		NodeID firstNode,secondNode;
		NodeID targetNode = null;
		String firstKey,secondKey;
		int first=0;
		int second=1;
		int flag=1;

		while(flag==1){
			firstNode = nodeList.get(first);
			secondNode = nodeList.get(second);
			secondKey = secondNode.getId();
			firstKey = firstNode.getId();
			
			int value = firstKey.compareTo(secondKey);
			int tempvalue1=key.compareTo(secondKey);
			int tempvalue2=key.compareTo(firstKey);
			if(value == 0){
				first++;
				second++;
				if(second>=nodeList.size()){
					flag=0;
					targetNode = nodeList.get(nodeList.size()-1);
					break;
				}
			}
			else if(value > 0){
				if(tempvalue1<=0||tempvalue2>=0){
					targetNode = firstNode;
					break;
				}
				else{
					first++;
					second++;
					if(second>=nodeList.size()){
						flag=0;
						targetNode = nodeList.get(nodeList.size()-1);
						break;
					}
				}
			}
			else{
				if(tempvalue2>0 && tempvalue1 <= 0){
					targetNode = firstNode;
					break;
				}
				else{
					first++;
					second++;
					if(second>=nodeList.size()){
						flag=0;
						targetNode = nodeList.get(nodeList.size()-1);	
						break;
					}
				}
			}
			
		}
		return targetNode;
	}

}	
