package com.havenspool.nettyamf3.handler;


import com.havenspool.nettyamf3.codec.ActionScriptObject;
import com.havenspool.nettyamf3.domain.User;
import com.havenspool.nettyamf3.manager.ChannelManager;
import com.havenspool.nettyamf3.manager.UserManager;
import flex.messaging.io.amf.ASObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class SystemHandler extends SimpleChannelInboundHandler{

	public void messageReceived(ChannelHandlerContext ctx,MessageEvent e)throws Exception{
		ActionScriptObject ao = new ActionScriptObject((ASObject)e.getMessage());
		String cmd = ao.getString("cmd");
		if(cmd.equals("Login")){
			firstLogin(ao,ctx,e);
		}else{
			super.messageReceived(ctx,e);
		}
	}

	private void firstLogin(ActionScriptObject ao,ChannelHandlerContext ctx,MessageEvent e){
		String userName = ao.getString("n");
		String zoneName = ao.getString("z");

		User user = UserManager.instance.joinZone(userName,zoneName,e.getChannel());
		ChannelManager.instance.addUser(e.getChannel(),user);

		ActionScriptObject res = new ActionScriptObject();
		res.putString("cmd", "Login");
		res.putBool("res",true);
		e.getChannel().write(res.getObject());
	}

	public void channelDisconnected(ChannelHandlerContext ctx,ChannelStateEvent e)throws Exception{
		closeChannel(e.getChannel());
		super.channelDisconnected(ctx,e);
	}

	private void closeChannel(Channel channel){
		User user = ChannelManager.instance.getUser(channel);
		if(user!=null){
			UserManager.instance.lostCon(user);
		}
	}
}
