package com.havenspool.nettyamf3;

import com.havenspool.nettyamf3.manager.ZoneManager;
import io.netty.bootstrap.ServerBootstrap;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


public class Server {
	public static void main(String[] args) {
		init();
		setUpSocketServer();
	}

	private static void init() {
		ZoneManager.instance.setUpZone();
	}

	private static void setUpSocketServer() {
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(Executors
						.newCachedThreadPool(), Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new ServerPipelineFactory());
		bootstrap.bind(new InetSocketAddress(8090));
	}
}
