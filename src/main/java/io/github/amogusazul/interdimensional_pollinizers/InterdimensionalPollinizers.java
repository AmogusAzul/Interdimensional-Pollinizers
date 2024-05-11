package io.github.amogusazul.interdimensional_pollinizers;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterdimensionalPollinizers implements ModInitializer {
	public static final String MOD_ID = "interdimensional_Pollinizers";
    public static final Logger LOGGER = LoggerFactory.getLogger("Interdimensional Pollinizers");

    @Override
    public void onInitialize(ModContainer mod) {
        LOGGER.info("Hello Quilt world from {}! Stay fresh!", mod.metadata().name());
    }
}
