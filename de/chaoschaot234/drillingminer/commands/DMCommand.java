package de.chaoschaot234.drillingminer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.chaoschaot234.drillingminer.DrillingMiner;

public class DMCommand implements CommandExecutor {

	private DrillingMiner main;

	public DMCommand(DrillingMiner dm) {
		main = dm;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		
		if(cs instanceof Player) {
			Player p = (Player) cs;
			if(args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("help"))) {
				help(p);
			}
			else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("kit")) {
					if(p.hasPermission("dm.kit.self")) {
						
					} else if(p.hasPermission("dm.kit.other")) {
						
					} else {
						permMsg(p);
					}
				}
				if(args[0].equalsIgnoreCase(""));
			}
		}
		else {
			cs.sendMessage("Â§cYou can only perform this command as a player");
		}
		
		return true;
	}
	
	private void help(CommandSender cs) {
		if(cs.hasPermission("dm.help")) {
			cs.sendMessage("Â§aâ•”â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�");
			cs.sendMessage("Â§aâ•‘  Â§3Â§lDrillingMiner Help");
			cs.sendMessage("Â§aâ•‘  Â§6/dm Â§bhelp Â§f: To get this help.");
			if(cs.hasPermission("dm.kit.self") || cs.hasPermission("dm.kit.other")) {
				cs.sendMessage("Â§aâ•‘  Â§6/dm Â§bkit Â§a[player] Â§f: Gives you or a player the DM Kit to build it.");
			}
			cs.sendMessage("Â§aâ•šâ•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�â•�");
		}
		else {
			permMsg(cs);
		}
	}
	
	private void permMsg(CommandSender cs) {
		cs.sendMessage("Â§cYou don't have permission to perform this command");
	}

}
