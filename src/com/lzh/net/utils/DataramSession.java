package com.lzh.net.utils;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;

public class DataramSession {

	private InetSocketAddress inetSocketAddress;
	private ChannelHandlerContext channelHandlerContext;
	private String id;
	public static Map<String, DataramSession> dMap = new HashMap<>();
	public static void create(String id,InetSocketAddress inetSocketAddress,ChannelHandlerContext channelHandlerContext){
		dMap.put(id, new DataramSession(id, inetSocketAddress, channelHandlerContext));
	}
	public static String create(InetSocketAddress inetSocketAddress,ChannelHandlerContext channelHandlerContext){
		UUID uuid=UUID.randomUUID();
		String str = uuid.toString(); 
		String uuidStr=str.replace("-", "");
		create(uuidStr, inetSocketAddress, channelHandlerContext);
		return uuidStr;
	}

	public static DataramSession achieve(String id){
		return dMap.get(id);
	}
	public static Set<DataramSession> getEntry(){
		return (Set<DataramSession>) dMap.values();
	}
	
	private DataramSession( String id,InetSocketAddress inetSocketAddress,ChannelHandlerContext channelHandlerContext){
		setId(id);
		setInetSocketAddress(inetSocketAddress);
		setChannelHandlerContext(channelHandlerContext);
	}

	public InetSocketAddress getInetSocketAddress() {
		return inetSocketAddress;
	}
	public void setInetSocketAddress(InetSocketAddress inetSocketAddress) {
		this.inetSocketAddress = inetSocketAddress;
	}
	public ChannelHandlerContext getChannelHandlerContext() {
		return channelHandlerContext;
	}
	public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
		this.channelHandlerContext = channelHandlerContext;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public void send(byte[] b){
		channelHandlerContext.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(b),inetSocketAddress));
	}
}
