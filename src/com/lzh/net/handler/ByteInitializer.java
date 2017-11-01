package com.lzh.net.handler;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.LineEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class ByteInitializer extends ChannelInitializer<Channel>{
	
	
	private List<ChannelHandler> last = new ArrayList<>();
	private List<ChannelHandler> first = new ArrayList<>();
	private int TIMEOUT = -1;
	
	@Override
	protected void initChannel(Channel channel) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = channel.pipeline();
//		pipeline.addLast(new LineBasedFrameDecoder(100*1024));
		pipeline.addLast(new ByteArrayDecoder());
		pipeline.addLast(new ByteArrayEncoder());
		pipeline.addLast(new IdleStateHandler(TIMEOUT,TIMEOUT,TIMEOUT));
		for(int i =0;i<last.size();i++){
			pipeline.addLast(last.get(i));
		}
		for(int i =0;i<first.size();i++){
			pipeline.addFirst(first.get(i));
		}
	
	}
	
	public ByteInitializer addLast(ChannelHandler handler) {
			last.add(handler);
			return this;
	}
	public ByteInitializer addFirst(ChannelHandler handler) {
		first.add(handler);
		return this;
}


	public int getTIMEOUT() {
		return TIMEOUT;
	}

	public ByteInitializer setTIMEOUT(int tIMEOUT) {
		TIMEOUT = tIMEOUT;
		return this;
	}
}
