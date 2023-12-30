package Server.Utils;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;

public class Channels {

    public static String getIP(Channel channel) {
        InetSocketAddress socketAdress = (InetSocketAddress) channel.remoteAddress();
        String ip = socketAdress.getAddress().getHostAddress();
        return ip;
    }
}
