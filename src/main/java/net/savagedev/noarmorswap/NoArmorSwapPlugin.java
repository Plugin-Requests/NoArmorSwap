package net.savagedev.noarmorswap;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class NoArmorSwapPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void on(PlayerInteractEvent event) {
        final Action action = event.getAction();

        if (action == Action.PHYSICAL || action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) {
            return;
        }

        final Block clickedBlock = event.getClickedBlock();

        if (clickedBlock != null && clickedBlock.getType().isInteractable()) {
            return;
        }

        final ItemStack itemInHand = event.getItem();

        if (itemInHand == null) {
            return;
        }

        final ItemStack[] armorContents = event.getPlayer().getInventory().getArmorContents();
        final Material itemInHandMaterial = itemInHand.getType();

        if (itemInHandMaterial == Material.ELYTRA && armorContents[2] != null) {
            event.setCancelled(true);
            return;
        }

        if (EnchantmentTarget.ARMOR_HEAD.includes(itemInHandMaterial) && armorContents[3] != null) {
            event.setCancelled(true);
            return;
        }

        if (EnchantmentTarget.ARMOR_TORSO.includes(itemInHandMaterial) && armorContents[2] != null) {
            event.setCancelled(true);
            return;
        }

        if (EnchantmentTarget.ARMOR_LEGS.includes(itemInHandMaterial) && armorContents[1] != null) {
            event.setCancelled(true);
            return;
        }

        if (EnchantmentTarget.ARMOR_FEET.includes(itemInHandMaterial) && armorContents[0] != null) {
            event.setCancelled(true);
        }
    }
}
