package com.havenspool.nettyamf3.manager;

import com.havenspool.nettyamf3.domain.Zone;
import static com.havenspool.nettyamf3.Config.ZONES;

import java.util.HashMap;
import java.util.Map;


public class ZoneManager {

	public static ZoneManager instance = new  ZoneManager();

	private final Map<String,Zone> zones;
	
	private ZoneManager(){
		zones = new HashMap<String, Zone>();
	}
	
	public Zone getZone(String name){
		return zones.get(name);
	}
	
	public void setUpZone(){
		for(String zoneName:ZONES){
			Zone zone = new Zone(zoneName);
			zones.put(zoneName, zone);
		}
	}
}
