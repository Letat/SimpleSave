package org.desmin88.simplesave;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SSplayerListener extends PlayerListener{
	private int players = 0;
	public SimpleSave plugin;
	public SSplayerListener(SimpleSave instance,int playercount){
		plugin = instance;
		if(playercount > 0) {
			this.players = playercount;
			Run();
		}
	}
	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		players++;
		if(players == 1) {
			Run();
		}
	}

	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		players--;
		if(players == 0 & plugin.ConfigArray[20].equals("false")) {
			plugin.getServer().getScheduler().cancelTasks(plugin);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, plugin.new SaveMethod());
			plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, plugin.new BackupMethod());

		}
	}

	@Override
	public void onPlayerKick(PlayerKickEvent event) {
		players--;
		if(players == 0 & plugin.ConfigArray[20].equals("false")) {
			plugin.getServer().getScheduler().cancelTasks(plugin);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, plugin.new SaveMethod());
			plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, plugin.new BackupMethod());

		}
	}
	public void Run() {
		if(plugin.ConfigArray[0].equals("true")){
			long saveinterval = java.lang.Integer.parseInt(plugin.ConfigArray[1].replaceAll("\\D", "")) * 1200;
			plugin.ScheduleSave(saveinterval);
			plugin.log.info("SimpleSave: Current save interval is " + plugin.ConfigArray[1] + " minute(s)");
		}
		if(plugin.ConfigArray[6].equals("true")){
			long saveinterval = java.lang.Integer.parseInt(plugin.ConfigArray[7].replaceAll("\\D", "")) * 1200;
			plugin.ScheduleBackup(saveinterval);
			plugin.log.info("SimpleSave: Current backup interval is " + plugin.ConfigArray[7] + " minute(s)");
		}
	}

}

