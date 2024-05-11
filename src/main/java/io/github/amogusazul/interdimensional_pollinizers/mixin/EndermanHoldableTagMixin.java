package io.github.amogusazul.interdimensional_pollinizers.mixin;

import io.github.amogusazul.interdimensional_pollinizers.InterdimensionalPollinizers;
import net.minecraft.block.Block;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockTags.class)
public abstract class EndermanHoldableTagMixin {

	@Shadow
	private static TagKey<Block> register(String id) {
		return TagKey.of(Registry.BLOCK_KEY, new Identifier(InterdimensionalPollinizers.MOD_ID, id));
	}

	@Unique
	private static final TagKey<Block> ENDERMAN_HOLDABLE = register("enderman_holdable");

}
