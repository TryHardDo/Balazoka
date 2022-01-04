package dev.tf2levi.balazo;

import dev.tf2levi.balazo.inventories.FuelInterface;
import dev.tf2levi.balazo.itemstacks.HarvesterItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ListenerClass implements Listener {
    @EventHandler
    public void onHarvesterDeploy(@NotNull BlockPlaceEvent e) {
        if (e.getBlock().getType() != Material.BLAST_FURNACE) return;

        if (e.getItemInHand().getItemMeta() == null) return;

        ItemMeta placedBlockMeta = e.getItemInHand().getItemMeta();

        if (!placedBlockMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) || placedBlockMeta.getLore() == null) return;

        UUID harvesterID = UUID.fromString(placedBlockMeta.getLore().get(placedBlockMeta.getLore().size() - 1).replace("§7", ""));

        if (!Balazoka.getHarvesters().containsKey(harvesterID)) return;

        Harvester harvester = Balazoka.getHarvesters().get(harvesterID);
        Player placer = e.getPlayer();

        if (!harvester.getOwner().getUniqueId().equals(placer.getUniqueId())) {
            placer.sendMessage("§cNem te vagy ennek a kombájnak a tulajdonosa így ezt nem használhatod.");
            e.setCancelled(true);
            return;
        }

        if (harvester.getCurrentPosition() != null) {
            placer.sendMessage("§cIlyen kombájn már le van helyezve ezzel az ID-vel.");
            e.setCancelled(true);
            return;
        }

        harvester.setCurrentPosition(e.getBlock().getLocation());
        Utils.updateCache(harvester);

        placer.sendMessage("§aKombájn letéve. Regisztrálva a rendszerben.... ID:" + harvester.getId());

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getClickedBlock() == null) return;

        if (e.getClickedBlock().getType()!= Material.BLAST_FURNACE) return;

        Location clickLocation = e.getClickedBlock().getLocation();
        Harvester harvester = Utils.searchByLocation(clickLocation);

        if (harvester == null) return;

        Player clicker = e.getPlayer();
        e.setCancelled(true);

        clicker.openInventory(new FuelInterface(harvester).getFuelInterface());

        clicker.sendMessage("§cDEBUG §e>> §aRányomtál egy bálázóra: ID: §e" + harvester.getId());
    }

    @EventHandler
    public void onHarvesterBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() != Material.BLAST_FURNACE) return;

        Location blockLoc = e.getBlock().getLocation();
        Harvester brokenHarvester = null;

        for (Map.Entry<UUID, Harvester> cachePart : Balazoka.getHarvesters().entrySet()) {
            if (blockLoc.equals(cachePart.getValue().getCurrentPosition())) {
                brokenHarvester = cachePart.getValue();
                break;
            }
        }

        if (brokenHarvester == null) return;

        brokenHarvester.setCurrentPosition(null);

        // Cache-be frissíteni kell az események miatt
        Utils.updateCache(brokenHarvester);

        e.setDropItems(false);
        Objects.requireNonNull(blockLoc.getWorld()).dropItem(blockLoc, new HarvesterItem(brokenHarvester).getItem());
    }
}
