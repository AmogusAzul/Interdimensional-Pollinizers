package io.github.amogusazul.interdimensional_pollinizers;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static io.github.amogusazul.interdimensional_pollinizers.InterdimensionalPollinizers.MOD_ID;


public class InterdimensionalPollinizersTags {
	public static final TagKey<Block> FUNGI = TagKey.of(Registry.BLOCK_KEY, new Identifier(MOD_ID, "fungi"));

}
