package zh_practise.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 处理服务端 channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
    	
    	ByteBuf in = (ByteBuf) msg;
    	try {
    		while (in.isReadable()) {
    			System.out.print((char) in.readByte());
    			System.out.flush();
    		}
//    		System.out.println(in.toString(CharsetUtil.UTF_8));
    	} finally {
    		ReferenceCountUtil.release(msg);
    	}
    	
//        // 默默地丢弃收到的数据
//        ((ByteBuf) msg).release(); // (3)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}