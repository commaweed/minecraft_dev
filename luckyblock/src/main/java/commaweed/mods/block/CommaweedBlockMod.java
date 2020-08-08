package commaweed.mods.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import commaweed.mods.block.model.CommaweedBlockModMetadata;
import commaweed.mods.block.model.LuckyBlock;

/**
 * Minecraft Mod that adds addition special diamond blocks to the game.
 */
@Mod(modid = CommaweedBlockMod.MODID, version = CommaweedBlockMod.VERSION)
public class CommaweedBlockMod {
	
	public static final String MODID = "commaweedblockmod";
	public static final String VERSION = "1.0";
	
	/**
	 * Mod initialization.
	 * @param event Initialization event.
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		CommaweedBlockModMetadata.registerMetadata();
	}

}
