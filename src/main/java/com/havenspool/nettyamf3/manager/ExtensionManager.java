package com.havenspool.nettyamf3.manager;


import com.havenspool.nettyamf3.extension.ChatExtension;
import com.havenspool.nettyamf3.extension.Extension;
import com.havenspool.nettyamf3.extension.SystemExtension;

import java.util.HashMap;
import java.util.Map;

public class ExtensionManager {

	private final Map<String,Extension> extensions;

	public static final ExtensionManager instance = new ExtensionManager();
	
	private ExtensionManager(){
		extensions = new HashMap<String,Extension>();
		extensions.put("sys",new SystemExtension());
		extensions.put("chat",new ChatExtension());
	}

	public Extension getExtension(String name){
		return extensions.get(name);
	}
}
