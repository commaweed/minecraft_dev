package com.example.examplemod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class TravisBlockGenerator  {//implements IWorldGenerator

//	@Override
//	public void generate(
//			Random random,
//			int chunkX, 
//			int chunkZ, 
//			World world,
//			IChunkProvider chunkGenerator, 
//			IChunkProvider chunkProvider
//	) {
//		switch(world.provider.getDimensionId()) {
//			case -1:
//				generateNether(world, random, chunkX * 16, chunkZ * 16);
//				break;
//			case 0:
//				generateSurface(world, random, chunkX * 16, chunkZ * 16);
//				break;
//			case 1:
//				generateEnd(world, random, chunkX * 16, chunkZ * 16);
//				break;
//		}
//
//		
//	}
//	
//	private void generateSurface(World world, Random random, int x, int z) {
//		addOreSpawn(ExampleMod.TRAVIS_BLOCK, 0, world, random, x, z, 16, 16, 5 + random.nextInt(5), 10, 50, 80);
//	}
//	
//	private void generateNether(World world, Random random, int x, int z) {
//		addOreSpawn(ExampleMod.TRAVIS_BLOCK, 0, world, random, x, z, 16, 16, 5 + random.nextInt(5), 10, 50, 80);
//	}
//	
//	private void generateEnd(World world, Random random, int x, int z) {
//		addOreSpawn(ExampleMod.TRAVIS_BLOCK, 0, world, random, x, z, 16, 16, 5 + random.nextInt(5), 10, 50, 80);
//	}
//	
//	private void addOreSpawn(
//			Block block,
//			int metadata,
//			World world,
//			Random random,
//			int blockxpos,
//			int blockzpos,
//			int maxx,
//			int maxz,
//			int maxveinsize,
//			int chancetospawn,
//			int miny,
//			int maxy
//	) {
//		int maxpossy = miny + (maxy - 1);
//		
//		assert maxy > miny : "maxy must be > miny";
//		assert maxx > 0 && maxx <= 16 : "maxx must be between [0 and 16)";
//		assert miny > 0 : "min y must be greater than 0";
//		assert maxy < 256 && maxy > 0 : "maxy must be between [0 and 256]";
//		assert maxz > 0 && maxz <=16 : "maxz must be between [0 and 16)";
//		
//		int diffBetweenMinMaxY = maxy = miny;
//		for (int x = 0; x < chancetospawn; x++) {
//			int posx = blockxpos + random.nextInt(maxx);
//			int posy = miny + random.nextInt(diffBetweenMinMaxY);
//			int posz = blockzpos + random.nextInt(maxz);
//			
//			WorldGenBlockBlob worldGenBlockBlob = new WorldGenBlockBlob(block, metadata);
//			worldGenBlockBlob.generate(world, random, new BlockPos(posx, posy, posz));
//		}
//		
//	}
	
	
}