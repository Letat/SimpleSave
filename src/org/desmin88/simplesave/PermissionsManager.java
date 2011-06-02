package org.desmin88.simplesave;

import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class PermissionsManager {
	private static PermissionHandler permissionHandler;
	private static Logger log = Logger.getLogger("Minecraft");
	
    public static void setupPermissions(Plugin plugin) {
        Plugin permissionsPlugin = plugin.getServer().getPluginManager().getPlugin("Permissions");

        if (permissionHandler == null) {
            if (permissionsPlugin != null) {
                permissionHandler = ((Permissions) permissionsPlugin).getHandler();
            } else {
                log.info("[" + plugin.getDescription().getName() + "] Permissions not detected, defaulting to Ops");
            }
        }
    }
    
    public static boolean usingPermissions() {
    	return (permissionHandler != null);
    }
    
    public static boolean hasPermission(Player p, String cmd) {
		if (usingPermissions()) {
		    if (permissionHandler.has(p, cmd))
		        return true;
		    return false;
		} else if (p.isOp())
		    return true;
		return false;
    }
}
