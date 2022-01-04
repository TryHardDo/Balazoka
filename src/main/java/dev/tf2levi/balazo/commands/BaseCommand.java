package dev.tf2levi.balazo.commands;

import dev.tf2levi.balazo.Balazoka;
import dev.tf2levi.balazo.Harvester;
import dev.tf2levi.balazo.itemstacks.HarvesterItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

public class BaseCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String @NotNull [] args) {
        if (args.length == 0) {
            sender.sendMessage("§cHelp lista ide.");
        } else {
            if (!(sender instanceof ConsoleCommandSender)) {
                Player p = ((Player) sender);

                if (args[0].equalsIgnoreCase("get")) {
                    // NULL a hely ha kézbe kerül. Letétel után ez felülíródik.
                    Harvester harvester = new Harvester(p, null);
                    HarvesterItem item = new HarvesterItem(harvester);

                    Balazoka.getHarvesters().put(harvester.getId(), harvester);

                    p.getInventory().addItem(item.getItem());
                    p.sendMessage("§aSikeres igénylés!");
                } else if (args[0].equalsIgnoreCase("test")) {
                    p.sendMessage("§c" + InventoryType.STONECUTTER.isCreatable());
                }
            }
        }
        return true;
    }
}
