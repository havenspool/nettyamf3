package com.havenspool.nettyamf3.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.havenspool.nettyamf3.Config.ROOMS;

public class Zone {
	private final String name;
	private final Map<String,Room>rooms;
	private final Map<String,User> users;

	public Zone(String name){
		this.name = name;
		rooms = new ConcurrentHashMap<String,Room>();
		users = new ConcurrentHashMap<String,User>();
		setUpRooms();
	}

	public void addUser(User user){
		users.put(user.getUserName(),user);
	}

	public void removeUser(User user){
		users.remove(user.getUserName());
	}

	private void setUpRooms(){
		for(String room:ROOMS){
			rooms.put(room,new Room(room,this));
		}
	}
	
	public String getName(){
		return name;
	}
	
	public Room getRoom(String roomName){
		return rooms.get(roomName);
	}
}
