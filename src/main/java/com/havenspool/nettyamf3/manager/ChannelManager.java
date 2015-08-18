package com.havenspool.nettyamf3.manager;


import com.havenspool.nettyamf3.domain.User;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelManager {
	
	public static ChannelManager instance = new ChannelManager();

	private final Map<Channel,User> allChannel;

	private ChannelManager(){
		allChannel = new ConcurrentHashMap<Channel,User>();
	}

	public User getUser(Channel channel){
		return allChannel.get(channel);
	}

	public void addUser(Channel channel,User user){
		allChannel.put(channel,user);
	}

	public void remove(Channel channel){
		allChannel.remove(channel);
	}

}
