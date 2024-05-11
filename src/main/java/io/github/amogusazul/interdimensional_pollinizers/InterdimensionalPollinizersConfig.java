package io.github.amogusazul.interdimensional_pollinizers;

import org.quiltmc.config.api.ReflectiveConfig;
import org.quiltmc.config.api.annotations.Comment;
import org.quiltmc.config.api.annotations.IntegerRange;
import org.quiltmc.config.api.annotations.SerializedName;
import org.quiltmc.config.api.values.TrackedValue;
import org.quiltmc.config.api.values.ValueList;
import org.quiltmc.loader.api.config.v2.QuiltConfig;

public class InterdimensionalPollinizersConfig extends ReflectiveConfig {
	public static final InterdimensionalPollinizersConfig INSTANCE = QuiltConfig.create(
		InterdimensionalPollinizers.MOD_ID, InterdimensionalPollinizers.MOD_ID, InterdimensionalPollinizersConfig.class);

	@Comment("choose how many of the endermen would have \"seeds\"")
	@SerializedName("pollinizers_percentage")
	@IntegerRange(min=0, max=100)
	public final TrackedValue<Integer> pollinizers_percentage = this.value(50);

	@Comment("Which seeds would pollinizers spawn with")
	@SerializedName("seeds")

	public final TrackedValue<ValueList<String>> seeds = this.list("",
		"minecraft:oak_sapling",
		"minecraft:spruce_sapling",
		"minecraft:birch_sapling",
		"minecraft:jungle_sapling",
		"minecraft:acacia_sapling",
		"minecraft:dark_oak_sapling",
		"minecraft:mangrove_propagule",
		"minecraft:grass",
		"minecraft:fern",
		"minecraft:azalea",
		"minecraft:flowering_azalea",
		"minecraft:seagrass",
		"minecraft:sea_pickle",
		"minecraft:dandelion",
		"minecraft:poppy",
		"minecraft:blue_orchid",
		"minecraft:allium",
		"minecraft:azure_bluet",
		"minecraft:red_tulip",
		"minecraft:orange_tulip",
		"minecraft:white_tulip",
		"minecraft:pink_tulip",
		"minecraft:oxeye_daisy",
		"minecraft:cornflower",
		"minecraft:lily_of_the_valley",
		"minecraft:spore_blossom",
		"minecraft:brown_mushroom",
		"minecraft:red_mushroom",
		"minecraft:crimson_fungus",
		"minecraft:warped_fungus",
		"minecraft:crimson_roots",
		"minecraft:warped_roots",
		"minecraft:nether_sprouts",
		"minecraft:weeping_vines",
		"minecraft:twisting_vines",
		"minecraft:sugar_cane",
		"minecraft:kelp",
		"minecraft:moss_carpet",
		"minecraft:moss_block",
		"minecraft:hanging_roots",
		"minecraft:big_dripleaf",
		"minecraft:small_dripleaf",
		"minecraft:bamboo",
		"minecraft:cactus",
		"minecraft:glow_lichen",
		"minecraft:lily_pad",
		"minecraft:sunflower",
		"minecraft:lilac",
		"minecraft:rose_bush",
		"minecraft:peony",
		"minecraft:tall_grass",
		"minecraft:large_fern",
		"minecraft:wheat",
		"minecraft:melon",
		"minecraft:pumpkin",
		"minecraft:nether_wart",
		"minecraft:chorus_fruit",
		"minecraft:beetroot",
		"minecraft:carrot",
		"minecraft:potato");

}
