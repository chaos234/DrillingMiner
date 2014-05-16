package de.chaoschaot234.drillingminer;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.chaoschaot234.drillingminer.commands.DMCommand;

public class DrillingMiner extends JavaPlugin {

	public YamlConfiguration m;


	@Override
	public void onEnable() {
		loadConfig();
		getCommands();
		registerEvents();
		System.out.println("[DrillingMiner v." + getDescription().getVersion() + "] enabled!");
	}
	
	@Override
	public void onDisable() {
		System.out.println("[DrillingMiner v." + getDescription().getVersion() + "] disabled!");
	}
	
	private void getCommands() {
		getCommand("drillingminer").setExecutor(new DMCommand(this));
	}
	
	private void registerEvents() {
		
	}
	
	private void loadConfig() {
		saveDefaultConfig();
		m = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "miners.yml"));
		saveMiners();
	}
	
	public void saveMiners() {
		try {
			m.save(new File(getDataFolder(), "miners.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
