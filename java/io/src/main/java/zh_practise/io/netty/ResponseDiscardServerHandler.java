package zh_practise.io.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ResponseDiscardServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.write(msg); // (1)
        ctx.flush(); // (2)
        
//        ctx.writeAndFlush(msg);
    }

}
