package commaweed.mods.block.model;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A lucky block.  It will be a singleton.
 */
public class LuckyBlock extends Block implements CommaweedBlockType {
	
	private static Item EXPLOSION_ITEM = Item.getItemFromBlock(Blocks.dirt);
	
	/**
	 * Defines the possible items that can drop when the user gets very lucky
	 */
	private static Item[] HIGH_CHANCE_ITEMS = new Item[] {
		Items.carrot,
		Item.getItemFromBlock(Blocks.enchanting_table),
		Items.saddle,
		Items.emerald,
		Items.blaze_rod,
		Items.diamond_axe,
		Items.diamond_boots,
		Items.diamond_chestplate,
		Items.diamond_helmet,
		Items.diamond_hoe,
		Items.diamond_horse_armor,
		Items.diamond_leggings,
		Items.diamond_pickaxe,
		Items.diamond_shovel,
		Items.diamond_sword,
		Items.ender_pearl,
		Item.getItemFromBlock(Blocks.tnt)
	};
	
	/**
	 * Defines the possible items that can drop when the user gets slightly lucky.  The last item is the unlucky
	 * one (EXPLOSION_ITEM).
	 */
	private static Item[] LOW_CHANCE_ITEMS = new Item[] {
		Items.apple,
		Items.stick,
		Items.coal,
		Item.getItemFromBlock(Blocks.torch),
		Items.cooked_porkchop,
		Items.cooked_beef,
		Item.getItemFromBlock(Blocks.wool),
		EXPLOSION_ITEM
	};
	
	
	/**
	 * Initialize.
	 */
	public LuckyBlock() {
		super(Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.0F);
		this.setResistance(2.0F);
		this.setStepSound(soundTypeStone);
		this.setUnlocalizedName(this.getRegistryName());
	}
	
	/**
	 * Returns the item dropped by using a random number generator.
	 * @param state The block state.
	 * @param random random generator
	 * @param fortune The fortune level
	 */
	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune) {
		Item result;
		
		int firstRandomChoice = 1 + random.nextInt(100);
		
		// 1% chance to get high item, 20% chance to get low item (but 1 of those items will cause explosion)
		if (firstRandomChoice > 99) {
			result = HIGH_CHANCE_ITEMS[random.nextInt(HIGH_CHANCE_ITEMS.length)];
		} else {
			int secondRandomChoice = 1 + random.nextInt(10);
			if (secondRandomChoice > 8) {
				result = LOW_CHANCE_ITEMS[random.nextInt(LOW_CHANCE_ITEMS.length)];
			} else {
				result = null;
			}
		}
		
		return result;
	}

    /**
     * Gets the quantity dropped based on the given fortune level
     * @param fortune The fortune level
     * @param random random generator
     */
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		return 1;
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
//		IBlockState state = world.getBlockState(pos);
//		Random rand = world instanceof World ? ((World) world).rand : new Random();
		return 1;
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
		return "luckyblock";
	}

	/**
	 * Returns a reference to the underlying block.
	 */
	@Override
	public Block getBlock() {
		return this;
	}
	
	/**
	 * Cause an explosion around the given entity in the given world.
	 * @param world
	 * @param entity
	 */
	private static void causeExplosion(World world, Entity entity, BlockPos blockPosition) {
		world.newExplosion(
			entity,
			blockPosition.getX(),	//explosionX
			blockPosition.getY(),	//explosionY
			blockPosition.getZ(),	//explosionZ
			2,	//explosionSize
			true,	//isFlaming
			true	//isSmoking
		); 	
	}
	
	/**
	 * Callback for a break event.  Any time a break event occurs,  this method will get called, even if it 
	 * wasn't meant for this block.
	 * @param event The break event that occurred.
	 */
	@SubscribeEvent
	public void onBlockBroken(HarvestDropsEvent event) {
		Block block = event.state.getBlock();
		if (block instanceof LuckyBlock) {
			LuckyBlock luckyBlock = (LuckyBlock) block;
			
			List<ItemStack> drops = event.drops;
			if (drops != null && drops.size() == 1) {
				Item itemThatDropped = drops.get(0).getItem();
				
				String outputText;
				if (ArrayUtils.contains(HIGH_CHANCE_ITEMS, itemThatDropped)) {
					outputText = EnumChatFormatting.GREEN + "VERY Lucky you!  " + new ItemStack(itemThatDropped).getDisplayName() + " dropped!";
					event.world.playSoundAtEntity(event.harvester, "ambient.weather.thunder", 2f, 1f);
				} else if (itemThatDropped != null && itemThatDropped.equals(EXPLOSION_ITEM)) {
					outputText = EnumChatFormatting.RED + "VERY UNLUCKY!";
					causeExplosion(event.world, event.harvester, event.pos);
				} else {
					outputText = EnumChatFormatting.YELLOW + "Lucky you!  " + new ItemStack(itemThatDropped).getDisplayName() + " dropped!";
					event.world.playSoundAtEntity(event.harvester, "mob.villager.death", 2f, 1f);
				}
				
				event.harvester.addChatMessage(new ChatComponentText(outputText));
			} 
		} 
	}
}
