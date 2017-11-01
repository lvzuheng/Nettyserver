package com.lzh.net.utils;

import java.util.concurrent.ConcurrentHashMap;


public class ConnectManager  {
	//	
	  private static ConnectManager instance;

	    public static ConnectManager getInstance() {
	        if (instance == null) {
	            instance = new ConnectManager();
	        }
	        return instance;
	    }

	    private ConnectManager() {
	    }

	    /** session map 缓存连接信息 **/
	    private ConcurrentHashMap<Object, ConnectionView> connectMap = new ConcurrentHashMap<Object, ConnectionView>();

	    public ConcurrentHashMap<Object, ConnectionView> getConnectMap() {
	        return connectMap;
	    }

	    public ConnectionView getConnect(Object key) {
	        if (key == null) {
	            return null;
	        }
	        ConnectionView connectionView = connectMap.get(key);
	        return connectionView;
	    }

	    public void saveConnect(Object key, ConnectionView value) {
	        if (key != null) {
	        	connectMap.put(key, value);
	        }
	    }

	    public void removeConnect(Object key) {
	        if (key != null)
	        	connectMap.remove(key);
	    }

	    public boolean contains(Object key) {
	        if (key == null)
	            return false;
	        return connectMap.containsKey(key);
	    }

	    /** session data map用来存放二进制消息剩余字节 **/
	    private ConcurrentHashMap<Object, byte[]> byteCache = new ConcurrentHashMap<Object, byte[]>();

	    public ConcurrentHashMap<Object, byte[]> getBytesCache() {
	        return byteCache;
	    }

	    public byte[] getCachedBytes(Object key) {
	        if (key == null)
	            return null;
	        return byteCache.get(key);
	    }

	    public void putBytesToCache(Object key, byte[] value) {
	        if (key != null)
	            byteCache.put(key, value);
	    }

	    public void removeCache(Object key) {
	        if (key != null)
	            byteCache.remove(key);
	    }

	    public boolean containsCache(Object key) {
	        if (key == null)
	            return false;
	        return byteCache.containsKey(key);
	    }
	
}
