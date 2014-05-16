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
						//TODO: Add a "new Kit()" with defined materials to give.
					} else if(p.hasPermission("dm.kit.other")) {
						//TODO: Add a "new Kit()" with defined materials to give.
					} else {
						permMsg(p);
					}
				}
				if(args[0].equalsIgnoreCase("playerkit")) {
					if(p.hasPermission("dm.pkit.self")) {
						//TODO: Add a "new Kit()" with defined materials to give.
					} else if (p.hasPermission("dm.pkit.other")) {
						//TODO: Add a "new Kit()" with defined materials to give.
					} else {
						permMsg(p);
					}
				}
			}
		}
		else {
			cs.sendMessage("§cYou can only perform this command as a player");
		}
		
		return true;
	}
	
	private void help(CommandSender cs) {
		if(cs.hasPermission("dm.help")) {
			cs.sendMessage("§a ######################################################################################################");
			cs.sendMessage("§a # §3 §lDrillingMiner Help");
			cs.sendMessage("§a # §6/dm §bhelp §f: To get this help.");
			if(cs.hasPermission("dm.kit.self") || cs.hasPermission("dm.kit.other")) {
				cs.sendMessage("§a # §6/dm §bkit §a[player] §f: Gives you or a player the DM Kit to build it.");
			}
			cs.sendMessage("§a ######################################################################################################");
		}
		else {
			permMsg(cs);
		}
	}
	
	private void permMsg(CommandSender cs) {
		cs.sendMessage("Â§cYou don't have permission to perform this command");
	}

}
