package de.chaoschaot234.drillingminer.classes;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * A kit of Items scaling with a given parameter.
 * 
 */
public class ScalingKit {

    private Map<Material, Integer> flat;
    private Map<Material, Integer> scaling;

    public ScalingKit() {
        this.flat = new HashMap<Material, Integer>();
        this.scaling = new HashMap<Material, Integer>();
    }

    /**
     * Set the flat amount of {@code mat} given.
     * 
     * @param mat
     *            Material in question
     * @param amount
     *            how many of the material should be given flat
     */
    public void setFlat(Material mat, int amount) {
        if (amount > 0) {
            this.flat.put(mat, amount);
        } else {
            this.flat.remove(mat);
        }
    }

    /**
     * Set the amount of {@code mat} given per scaling unit.
     * 
     * @param mat
     *            Material in question
     * @param amount
     *            how many of the material should be given per scaling unit
     */
    public void setScaling(Material mat, int amount) {
        if (amount > 0) {
            this.scaling.put(mat, amount);
        } else {
            this.scaling.remove(mat);
        }
    }

    /**
     * Calculate the kit for a given parameter
     * 
     * @param param parameter
     * @return flat items + (param * scaling items)
     */
    public List<ItemStack> getItems(int param) {
        Set<Material> mats = this.materialSet();
        List<ItemStack> result = new LinkedList<ItemStack>();

        for (Material mat : mats) {
            int flatamount = (this.flat.get(mat) == null) ? 0 : this.flat.get(mat);
            int scaleamount = (this.scaling.get(mat) == null) ? 0 : this.scaling.get(mat);

            int amount = flatamount + param * scaleamount;

            result.addAll(getMinimalStacks(mat, amount));
        }
        return result;
    }

    /**
     * Calculates the minimal list of stacks
     * 
     * @param mat
     *            Material the stacks should contain
     * @param amount
     *            total amount of material the stacks should contain
     * @return Stacks of {@code mat} containing {@code amount} units in total
     */
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

    /**
     * Gives all the material types contained in the kit.
     * 
     * Attention: If a Material has only a scaling value set, it will be in the
     * materialSet but {@code getItems(0)} will not contain a Stack of it
     * 
     * @return a Set of Material types in the set
     */
    public Set<Material> materialSet() {
        Set<Material> mats = new TreeSet<Material>();
        mats.addAll(this.flat.keySet());
        mats.addAll(this.scaling.keySet());
        return mats;
    }
}
