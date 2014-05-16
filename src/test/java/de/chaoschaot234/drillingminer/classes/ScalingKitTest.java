package de.chaoschaot234.drillingminer.classes;

import static org.junit.Assert.*;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;

import static de.chaoschaot234.drillingminer.classes.ScalingKit.getMinimalStacks;

public class ScalingKitTest {
    
    @Test
    public void newKitIsEmpty() {
        ScalingKit kit = new ScalingKit();
        assertEquals(0, kit.materialSet().size());
        assertEquals(0, kit.getItems(0).size());
        assertEquals(0, kit.getItems(1000).size());
    }
    
    @Test
    public void testAddAndRemoveFlatIngredient() {
        ScalingKit kit = new ScalingKit();
        kit.setFlat(Material.EGG, 3);
        assertEquals(1, kit.materialSet().size());
        assertTrue(kit.materialSet().contains(Material.EGG));
        kit.setFlat(Material.EGG, 0);
        assertEquals(0, kit.materialSet().size());
        assertFalse(kit.materialSet().contains(Material.EGG));
    }
    
    @Test
    public void testAddAndRemoveScalingIngredient() {
        ScalingKit kit = new ScalingKit();
        kit.setScaling(Material.EGG, 3);
        assertEquals(1, kit.materialSet().size());
        assertTrue(kit.materialSet().contains(Material.EGG));
        kit.setScaling(Material.EGG, 0);
        assertEquals(0, kit.materialSet().size());
        assertFalse(kit.materialSet().contains(Material.EGG));
    }
   
    @Test
    public void testScaling() {
        ScalingKit kit = new ScalingKit();
        kit.setScaling(Material.STONE, 1);
        for (int i = 0; i < 2500; i += 21) {
            List<ItemStack> stacks = kit.getItems(i);
            int count = 0;
            for (ItemStack stack : stacks) {
                assertEquals(Material.STONE, stack.getType());
                count += stack.getAmount();
            }
            assertEquals(i, count);
        }
    }

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
