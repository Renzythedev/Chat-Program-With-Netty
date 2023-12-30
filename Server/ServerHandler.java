package Server;

import Server.Utils.Channels;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        ByteBuf byteBuf = null;
        try {
            byteBuf = Unpooled.copiedBuffer(((String) msg).getBytes());
            byte[] data = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(data);
            System.out.println("["+ Channels.getIP(channel) +"]: " + new String(data));
            Server.channels.sendMessageToAllChannels(channel, "["+ Channels.getIP(channel) +"]: "+ new String(data));
        }finally {
            byteBuf.release();
        }

    
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        Server.channels.addChannel(channel);
        System.out.println("[SERVER]: " + Channels.getIP(channel) + " connected to server");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        Server.channels.removeChannel(channel);
        System.out.println("[SERVER]: " + Channels.getIP(channel) + " disconnected from server");
    }

}
