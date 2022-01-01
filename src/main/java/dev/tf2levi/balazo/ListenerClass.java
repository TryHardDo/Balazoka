package dev.tf2levi.balazo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ListenerClass implements Listener {
    @EventHandler
    public void onHarvesterDeploy(@NotNull BlockPlaceEvent e) {
        if (e.getItemInHand().getItemMeta() == null) {
            return;
        }

        ItemMeta placedBlockMeta = e.getItemInHand().getItemMeta();

        if (!placedBlockMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) || placedBlockMeta.getLore() == null) {
            return;
        }

        UUID harvesterID = UUID.fromString(placedBlockMeta.getLore().get(placedBlockMeta.getLore().size() + 1).replace("§7", ""));

        if (!Balazoka.getHarvesters().containsKey(harvesterID)) {
            return;
        }

        Harvester harvester = Balazoka.getHarvesters().get(harvesterID);

        // Todo: Innen folytatni
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

    }
}
