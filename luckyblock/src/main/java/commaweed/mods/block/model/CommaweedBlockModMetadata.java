package commaweed.mods.block.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;
import commaweed.mods.block.CommaweedBlockMod;
import commaweed.mods.block.gen.CommaweedBlockWorldGenerator;

/**
 * Provides all of the meta data for the commaweed block mod and registers them as needed.
 */
public class CommaweedBlockModMetadata {
	
	// define blocks and items used by this mod
	private static LuckyBlock luckyBlock = new LuckyBlock();
	private static JubbleyBlock jubbleyBlock = new JubbleyBlock();
	private static ExtraDiamondOreBlock xDiamondOreBlock = new ExtraDiamondOreBlock();
	
	/**
	 * Prevent instantiation because this class contains purely static methods and members.
	 */
	private CommaweedBlockModMetadata() { }
	
	/**
	 * Registers all of the blocks and items.
	 */
	private static void registerBlock(CommaweedBlockType blockType) {
		System.out.println(blockType.getRegistryName());
		
		GameRegistry.registerBlock(blockType.getBlock(), blockType.getRegistryName());
		
		Item item = GameRegistry.findItem(CommaweedBlockMod.MODID, blockType.getRegistryName());
		ModelResourceLocation resourceLocator = new ModelResourceLocation(
			CommaweedBlockMod.MODID + ":" + blockType.getRegistryName(), 
			"inventory"
		);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
			item,
			0, 
			resourceLocator
		);	
	}
	
	/**
	 * Registers objects that are listening for events.
	 */
	private static void registerEvents() {
		MinecraftForge.EVENT_BUS.register(luckyBlock);
	}
	
	/**
	 * Registers all the pertinent mod metadata with the appropriate registries.
	 */
	public static void registerMetadata() {
		registerBlock(luckyBlock);
		registerBlock(jubbleyBlock);
		registerBlock(xDiamondOreBlock);
		
		registerEvents();
		
		// block generators
		GameRegistry.registerWorldGenerator(
			new CommaweedBlockWorldGenerator(luckyBlock, 10, 100, 16, 16, 16, 90), 
			0	// weight: lower number takes priority over other mods
		);
		
		GameRegistry.registerWorldGenerator(
			new CommaweedBlockWorldGenerator(jubbleyBlock, 5, 25, 16, 16, 60, 90), 
			1	
		);
		
		GameRegistry.registerWorldGenerator(
			new CommaweedBlockWorldGenerator(xDiamondOreBlock, 6, 1, 16, 16, 16, 100), 
			2	
		);
		
		KatiesRecipes.registerRecipes(jubbleyBlock);
		
	}
	
}