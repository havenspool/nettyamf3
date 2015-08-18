package com.havenspool.nettyamf3.domain;


import io.netty.channel.Channel;

public class User {
	private final String userName;
	private final Channel channel;
	private final Zone zone;
	private  Room room;

	public User(String userName,Channel channel,Zone zone){
		this.userName = userName;
		this.channel = channel;
		this.zone = zone;
	}

	public void addRoom(Room room){
		this.room = room;
	}

	public String getUserName() {
		return userName;
	}

	public Channel getChannel() {
		return channel;
	}

	public Zone getZone() {
		return zone;
	}

	public Room getRoom() {
		return room;
	}
	
}
