package io.github.amogusazul.interdimensional_pollinizers;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.block.Block;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Holder;
import net.minecraft.util.HolderSet;
import net.minecraft.util.Identifier;
import org.quiltmc.config.api.ReflectiveConfig;
import org.quiltmc.config.api.annotations.Comment;
import org.quiltmc.config.api.annotations.IntegerRange;
import org.quiltmc.config.api.annotations.SerializedName;
import org.quiltmc.config.api.values.TrackedValue;
import org.quiltmc.config.api.values.ValueList;
import org.quiltmc.loader.api.config.v2.QuiltConfig;

import net.minecraft.util.registry.Registry;

public class InterdimensionalPollinizersConfig extends ReflectiveConfig {
	public static final InterdimensionalPollinizersConfig INSTANCE = QuiltConfig.create(
		InterdimensionalPollinizers.MOD_ID, InterdimensionalPollinizers.MOD_ID, InterdimensionalPollinizersConfig.class);

	@Comment("choose how many of the endermen would have \"seeds\"")
	@SerializedName("pollinizers_percentage")
	@IntegerRange(min=0, max=100)
	public final TrackedValue<Integer> pollinizers_percentage = this.value(50);

	@Comment("Which seeds would pollinizers will spawn with")
	@SerializedName("seeds")

	public final TrackedValue<ValueList<String>> seeds = this.list("",
		"minecraft:grass",
		"minecraft:fern",
		"minecraft:sea_pickle",
		"minecraft:nether_sprouts",
		"minecraft:weeping_vines",
		"minecraft:twisting_vines",
		"minecraft:sugar_cane",
		"minecraft:kelp",
		"minecraft:moss_block",
		"minecraft:small_dripleaf",
		"minecraft:bamboo_sapling",
		"minecraft:glow_lichen",
		"minecraft:lily_pad",
		"minecraft:wheat",
		"minecraft:melon",
		"minecraft:pumpkin",
		"minecraft:nether_wart"
	);

	@Comment("Here you can add any tag to the pollinizers' block pool")
	@SerializedName("tag_seeds")
	public final TrackedValue<ValueList<String>> tag_seeds = this.list("",
		"minecraft:crops",
		"minecraft:enderman_holdable",
		"minecraft:flowers",
		"minecraft:saplings",
		"minecraft:underwater_bonemeals"
	);

	public static ObjectArraySet<String> flattened_seeds = new ObjectArraySet<>();

	public void flattenTags(MinecraftServer server){
		InterdimensionalPollinizers.LOGGER.debug("runs");

		flattened_seeds = new ObjectArraySet<>();
		flattened_seeds.addAll(INSTANCE.seeds.value());

		for (String tag : INSTANCE.tag_seeds.value()){

			TagKey<Block> tagKey = TagKey.of(Registry.BLOCK_KEY, new Identifier(tag));
			 HolderSet.NamedSet<Block> tags_blocks = Registry.BLOCK.getOrCreateTag(tagKey);

			 for (Holder<Block> block : tags_blocks){
				 flattened_seeds.add(Registry.BLOCK.getId(block.value()).toString());
			 }
		}
	}

}
