package com.havenspool.nettyamf3.handler;


import com.havenspool.nettyamf3.codec.ActionScriptObject;
import com.havenspool.nettyamf3.domain.User;
import com.havenspool.nettyamf3.extension.Extension;
import com.havenspool.nettyamf3.manager.ChannelManager;
import com.havenspool.nettyamf3.manager.ExtensionManager;
import flex.messaging.io.amf.ASObject;
import io.netty.channel.ChannelHandlerContext;

public class ExtensionHandler extends SimpleChannelHandler {
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		ActionScriptObject ao = new ActionScriptObject((ASObject)e.getMessage());
		String cmd = ao.getString("cmd");
		String roomName = ao.getString("r");
		String exName = ao.getString("ex");
		ActionScriptObject param = ao.getObject("p");

		User user = ChannelManager.instance.getUser(e.getChannel());
		if(user != null){
			Extension extension = ExtensionManager.instance.getExtension(exName);
			extension.request(cmd,param,user,roomName);
		}
	}
}
