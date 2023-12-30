package Client.Utils;

import io.netty.channel.EventLoopGroup;

public class Group {

    public static void stop(EventLoopGroup group) {
        group.shutdownGracefully();
    }
}
