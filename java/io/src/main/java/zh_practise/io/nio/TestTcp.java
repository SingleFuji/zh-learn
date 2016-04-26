package zh_practise.io.nio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTcp {
	
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(4093);
			while(true) {
				Socket socket = server.accept();
				new Thread(new Mt(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class Mt  implements Runnable {
	Socket socket = null;
	public Mt(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			InputStream in = new BufferedInputStream(socket.getInputStream());
			if(in.available()>0)
			{
				
			}
			byte[] bytes = new byte[20];
			int count  =0;
			int num= 0;
			while((num=in.read(bytes)) != -1) {
				System.out.println(new String(bytes));
				System.out.println(count++);
			}
			System.out.println(new String(bytes));
			OutputStream out = socket.getOutputStream();
			FileOutputStream fos = new FileOutputStream(new File("D:\\0200.bin"));
			fos.write(bytes);
			fos.flush();
			fos.close();
			out.write(bytes);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
