package io.github.amogusazul.interdimensional_pollinizers.mixin;

import io.github.amogusazul.interdimensional_pollinizers.InterdimensionalPollinizersConfig;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public abstract class EndermanPollinizerMixin extends MobEntityMixin {

	@Shadow
	public abstract void setCarriedBlock(@Nullable BlockState state);

	@Override
	void stub(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {

		InterdimensionalPollinizersConfig config = InterdimensionalPollinizersConfig.INSTANCE;
		final RandomGenerator random = world.getRandom();
		final int seed = random.range(0, config.seeds.value().size() - 1);

		if (random.range(1, 100) <= config.pollinizers_percentage.value()) { //50% of spawning an item holding enderman
			this.setCarriedBlock(Registry.BLOCK.get(new Identifier(config.seeds.value().get(seed))).getDefaultState());
		}

	}
}
