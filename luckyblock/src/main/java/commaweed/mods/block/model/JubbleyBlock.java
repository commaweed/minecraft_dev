package commaweed.mods.block.model;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.RecipesBanners.RecipeAddPattern;
import net.minecraft.item.crafting.RecipesCrafting;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Katie's tulip block.  She calls tulips "JUBBLEYs".
 */
public class JubbleyBlock extends Block implements CommaweedBlockType {
    
    /**
     * Initialize.
     */
    public JubbleyBlock() {
        super(Material.grass);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.0F);
        this.setResistance(2.0F);
        this.setStepSound(soundTypeGrass);
        this.setUnlocalizedName(this.getRegistryName());
    }
    
    /**
     * Returns the item dropped.
     * @param state The block state.
     * @param random random number generator.
     * @param fortune The fortune level.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return Item.getItemFromBlock(Blocks.red_flower);
    }
    
    /**
     * Gets the quantity dropped based on the given fortune level
     * @param fortune The fortune level
     * @param random random generator
     */
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return 9 * (fortune <= 0 ? 1 : fortune);
    }
    
    /**
     * {@inherit}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the unlocalName
     */
    public String getRegistryName() {
        return "jubbleyblock";
    }
    
    /**
     * Returns a reference to the underlying block.
     */
    @Override
    public Block getBlock() {
        return this;
    }   
}
