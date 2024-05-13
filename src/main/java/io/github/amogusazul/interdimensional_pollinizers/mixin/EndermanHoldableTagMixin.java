package io.github.amogusazul.interdimensional_pollinizers.mixin;


import io.github.amogusazul.interdimensional_pollinizers.InterdimensionalPollinizersConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PickUpBlockGoal")
public class EndermanHoldableTagMixin {

	@Redirect(
		method = "tick",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/tag/TagKey;)Z")
	)
	private boolean BlockstateInConfig(BlockState instance, TagKey<Block> tagKey) {
		return InterdimensionalPollinizersConfig.flattened_seeds.contains(
			Registry.BLOCK.getId(instance.getBlock())
				.toString());
	}


}
