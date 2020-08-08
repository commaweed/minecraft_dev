package commaweed.mods.block.model;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Contains all the crafting recipes that Katie will use.
 */
public class KatiesRecipes {
    
    private static ItemStack jubbleySword = new ItemStack(Items.diamond_sword);
    private static ItemStack jubbleyChest = new ItemStack(Items.diamond_chestplate);
    private static ItemStack jubbleyHelmet = new ItemStack(Items.diamond_helmet);
    private static ItemStack jubbleyBoots = new ItemStack(Items.diamond_boots);
    private static ItemStack jubbleyLeggings = new ItemStack(Items.diamond_leggings);
    
    private static Enchantment[] SWORD_ENCHANTMENTS = new Enchantment[] { 
        Enchantment.smite
    };
    
    private static void addRandomEnchantments() {
        
    }
    
    /**
     * prevent instantiation.
     */
    private KatiesRecipes() { }
    
    public static void registerRecipes(JubbleyBlock jubbleyBlock) {
        // recipes
        GameRegistry.addRecipe(
            new ItemStack(jubbleyBlock), 
            "XXX",
            "XXX",
            "XXX",
            'X',
            Item.getItemFromBlock(Blocks.red_flower)
        );
        
        ItemStack jubbleySword = new ItemStack(Items.diamond_sword);
        jubbleySword.addEnchantment(Enchantment.smite, 4);
        jubbleySword.addEnchantment(Enchantment.power, 4);
        jubbleySword.addEnchantment(Enchantment.unbreaking, 4);
        jubbleySword.addEnchantment(Enchantment.sharpness, 4);
        jubbleySword.addEnchantment(Enchantment.fireAspect, 4);
        
        GameRegistry.addRecipe(
            jubbleySword, 
            " X ",
            " X ",
            " Y ",
            'X', jubbleyBlock, 
            'Y', Items.apple 
        );
        
        ItemStack jubbleyChest = new ItemStack(Items.diamond_chestplate);
        jubbleyChest.addEnchantment(Enchantment.fireProtection, 4);
        jubbleyChest.addEnchantment(Enchantment.blastProtection, 4);
        jubbleyChest.addEnchantment(Enchantment.unbreaking, 4);
        jubbleyChest.addEnchantment(Enchantment.thorns, 4);
        jubbleyChest.addEnchantment(Enchantment.projectileProtection, 4);
        
        GameRegistry.addRecipe(
            jubbleyChest, 
            "X X",
            "XXX",
            "XXX",
            'X',
            jubbleyBlock
        );
        
        
        ItemStack jubbleyHelmet = new ItemStack(Items.diamond_helmet);
        jubbleyHelmet.addEnchantment(Enchantment.aquaAffinity, 4);
        jubbleyHelmet.addEnchantment(Enchantment.respiration, 4);
        jubbleyHelmet.addEnchantment(Enchantment.unbreaking, 4);
        jubbleyHelmet.addEnchantment(Enchantment.thorns, 4);
        jubbleyHelmet.addEnchantment(Enchantment.projectileProtection, 4);
        
        GameRegistry.addRecipe(
            jubbleyHelmet, 
            "XXX",
            "X X",
            "   ",
            'X',
            jubbleyBlock
        );
        
        ItemStack jubbleyBoots = new ItemStack(Items.diamond_boots);
        jubbleyBoots.addEnchantment(Enchantment.featherFalling, 4);
        jubbleyBoots.addEnchantment(Enchantment.thorns, 4);
        jubbleyBoots.addEnchantment(Enchantment.unbreaking, 4);
        jubbleyBoots.addEnchantment(Enchantment.protection, 4);
        jubbleyBoots.addEnchantment(Enchantment.blastProtection, 4);
        
        GameRegistry.addRecipe(
            jubbleyBoots, 
            "XXX",
            "X X",
            "   ",
            'X',
            jubbleyBlock
        );
        
        ItemStack jubbleyLeggings = new ItemStack(Items.diamond_leggings);
        jubbleyLeggings.addEnchantment(Enchantment.thorns, 4);
        jubbleyLeggings.addEnchantment(Enchantment.blastProtection, 4);
        jubbleyLeggings.addEnchantment(Enchantment.unbreaking, 4);
        jubbleyLeggings.addEnchantment(Enchantment.protection, 4);
        jubbleyLeggings.addEnchantment(Enchantment.blastProtection, 4);
        
        GameRegistry.addRecipe(
            jubbleyLeggings, 
            "XXX",
            "X X",
            "X X",
            'X',
            jubbleyBlock
        );
    }
}