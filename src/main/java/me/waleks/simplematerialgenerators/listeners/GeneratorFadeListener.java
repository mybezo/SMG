package me.waleks.simplematerialgenerators.listeners;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import me.waleks.simplematerialgenerators.SimpleMaterialGenerators;
import me.waleks.simplematerialgenerators.items.MaterialGenerator;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

import javax.annotation.Nonnull;

/**
 * Stops generator blocks from melting or fading, for example the Ice Generator
 * turning into water or the Snow Block Generator disappearing next to a light source.
 */
public class GeneratorFadeListener implements Listener {

    public GeneratorFadeListener(@Nonnull SimpleMaterialGenerators plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onFade(@Nonnull BlockFadeEvent e) {
        Location l = e.getBlock().getLocation();

        if (StorageCacheUtils.hasBlock(l) && StorageCacheUtils.getSfItem(l) instanceof MaterialGenerator) {
            e.setCancelled(true);
        }
    }
}
