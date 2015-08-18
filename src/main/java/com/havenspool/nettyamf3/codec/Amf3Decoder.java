package com.havenspool.nettyamf3.codec;

import java.io.ByteArrayInputStream;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;


import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;
import io.netty.handler.codec.ByteToMessageDecoder;


public class Amf3Decoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(in.readableBytes()>=4){
			in.markReaderIndex();
			int needBytes = in.readInt();
			if(in.readableBytes()>=needBytes){
				in.resetReaderIndex();
				byte[] content = new byte[in.readableBytes()];
				in.readBytes(content);
				Amf3Input amf3Input = new Amf3Input(SerializationContext.getSerializationContext());
				amf3Input.setInputStream(new ByteArrayInputStream(content));
				Object decoded = amf3Input.readObject();
				if (decoded != null) {
					out.add(decoded);
				}
			}
		}
	}
}
