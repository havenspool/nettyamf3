package com.havenspool.nettyamf3.manager;


import com.havenspool.nettyamf3.domain.Room;
import com.havenspool.nettyamf3.domain.User;
import com.havenspool.nettyamf3.domain.Zone;
import io.netty.channel.Channel;

public class UserManager {

	public static UserManager instance = new UserManager();

	public User joinZone(String userName,String zoneName,Channel channel){
		Zone zone = ZoneManager.instance.getZone(zoneName);
		User user = new User(userName,channel,zone);
		zone.addUser(user);
		return user;
	}

	public boolean joinRoom(User user,String roomName){
		Room room = user.getZone().getRoom(roomName);
		room.addUser(user);
		user.addRoom(room);
		return true;
	}

	public void lostCon(User user){
		user.getRoom().removeUser(user);
		user.getZone().removeUser(user);
		ChannelManager.instance.remove(user.getChannel());
	}
}
