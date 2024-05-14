package io.github.amogusazul.interdimensional_pollinizers.mixin;

import io.github.amogusazul.interdimensional_pollinizers.InterdimensionalPollinizers;
import io.github.amogusazul.interdimensional_pollinizers.InterdimensionalPollinizersTags;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.*;

import net.minecraft.client.render.entity.feature.EndermanBlockFeatureRenderer;

@Mixin(EndermanBlockFeatureRenderer.class)
public class EndermanBlockFeatureRendererMixin {

	@Final
	@Shadow
	private BlockRenderManager blockRenderManager;

	/**
	 * @author
	 * AmogusAzul
	 * @reason
	 * Visual Overhaul for some blocks
	 */
	@Overwrite
	public void render(
		MatrixStack matrixStack,
		VertexConsumerProvider vertexConsumerProvider,
		int i,
		EndermanEntity endermanEntity,
		float f,
		float g,
		float h,
		float j,
		float k,
		float l
	) {

		BlockState blockState = endermanEntity.getCarriedBlock();
		if (blockState != null) {

			Block block = blockState.getBlock();

			if (block instanceof TallPlantBlock){

				plotBlock(i, matrixStack, vertexConsumerProvider,
					blockState.getBlock().getDefaultState().with(Properties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER),
					true, true, false, 0, 0.4f, 0, 0, 0);

				plotBlock(i, matrixStack, vertexConsumerProvider,
					blockState.getBlock().getDefaultState().with(Properties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER),
					true, false, false, 0, 0.4f, 0, 0, 0);

			} else if (block instanceof FlowerBlock || blockState.isIn(InterdimensionalPollinizersTags.FUNGI)
				|| block == Blocks.BAMBOO || (blockState.isIn(BlockTags.SAPLINGS) && !(block instanceof AzaleaBlock))
				|| block == Blocks.FERN) {

				plotBlock(i, matrixStack, vertexConsumerProvider, blockState, true, false, false, 55, 0, 0.2f, -0.3f, -0.5f);

			} else if(block == Blocks.SEA_PICKLE || block == Blocks.LILY_PAD){

				plotBlock(i, matrixStack, vertexConsumerProvider, blockState, true, false, false, 20, 0, 0, 0, 0);

			} else if (block instanceof CoralParentBlock && block.getDefaultState().getProperties().contains(Properties.FACING)) {

				InterdimensionalPollinizers.LOGGER.debug("CORAL");

				plotBlock(i, matrixStack, vertexConsumerProvider, blockState, true, false, false, 20, 0, 0, 0, -1);

			} else if (block instanceof CoralParentBlock){

				plotBlock(i, matrixStack, vertexConsumerProvider, blockState, true, false, false, 55, 0, 0, 0, -0.3f);

			} else if (block == Blocks.TWISTING_VINES || block == Blocks.WEEPING_VINES
				|| block == Blocks.CRIMSON_ROOTS || block == Blocks.WARPED_ROOTS || block == Blocks.NETHER_SPROUTS
				|| block == Blocks.GRASS || block == Blocks.KELP || block == Blocks.SEAGRASS) {

				plotBlock(i, matrixStack, vertexConsumerProvider, blockState, true, false, false, 20, 0, 0, 0, 0);

			} else if (blockState.isIn(BlockTags.CROPS) || block == Blocks.NETHER_WART) {

				plotBlock(i, matrixStack, vertexConsumerProvider, blockState, true, false, true, 0, 0, 0, 0, 0);

			} else {
				plotBlock(i, matrixStack, vertexConsumerProvider, blockState, false, false, true, 20, 0, 0,0, 0);
			}

		}


	}

	@Unique
	private void plotBlock(
		int i,
		MatrixStack matrixStack,
		VertexConsumerProvider vertexConsumerProvider,
		BlockState blockState,
		boolean Bigger,
		boolean blockOffset,
		boolean diagonal,
		float tilt,
		float zOffset,
		float diagonalOffset,
		float xOffset,
		float yOffset
	){
		float MoveX1 = 0f;
		float MoveY1 = 0.6875f;
		float MoveZ1 = -0.75f;
		float RotationY1 = 45f;
		float RotationY2 = 90f;
		float MoveX2 = 0.25f;
		float MoveY2 = 0.1875f;
		float MoveZ2 = 0.25f;
		float scale = 0.5f;

		if (Bigger){
			scale = 1f;
			MoveX1 += 0.25f;
		}
		if (blockOffset){
			MoveY2 -= scale;
		}

		if (!diagonal){
			RotationY1 = 0f;
		}

		matrixStack.push();
		matrixStack.translate(MoveX1 + xOffset, MoveY1 + yOffset, MoveZ1 + zOffset);
		matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(tilt));
		matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(RotationY1));
		matrixStack.translate(MoveX2, MoveY2, MoveZ2 + diagonalOffset);
		matrixStack.scale(-scale, -scale, scale);
		matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(RotationY2));
		this.blockRenderManager.renderBlockAsEntity(blockState, matrixStack, vertexConsumerProvider, i, OverlayTexture.DEFAULT_UV);
		matrixStack.pop();
	}
}
