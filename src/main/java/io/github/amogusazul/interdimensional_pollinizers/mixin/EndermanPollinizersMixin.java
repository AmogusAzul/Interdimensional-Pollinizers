package io.github.amogusazul.interdimensional_pollinizers.mixin;

import io.github.amogusazul.interdimensional_pollinizers.InterdimensionalPollinizersConfig;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public abstract class EndermanPollinizersMixin extends MobEntityMixin {

	@Shadow
	public abstract void setCarriedBlock(@Nullable BlockState state);

	@Override
	void stub(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
		final RandomGenerator random = world.getRandom();
		final int seed = random.range(0, InterdimensionalPollinizersConfig.flattened_seeds.size() - 1);

		if (random.range(1, 100) <= InterdimensionalPollinizersConfig.INSTANCE.pollinizers_percentage.value()) { // percentage of spawning a block holding enderman
			int counter = 0;
			Block block = Blocks.AIR;
			//accessing a set's value
			for (String block_id : InterdimensionalPollinizersConfig.flattened_seeds){
				if (counter == seed){
					block = Registry.BLOCK.get(new Identifier(block_id));
					break;
				}
				counter++;
			}
			this.setCarriedBlock(getBlockstate(block));
		}

	}

	@Unique
	BlockState getBlockstate(Block block){
		if (block instanceof TallPlantBlock){
			return block.getDefaultState().with(Properties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER);
		}
		if (block.getDefaultState().isIn(BlockTags.CROPS)){
			return block.getDefaultState().with(Properties.AGE_7, 7);
		}
		if (block == Blocks.NETHER_WART){
			return block.getDefaultState().with(Properties.AGE_3, 3);
		}
		if (block instanceof CoralParentBlock){
			return block.getDefaultState().with(Properties.WATERLOGGED, false);
		}
		return block.getDefaultState();

	}
}
