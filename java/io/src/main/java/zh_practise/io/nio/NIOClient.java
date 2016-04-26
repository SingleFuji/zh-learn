package zh_practise.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


public class NIOClient {

	public static void main(String[] args) throws IOException {
		SocketChannel clientChannel = SocketChannel.open();
		clientChannel.configureBlocking(false);
		clientChannel.connect(new InetSocketAddress("127.0.0.1",4983));
		Selector selector = Selector.open();
		clientChannel.register(selector, SelectionKey.OP_CONNECT);
		while(true) {
			selector.select();
			Iterator ite = selector.selectedKeys().iterator();
			while(ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();
				ite.remove();
				if(key.isConnectable())
				{
					SocketChannel channel = (SocketChannel) key.channel();
					if(channel.isConnectionPending())
					{
						channel.finishConnect();
					}
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap("ReqReq".getBytes()));
					channel.register(selector, SelectionKey.OP_READ);
					
				} else if(key.isReadable()) {
					SocketChannel channel = (SocketChannel) key.channel();
					ByteBuffer buffer = ByteBuffer.allocate(30);
					channel.read(buffer);
					byte[] data = buffer.array();
					System.out.println(new String(data));
					//写数据
					ByteBuffer outBuffer = ByteBuffer.wrap("ReqReqHH".getBytes());
					channel.write(outBuffer);
				}
				
			}
		}
	}
}
