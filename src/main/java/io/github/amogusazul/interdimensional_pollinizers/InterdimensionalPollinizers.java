package io.github.amogusazul.interdimensional_pollinizers;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents.READY;

public class  InterdimensionalPollinizers implements ModInitializer {
	public static final String MOD_ID = "interdimensional_pollinizers";
    public static final Logger LOGGER = LoggerFactory.getLogger("Interdimensional Pollinizers");

    @Override
    public void onInitialize(ModContainer mod) {
        LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
		READY.register(InterdimensionalPollinizersConfig.INSTANCE::flattenTags);

    }
}
