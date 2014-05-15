package de.chaoschaot234.drillingminer.classes;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DMKit {
	
	private Map<Material, Integer> flat;
	private Map<Material, Integer> scaling;
	
	public DMKit() {
		this.flat = new HashMap<Material, Integer>();
		this.scaling = new HashMap<Material, Integer>();
	}
	
	public void setFlat(Material mat, int amount) {
		if (amount > 0) {
			this.flat.put(mat, amount);
		} else {
			this.flat.remove(mat);
		}
	}
	
	public void setScaling(Material mat, int amount) {
		if (amount > 0) {
			this.scaling.put(mat, amount);
		} else {
			this.scaling.remove(mat);
		}
	}
	
	public List<ItemStack> getItems(int param) {
		Set<Material> mats = new TreeSet<Material>();
		mats.addAll(this.flat.keySet());
		mats.addAll(this.scaling.keySet());
		
		List<ItemStack> result = new LinkedList<ItemStack>();
		
		for (Material mat : mats) {
			int flatamount = (this.flat.get(mat) == null) ? 0 : this.flat.get(mat);
			int scaleamount = (this.scaling.get(mat) == null) ? 0 : this.scaling.get(mat);
			
			int amount = flatamount + param * scaleamount;
			
			result.addAll(getMinimalStacks(mat, amount));
		}
		return result;
	}
	
	public List<ItemStack> getMinimalStacks(Material mat, int amount) {
		List<ItemStack> result = new LinkedList<ItemStack>();
		int maxStackSize = new ItemStack(mat).getMaxStackSize();
		
		while (amount > maxStackSize) {
			result.add(new ItemStack(mat, amount));
			amount -= maxStackSize;
		}
		result.add(new ItemStack(mat, amount));
		return result;
	}
}
