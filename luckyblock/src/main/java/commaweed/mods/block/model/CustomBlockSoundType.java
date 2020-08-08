package commaweed.mods.block.model;

import net.minecraft.block.Block;

/**
 * Custom Block Sound Type that can be used to override a blocks step sound.  It
 * will include 3 sounds, break-sound, step-sound, and place-block-sound.
 */
public class CustomBlockSoundType extends Block.SoundType {

	private final String breakSoundName;
	private final String stepSoundName;
	private final String placeSoundName;
	
	/**
	 * Initialize with the give three sounds.
	 * @param breakSoundName The string value of the sound to make when the block breaks.
	 * @param stepSoundName The string value of the sound to make when stepping on the block.
	 * @param placeSoundName The string value of the sound to make when placing on the block.
	 * @param volume The volume of the sound.
	 * @param frequency The frequency of the sound.
	 */
	public CustomBlockSoundType(
		String breakSoundName,
		String stepSoundName,
		String placeSoundName,
		float volume, 
		float frequency
	) {
		super(stepSoundName, volume, frequency);
		
		this.breakSoundName = breakSoundName;
		this.stepSoundName = stepSoundName;
		this.placeSoundName = placeSoundName;
	}

	/**
	 * Override to return the given value.
	 */
	@Override
	public String getBreakSound() {
		return this.breakSoundName;
	}

	/**
	 * Override to return the given value.
	 */
	@Override
	public String getStepSound() {
		return this.stepSoundName;
	}

	/**
	 * Override to return the given value.
	 */
	@Override
	public String getPlaceSound() {
		return this.placeSoundName;
	}
}