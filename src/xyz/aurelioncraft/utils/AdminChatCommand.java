package xyz.aurelioncraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminChatCommand implements CommandExecutor
{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!sender.hasPermission("ac.use")) return Utils.noPerm(sender);
		if (args.length == 0) return false;
		
		return sendAcMessage(sender, args);
	}
	
	public static boolean sendAcMessage(CommandSender sender, String... args)
	{
		String name = "";
		if (sender instanceof Player)
			name += ((Player) sender).getDisplayName();
		else
			name += ChatColor.translateAlternateColorCodes('&', "&9Console");
			
		String preparedPrefix = prefix + name + seperator;
		
		String message = Utils.join(" ", args);
		
		if (sender.hasPermission("essentials.chat.color"))
			message = ChatColor.translateAlternateColorCodes('&', message);
			
		String preparedMessage = preparedPrefix + message;
		
		for (Player p : Bukkit.getOnlinePlayers())
			if (p.hasPermission("ac.see")) p.sendMessage(preparedMessage);
		Main.utilsPlugin.getServer().getConsoleSender().sendMessage(preparedMessage);
		
		return true;
	}
	
	public static final String prefix = ChatColor.translateAlternateColorCodes('&', "&8[&cAC&8] ");
	public static final String seperator = ChatColor.translateAlternateColorCodes('&', "&8:&b ");
	
	public static final String key = ",";
}
