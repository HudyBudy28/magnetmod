package com.hz28.magnetmod;

import com.hz28.magnetmod.mixin.GossipTypeAccessor;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.gossip.GossipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagnetMod implements ModInitializer {
	public static final String MOD_ID = "magnetmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		((GossipTypeAccessor) (Object) GossipType.MAJOR_POSITIVE).magnetmod$setMax(100);

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}