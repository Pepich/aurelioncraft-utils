package xyz.aurelioncraft.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Utils
{
	public static final String noPerm = ChatColor.translateAlternateColorCodes('&',
			"&cSorry but you don't have permissions to run this command!");
			
	public static String join(String seperator, String... toJoin)
	{
		String result = "";
		for (String s : toJoin)
		{
			result += seperator + s;
		}
		result = result.replaceFirst(seperator, "");
		return result;
	}
	
	public static boolean noPerm(CommandSender sender)
	{
		sender.sendMessage(noPerm);
		return true;
	}
}
