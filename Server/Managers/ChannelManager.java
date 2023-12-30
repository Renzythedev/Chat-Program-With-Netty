package Server.Managers;

import Server.Utils.Channels;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class ChannelManager {

    private HashMap<String,Channel> channels;

    public ChannelManager() {
        channels = new HashMap<String, Channel>();
    }

    public void addChannel(Channel channel) {
        channels.put(Channels.getIP(channel),channel);
    }

    public void removeChannel(Channel channel) {
        channels.remove(Channels.getIP(channel), channel);
    }

    public boolean isActive(Channel channel) {
        return channels.containsKey(Channels.getIP(channel));
    }

    public Channel getChannel(String ip) {
        Channel channel = channels.get(ip);
        return channel;
    }

    public void sendMessage(Channel channel,String msg) {
        String ip = Channels.getIP(channel);
        Channel toChannel = channels.get(ip);
        toChannel.writeAndFlush(msg);
    }

    public void sendMessageToAllChannels(String msg) {
        for(Map.Entry<String, Channel> ctx : channels.entrySet()) {
            Channel toChannel = ctx.getValue();
            toChannel.writeAndFlush(msg);
        }
    }

    public void sendMessageToAllChannels(Channel channel, String msg) {
        for(Map.Entry<String, Channel> ctx : channels.entrySet()) {
            Channel toChannel = ctx.getValue();
            if(!(toChannel == channel)) {
                toChannel.writeAndFlush(msg);
            }

        }
    }

}

