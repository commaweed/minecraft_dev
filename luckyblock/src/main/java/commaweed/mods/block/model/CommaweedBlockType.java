package commaweed.mods.block.model;

import net.minecraft.block.Block;

/**
 * All blocks in the commaweed mod must implement this.
 */
public interface CommaweedBlockType {
    
    /**
     * Returns the registry name of the custom commaweed block.
     * @return A string representing the block registry name.
     */
    String getRegistryName();
    
    /**
     * Returns a reference to the underlying block.
     * @return A reference to the underlying block.
     */
    Block getBlock();
    
}