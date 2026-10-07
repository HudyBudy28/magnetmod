package com.hz28.magnetmod;

import java.util.function.Function;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final String MOD_ID = "magnetmod";

    public static final Item MAGNETIZED_IRON =
            register("magnetized_iron", Item::new, new Item.Properties());

    public static final Item INVENTORY_MAGNET =
            register("inventory_magnet", props -> new InventoryMagnetItem(props, 6.0), new Item.Properties().stacksTo(1));

    public static final Item ADVANCED_INVENTORY_MAGNET =
            register("advanced_inventory_magnet", props -> new InventoryMagnetItem(props, 12.0), new Item.Properties().stacksTo(1));

    private static <T extends Item> T register(String name, Function<Item.Properties, T> factory, Item.Properties props) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, name));
        T item = factory.apply(props.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);
        return item;
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(output -> output.accept(MAGNETIZED_IRON));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(output -> {
                    output.accept(INVENTORY_MAGNET);
                    output.accept(ADVANCED_INVENTORY_MAGNET);
                });
    }
}