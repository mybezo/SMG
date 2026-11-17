package me.waleks.simplematerialgenerators.guide;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

/**
 * A menu that lists other item groups, for example "Compressed Items".
 * It is not listed in the main menu of the guide, it is opened from a button instead.
 */
public class GroupListItemGroup extends FlexItemGroup {

    private final List<ItemGroup> groups = new ArrayList<>();

    @ParametersAreNonnullByDefault
    public GroupListItemGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    public void addGroup(@Nonnull ItemGroup group) {
        groups.add(group);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        if (mode == SlimefunGuideMode.SURVIVAL_MODE) {
            profile.getGuideHistory().add(this, 1);
        }

        ChestMenu menu = GuideMenus.create(p, profile, mode);
        GuideMenus.addPageButtons(menu, p, 1, 1, page -> {});

        int slot = GuideMenus.FIRST_SLOT;
        for (ItemGroup group : groups) {
            if (group.getItems().isEmpty() || slot > GuideMenus.LAST_SLOT) {
                continue;
            }
            menu.addItem(slot, group.getItem(p));
            menu.addMenuClickHandler(slot, (pl, s, item, action) -> {
                SlimefunGuide.openItemGroup(profile, group, mode, 1);
                return false;
            });
            slot++;
        }

        menu.open(p);
    }
}
