package com.lzh.net.handler;


import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.LineEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class StringInitializer extends ChannelInitializer<Channel>{

	private List<ChannelHandler> list = new ArrayList<>();
	private int TIMEOUT = -1;
	
	@Override
	protected void initChannel(Channel channel) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.addFirst(new LineBasedFrameDecoder(Integer.MAX_VALUE));
		pipeline.addLast(new StringDecoder());
		pipeline.addLast(new StringEncoder());
		pipeline.addLast(new IdleStateHandler(TIMEOUT,TIMEOUT,TIMEOUT));
		for(int i =0;i<list.size();i++){
			pipeline.addLast(list.get(i));
		}	
	
	}
	
	public StringInitializer setHandler(ChannelHandler handler) {
//		System.out.println(handler.getClass().getName()+":"+getClass().isAnnotationPresent(Sharable.class));
//		if(!handler.getClass().isAnnotationPresent(Sharable.class)){
			list.add(handler);
//		}
		return this;
	}

	public int getTIMEOUT() {
		return TIMEOUT;
	}

	public StringInitializer setTIMEOUT(int tIMEOUT) {
		TIMEOUT = tIMEOUT;
		return this;
	}

}
