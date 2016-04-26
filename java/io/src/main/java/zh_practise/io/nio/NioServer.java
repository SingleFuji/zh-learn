package zh_practise.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {

	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.bind(new InetSocketAddress("127.0.0.1", 4093));
		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true)	{
			selector.select();
			Iterator ite = selector.selectedKeys().iterator();
			while(ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();
				ite.remove();
				//如果是客户端请求连接事件
				if(key.isAcceptable())
				{
					ServerSocketChannel server = (ServerSocketChannel)key.channel();
					SocketChannel channel = server.accept();
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap("Channel Server".getBytes()));
					channel.register(selector, SelectionKey.OP_READ);
				} else if(key.isReadable()) //读事件
				{
					SocketChannel channel = (SocketChannel) key.channel();
					ByteBuffer buffer = ByteBuffer.allocate(10);
					channel.read(buffer);
					byte[] data = buffer.array();
					String msg = new String(data);
					System.out.println("accept:"+msg);
					ByteBuffer outBuffer = ByteBuffer.wrap("RespMsg".getBytes());
					channel.write(outBuffer);
				}
			}
		}
	}
}
