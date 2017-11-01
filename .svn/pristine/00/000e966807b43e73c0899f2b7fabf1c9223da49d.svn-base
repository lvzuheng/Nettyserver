package com.lzh.net.handler;


import com.lzh.net.utils.ConnectManager;
import com.lzh.net.utils.ConnectionView;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;


@Sharable
public class TcpHandler extends NettyHandler{
	
	
	private ConnectionView    connectionView;
	public int TIMEOUT = 20;


	/*接收到消息*/
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub 
		super.channelRead(ctx, msg);	
	}
	
	/*连接已建立*/
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	// TODO Auto-generated method stub
		super.channelActive(ctx);
		   connectionView = new ConnectionView(ctx.channel());
	       ConnectManager.getInstance().saveConnect(ctx.channel().remoteAddress(),connectionView);
	       onChannelCreate(connectionView);
	}
	

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub 
		super.channelInactive(ctx);
		onChannelClose(ConnectManager.getInstance().getConnect(ctx.channel().remoteAddress()));
		ctx.close();
	}
	
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
	// TODO Auto-generated method stub 
		super.userEventTriggered(ctx, evt);
		if(evt instanceof IdleStateEvent){
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
			if (idleStateEvent.state() == IdleState.ALL_IDLE) {
				setAllIdleState(ctx);
			}else if(idleStateEvent.state() == IdleState.READER_IDLE){
				setReadIdleState(ctx);
			}else if(idleStateEvent.state() == IdleState.WRITER_IDLE){
				setWriteIdleState(ctx);
			}
		}
	}
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub	通道解除注册
		super.channelUnregistered(ctx);	
	}

	//通道全空闲
	public void setAllIdleState(ChannelHandlerContext ctx) {}
	//通道读空闲
	public void setReadIdleState(ChannelHandlerContext ctx) {}
	//通道写空闲
	public void setWriteIdleState(ChannelHandlerContext ctx) {}
	
    public void onChannelCreate(ConnectionView connectionView) {}
    public void onChannelClose(ConnectionView connectionView) {}

	public int getTIMEOUT() {
		return TIMEOUT;
	}

	public TcpHandler setTIMEOUT(int tIMEOUT) {
		TIMEOUT = tIMEOUT;
		return this;
	}

}
