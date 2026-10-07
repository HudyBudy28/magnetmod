package com.hz28.magnetmod;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

public class InventoryMagnetItem extends Item {
    private final double radius;

    public InventoryMagnetItem(Properties properties, double radius) {
        super(properties);
        this.radius = radius;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity owner, EquipmentSlot slot) {
        if (slot != EquipmentSlot.OFFHAND) return;
        if (!(owner instanceof Player player) || player.isSpectator()) return;

        AABB box = player.getBoundingBox().inflate(radius);
        for (ItemEntity item : level.getEntitiesOfClass(ItemEntity.class, box)) {
            if (item.isAlive() && item.distanceToSqr(player) <= radius * radius) {
                item.playerTouch(player);
            }
        }
    }
}