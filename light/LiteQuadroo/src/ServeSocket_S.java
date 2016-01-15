import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ServeSocket_S  extends QuadRR {

public ServeSocket_S(QuadRR rr) {
	  super(rr);
	  String W =  rr.W;
	  int luid =rr.uid;
	  String m = rr.m;
	  statement = rr.statement;
	  System.out.println("in socket serve:" + W);
	   
		  // TODO Auto-generated catch block
		  ServerSocket servsock = null;
		try {
			servsock = new ServerSocket(13299);
			System.out.println("scocket waiting:");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		    while (true) {
		      System.out.println("Waiting...");

		      Socket sock = null;
			try {
				sock = servsock.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      System.out.println("Accepted connection : " + sock);

		      // sendfile
		      File myFile = new File ("C:/ActApprent/WS/pureQuadroo/qudtest.db");
		      byte [] mybytearray  = new byte [(int)myFile.length()];
		      FileInputStream fis = null;
			try {
				fis = new FileInputStream(myFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      BufferedInputStream bis = new BufferedInputStream(fis);
		      try {
				bis.read(mybytearray,0,mybytearray.length);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      OutputStream os = null;
			try {
				os = sock.getOutputStream();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      System.out.println("Sending...");
		      try {
				os.write(mybytearray,0,mybytearray.length);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      try {
				os.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      try {
				sock.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      }  
		  
		  }  

}
 