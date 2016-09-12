package xyz.aurelioncraft.utils;

import org.bukkit.command.PluginCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	public static JavaPlugin utilsPlugin;
	public static String version;
	
	@Override
	public void onEnable()
	{
		version = getDescription().getVersion();
		utilsPlugin = this;
		registerAdminChatCommand();
		this.getLogger().info("Enabled aurelioncraft utils " + version);
	}
	
	@Override
	public void onDisable()
	{
		this.getLogger().info("Disabled aurelioncraft utils " + version);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		if (e.isCancelled()) return;
		if (e.getMessage().startsWith(AdminChatCommand.key))
		{
			e.setCancelled(true);
			AdminChatCommand.sendAcMessage(e.getPlayer(), e.getMessage().replaceFirst(AdminChatCommand.key, ""));
		}
	}
	
	private void registerAdminChatCommand()
	{
		PluginCommand command = getCommand("adminchat");
		command.setDescription("Sends a message to other staff members. Permnodes = utils.ac.use/utils.ac.see");
		command.setPermission("utils.ac.use");
		command.setPermissionMessage(Utils.noPerm);
		command.setUsage("/adminchat <message>");
		command.setExecutor(new AdminChatCommand());
	}
}
