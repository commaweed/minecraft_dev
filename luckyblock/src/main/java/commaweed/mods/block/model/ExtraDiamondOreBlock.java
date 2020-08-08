package commaweed.mods.block.model;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Special diamond block.
 */
public class ExtraDiamondOreBlock extends Block implements CommaweedBlockType {
    
    private final String registryName = "xdiamondoreblock";

    /**
     * Initialize.
     */
    public ExtraDiamondOreBlock() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName(this.getRegistryName());
        this.setHarvestLevel("pickaxe", 2); // 0 = wood (testing), we want iron = 2 
        
        // same as diamond
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundTypePiston);
    }
    
    /**
     * Returns the item dropped.
     * @param state The block state.
     * @param random random generator
     * @param fortune The fortune level
     */
    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return Items.diamond;
    }
    
    /**
     * Gathers how much experience this block drops when broken.
     * @param world The world
     * @param pos Block position
     * @param fortune
     * @return Amount of XP from breaking this block.
     */
    @Override
    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune) {
        // same as diamond
        Random random = world instanceof World ? ((World) world).rand : new Random();
        return MathHelper.getRandomIntegerInRange(random, 3, 7);
    }   
    
    /**
     * Gets the quantity dropped based on the given fortune level
     * @param fortune The fortune level
     * @param random random generator
     */
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        // same as diamond
        if (fortune > 0) {
            int j = random.nextInt(fortune + 2) - 1;

            if (j < 0) {
                j = 0;
            }

            return this.quantityDropped(random) * (j + 1);
        } else {
            return this.quantityDropped(random);
        }
    }
    
    /**
     * {@inherit}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * {@inherit}
     */
    public String getRegistryName() {
        return "xdiamondoreblock";
    }

    /**
     * {@inherit}
     */
    @Override
    public Block getBlock() {
        return this;
    }
    
}
