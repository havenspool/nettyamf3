package com.havenspool.nettyamf3.domain;

import java.util.HashSet;
import java.util.Set;

public class Room {
	private final String name;
	private final Zone zone;
	private final Set<User> users;

	public Room(String name, Zone zone) {
		this.name = name;
		this.zone = zone;
		users = new HashSet<User>();
	}

	public void addUser(User user) {
		synchronized (user) {
			users.add(user);
		}
	}

	public void removeUser(User user){
		synchronized (user) {
			users.remove(user);
		}
	}

	public String getName() {
		return name;
	}

	public Zone getZone() {
		return zone;
	}
}
