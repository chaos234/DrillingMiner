package de.chaoschaot234.drillingminer.classes;

import static org.junit.Assert.*;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;

import static de.chaoschaot234.drillingminer.classes.ScalingKit.getMinimalStacks;

public class ScalingKitTest {

    @Test
    public void testGetMinimalStacksAreMinimal() {
        assertEquals(2, getMinimalStacks(Material.STONE, 120).size());
        assertEquals(14, getMinimalStacks(Material.IRON_AXE, 14).size());
        assertEquals(10, getMinimalStacks(Material.EGG, 149).size());
    }
    
    @Test
    public void testGetMinimalStacksContainCorrectAmount() {
        Material[] materials = {Material.WOOL, Material.EGG, Material.WOOD_PICKAXE, Material.BIRCH_WOOD_STAIRS};
        int amounts[] = {2432, 123, 12, 53};
        
        for (int i = 0; i < materials.length; i++) {
            List<ItemStack> stacks = getMinimalStacks(materials[i], amounts[i]);
            int count = 0;
            for (ItemStack stack : stacks) {
                assertEquals(materials[i], stack.getType());
                count += stack.getAmount();
            }
            assertEquals(amounts[i], count);
        }
    }

}
