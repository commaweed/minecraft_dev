package commaweed.mods.block.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * Randomly places the underlying block into the world.
 */
public class CommaweedBlockWorldGenerator implements IWorldGenerator {

	private final Block blockToSpawn;
	private final int maxVeinSize;
	private final int chancetospawn;
	private final int blockMaximumXCoordinate;
	private final int blockMaximumZCoordinate;
	private final int blockMinimumYCoordinate;
	private final int blockMaximumYCoordinate;
	
	/**
	 * Initialize with the given block to spawn.
	 * @param blockToSpawn
	 * @param maxVeinSize The maximum number of block spawns per vein.
	 * @param chancesToSpawn The number of chances to spawn (accomplished via a for-loop).
	 * @param blockMaximumXCoordinate The maximum X coordinate to use for the block.
	 * @param blockMaximumZCoordinate The maximum Z coordinate to use for the block.
	 * @param blockMinimumYCoordinate The minimum Y coordinate to use for the block (y is vertical position)
	 * @param blockMaximumYCoordinate The maximum Y coordinate to use for the block.
	 */
	public CommaweedBlockWorldGenerator(
		Block blockToSpawn,
		int maxVeinSize,
		int chancetospawn,
		int blockMaximumXCoordinate,
		int blockMaximumZCoordinate,
		int blockMinimumYCoordinate,
		int blockMaximumYCoordinate			
	) {
		this.blockToSpawn = blockToSpawn;
		
		validateCoordinates(blockMaximumXCoordinate, blockMaximumZCoordinate, blockMinimumYCoordinate, blockMaximumYCoordinate);
		
		this.maxVeinSize = maxVeinSize;
		this.chancetospawn = chancetospawn;
		this.blockMaximumXCoordinate = blockMaximumXCoordinate;
		this.blockMaximumZCoordinate = blockMaximumZCoordinate;
		this.blockMinimumYCoordinate = blockMinimumYCoordinate;
		this.blockMaximumYCoordinate = blockMaximumYCoordinate;
	}
	
	/**
	 * {@inherit}
	 */
	@Override
	public void generate(
			Random random,
			int chunkX, 
			int chunkZ, 
			World world,
			IChunkProvider chunkGenerator, 
			IChunkProvider chunkProvider
	) {		
		switch(world.provider.getDimensionId()) {
			case -1:	// generate in the nether world
				break;
			case 0:		// generate in the overworld
				generateSurface(world, random, chunkX, chunkZ);
				break;
			case 1:		// generate in the end world
				break;
		}
	}
	
	/**
	 * Generates the surface.
	 * @param world The world.
	 * @param random A random number generator.
	 * @param x The x chunk coordinate.
	 * @param z The z chunk coordinate.
	 */
	private void generateSurface(
		World world,
		Random random,
		int chunkX, 
		int chunkZ
	) {
		this.randomlySpawnBlocksForChunk(
			this.blockToSpawn,
			world,
			random,
			chunkX,
			chunkZ
		);
	}
	
	/**
	 * Validates the given block boundary coordinates.
	 * @param blockMaximumXCoordinate
	 * @param blockMaximumZCoordinate
	 * @param blockMinimumYCoordinate
	 * @param blockMaximumYCoordinate
	 */
	private void validateCoordinates(
		int blockMaximumXCoordinate,
		int blockMaximumZCoordinate,
		int blockMinimumYCoordinate,
		int blockMaximumYCoordinate	
	) {
		if (blockMaximumXCoordinate <= 0 || blockMaximumXCoordinate > 16) {
			throw new IllegalArgumentException("Invalid blockMaximumXCoordinate; it must must be between (1, 16) inclusive");
		}
		
		if (blockMinimumYCoordinate <= 0 || blockMinimumYCoordinate > blockMaximumYCoordinate) {
			throw new IllegalArgumentException("Invalid blockMinimumYCoordinate; it must must be between (1, blockMaximumYCoordinate) inclusive");
		}
		
		if (blockMaximumYCoordinate <= 0 || blockMaximumYCoordinate > 255) {
			throw new IllegalArgumentException("Invalid blockMaximumYCoordinate; it muist be between (1, 255) inclusive");
		}
		
		if (blockMaximumZCoordinate <= 0 || blockMaximumZCoordinate > 16) {
			throw new IllegalArgumentException("Invalid blockMaximumZCoordinate; it muist be between (1, 16) inclusive");
		}	
	}
	
	/**
	 * Generates the blocks.
	 * @param block The block to randomly add to the world.
	 * @param world The world to add the block to.
	 * @param random The random number generator.
	 * @param maxVeinSize The maximum number of block spawns per vein.
	 * @param chancesToSpawn The number of chances to spawn (accomplished via a for-loop).
	 * @param blockMaximumXCoordinate The maximum X coordinate to use for the block.
	 * @param blockMaximumZCoordinate The maximum Z coordinate to use for the block.
	 * @param chancetospawn The number of chances to spawn (accomplished via a for-loop).
	 * @param blockMinimumYCoordinate The minimum Y coordinate to use for the block (y is vertical position)
	 * @param blockMaximumYCoordinate The maximum Y coordinate to use for the block.
	 */
	private void randomlySpawnBlocksForChunk(
		Block block,
		World world,
		Random random,
		int chunkX, 
		int chunkZ
	) {
		// the chunk coordinates are given chunk (x, z); to find actual coordinates, multiply each by 16
		// The X of chunk will be Floor( X coordinate / 16 ); so xChunk * 16 = xCoordinate
		// The Z of chunk will be Floor( Z coordinate / 16 ); so zChunk * 16 = yCoordinate
		int xCoordinate = chunkX * 16;
		int zCoordinate = chunkZ * 16;
		
		/*
		X: Player's location in blocks East of 0,0 (negative values are to the West)
		Y: Player's (feet) altitude in blocks (63 (62.9) is overworld sea level, 11 (10.9) is overworld lava flood level, 32 (31.9) is nether lava sea).
		Z: Player's location in blocks South of 0,0 (negative values are to the North)
		*/
		
		int maxpossy = blockMinimumYCoordinate + (blockMaximumYCoordinate - 1);
		
		int diffBetweenMinMaxY = blockMaximumYCoordinate - blockMinimumYCoordinate;
		
		for (int numVeins = 0; numVeins < chancetospawn; numVeins++) {
			int posx = xCoordinate + random.nextInt(blockMaximumXCoordinate);
			int posy = blockMinimumYCoordinate + random.nextInt(diffBetweenMinMaxY);
			int posz = zCoordinate + random.nextInt(blockMaximumZCoordinate);
			
			WorldGenMinable gen = new WorldGenMinable(block.getDefaultState(), maxVeinSize);
//			WorldGenBlockBlob worldGenBlockBlob = new WorldGenBlockBlob(this.block, 0);
			gen.generate(world, random, new BlockPos(posx, posy, posz));
		}		
	}
	
}