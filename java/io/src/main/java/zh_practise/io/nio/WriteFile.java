package zh_practise.io.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {

	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream(new File("D:\\0200.bin"));
		fos.write("j222j".getBytes());
		fos.flush();
		fos.close();
	}
}
