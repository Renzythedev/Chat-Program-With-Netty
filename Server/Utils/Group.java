package Server.Utils;

import io.netty.channel.EventLoopGroup;

public class Group {

    public static void stop(EventLoopGroup patern, EventLoopGroup child) {
        patern.shutdownGracefully();
        child.shutdownGracefully();
    }
}
