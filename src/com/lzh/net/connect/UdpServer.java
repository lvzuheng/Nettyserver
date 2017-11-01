package com.lzh.net.connect;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UdpServer {

	private int port = -1;
	private String address = null;
	private ChannelHandler handler = null;
	private Bootstrap bootstrap ;
	private Channel channel;

	public UdpServer() {
		// TODO Auto-generated constructor stub
	}

	public UdpServer create() {
		bootstrap = new Bootstrap();
		EventLoopGroup eventLoopGroup= new NioEventLoopGroup();
		bootstrap.group(eventLoopGroup);
		bootstrap.channel( NioDatagramChannel.class);
		return this;
	}

	public Channel open() {
		if(port != -1){
			try {
				channel =bootstrap.bind(port).sync().channel();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return channel;
	}



	public int getPort() {
		return port;
	}


	public UdpServer setPort(int port) {
		this.port = port;
		return this;
	}


	public String getAddress() {
		return address;
	}


	public UdpServer setAddress(String address) {
		this.address = address;
		return this;
	}


	public ChannelHandler getHandler() {
		return handler;
	}


	public UdpServer setHandler(ChannelHandler handler) {
		this.handler = handler;
		bootstrap.handler(handler);
		return this;
	}


	public boolean isConnected() {
		return channel.isActive();
	}

}
