Project : Chord-Distributed-Hash-Table

<b>Discription</b> : 
	This is an implementation Chord Distributed Hash Table(using Apache thrift as IDL). The program implementation is done in java.


<b>Environment Setup</b>:

			To use Thrift installed under home directory, you need to set the environment variable PATH:		
			$> bash
			$> export PATH=$PATH:/home/yaoliu/src_code/local/bin
						
<b>How to Compile</b>:
			
			* First clone the repository into a folder by using command : git clone https://github.com/Yao-Liu-CS457-CS557/cs457-557-f19-pa2-yashpatel007.git
			* Then run the command $>ip address
				then change the IP Address in node.txt with your machine IP Address
			* then run these two commands 
				$>make clean
				and then run command 
				$>make
				this should compile the code

<b>Running the code</b>:

		* To run the code open three terminal windows on the machine and navigate to the project folder. Then type 
			$> ./server.sh <portnumber> // as in your node.txt file
			do this for all the nodes in nodes.txt file. Now your servers are up and ready for listening.
			
			Note* : if the system give exception that port can not be used, try using diffrent port number or wait for a while.
			
			Once all your servers are up and running.
			Run Init program:
				For each DHT node, a fingertable needs to be initialized. In this assignment, I have provided a finger table initializer program that will compute the fingertable for each running DHT node and send each fingertable (using
				the setFingertable server method you implement) to the appropriate node. The initializer program takes a
				filename as its only command line argument. To run the initializer:
				$> chmod +x init
				$> ./init nodes.txt
				
			This should initialize all the nodes with their finger table.
			
<b>Testing with Client</b>:
		
		* run the command $> ./client.sh <ipaddress> <port number>
			make sure the ip address is the same as your machine ip address
			
			
<b>Implementation Status</b>:
	
		* Program is working, all the functions described are working.
		* I have tested readFile, writefile and according to me they are working.
		
			
