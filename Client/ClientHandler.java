package Client;

import Server.Server;
import Server.Utils.Channels;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof String) {
            ByteBuf byteBuf = null;
            try {
                byteBuf = Unpooled.copiedBuffer(((String) msg).getBytes());
                byte[] data = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(data);
                System.out.println(new String(data));
            }finally {
                byteBuf.release();
            }

        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String message= scanner.nextLine();
            ctx.writeAndFlush(message);
        }
    }
}
