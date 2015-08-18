package com.havenspool.nettyamf3;


import com.havenspool.nettyamf3.codec.Amf3Decoder;
import com.havenspool.nettyamf3.codec.Amf3Encoder;
import com.havenspool.nettyamf3.handler.ExtensionHandler;
import com.havenspool.nettyamf3.handler.SystemHandler;
import io.netty.channel.ChannelPipeline;



public class ServerPipelineFactory implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("decoder", new Amf3Decoder());
		pipeline.addLast("encoder", new Amf3Encoder());
		
		pipeline.addLast("sys", new SystemHandler());
		pipeline.addLast("ex", new ExtensionHandler());
		
		return pipeline;
	}

}
