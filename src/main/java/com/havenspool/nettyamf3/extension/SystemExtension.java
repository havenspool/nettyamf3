package com.havenspool.nettyamf3.extension;


import com.havenspool.nettyamf3.codec.ActionScriptObject;
import com.havenspool.nettyamf3.domain.User;
import com.havenspool.nettyamf3.manager.UserManager;

public class SystemExtension extends Extension{
	
	private final String CMD_JOIN_ROOM = "1-1";

	@Override
	public void request(String cmd, ActionScriptObject ao, User sender,
			String fromRoom) {
		if(cmd.equals(CMD_JOIN_ROOM)){
			this.handleJoinRoom(cmd, ao, sender, fromRoom);
		}
	}
	
	private  void handleJoinRoom(String cmd, ActionScriptObject ao, User sender,
			String fromRoom){
		String room = ao.getString("room");
		UserManager.instance.joinRoom(sender,room);

		ActionScriptObject res = createAsObject(cmd);
		res.putString("room",room);
		this.sendResponse(res,sender);
	}

}
