package com.havenspool.nettyamf3.extension;


import com.havenspool.nettyamf3.codec.ActionScriptObject;
import com.havenspool.nettyamf3.domain.User;

import java.util.Set;

public abstract class Extension {
	public void init(){}

	public void destroy(){}

	public abstract void request(String cmd,ActionScriptObject ao,User sender,String fromRoom);

	public void sendResponse(ActionScriptObject ao,Set<User> recipients){
		for(User user:recipients){
			if(user.getChannel().isWritable()){
				user.getChannel().write(ao.getObject());
			}
		}
	}
	
	public void sendResponse(ActionScriptObject ao,User recipient){
		recipient.getChannel().write(ao.getObject());
	}
	
	protected ActionScriptObject createAsObject(String cmd){
		ActionScriptObject ao = new ActionScriptObject();
		ao.putString("cmd", cmd);
		return ao;
	}
}
