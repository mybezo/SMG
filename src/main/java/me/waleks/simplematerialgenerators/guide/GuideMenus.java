package me.waleks.simplematerialgenerators.guide;

import city.norain.slimefun4.VaultIntegration;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.guide.GuideHistory;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideImplementation;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.guide.SurvivalSlimefunGuide;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * Helpers to draw this addon's own guide menus the same way as the Slimefun guide.
 */
final class GuideMenus {

    static final int FIRST_SLOT = 9;
    static final int LAST_SLOT = 44;
    static final int PREVIOUS_SLOT = 46;
    static final int NEXT_SLOT = 52;

    private GuideMenus() {}

    /**
     * Creates a guide menu with the header, footer and back button.
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    static ChestMenu create(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        ChestMenu menu = new ChestMenu(Slimefun.getLocalization().getMessage(p, "guide.title.main"));
        menu.setEmptySlotsClickable(false);
        menu.addMenuOpeningHandler(SoundEffect.GUIDE_BUTTON_CLICK_SOUND::playFor);

        SlimefunGuideImplementation guide = getGuide(mode);
        if (guide instanceof SurvivalSlimefunGuide) {
            ((SurvivalSlimefunGuide) guide).createHeader(p, profile, menu);
        } else {
            for (int i = 0; i < 9; i++) {
                menu.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
            }
            for (int i = 45; i < 54; i++) {
                menu.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
            }
        }

        addBackButton(menu, p, profile, mode);
        return menu;
    }

    @ParametersAreNonnullByDefault
    private static void addBackButton(ChestMenu menu, Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        GuideHistory history = profile.getGuideHistory();
        boolean survival = mode == SlimefunGuideMode.SURVIVAL_MODE;

        if (survival && history.size() > 1) {
            menu.addItem(1, ChestMenuUtils.getBackButton(p, "", "&fLeft Click: &7Go back", "&fShift + Left Click: &7Go to the main menu"));
            menu.addMenuClickHandler(1, (pl, slot, item, action) -> {
                if (action.isShiftClicked()) {
                    SlimefunGuide.openMainMenu(profile, mode, history.getMainMenuPage());
                } else {
                    history.goBack(getGuide(mode));
                }
                return false;
            });
        } else {
            menu.addItem(1, ChestMenuUtils.getBackButton(p, "", ChatColor.GRAY + Slimefun.getLocalization().getMessage(p, "guide.back.guide")));
            menu.addMenuClickHandler(1, (pl, slot, item, action) -> {
                SlimefunGuide.openMainMenu(profile, mode, history.getMainMenuPage());
                return false;
            });
        }
    }

    /**
     * Adds the previous and next page buttons.
     */
    @ParametersAreNonnullByDefault
    static void addPageButtons(ChestMenu menu, Player p, int page, int pages, PageOpener opener) {
        menu.addItem(PREVIOUS_SLOT, ChestMenuUtils.getPreviousButton(p, page, pages));
        menu.addMenuClickHandler(PREVIOUS_SLOT, (pl, slot, item, action) -> {
            if (page > 1) {
                opener.open(page - 1);
            }
            return false;
        });

        menu.addItem(NEXT_SLOT, ChestMenuUtils.getNextButton(p, page, pages));
        menu.addMenuClickHandler(NEXT_SLOT, (pl, slot, item, action) -> {
            if (page < pages) {
                opener.open(page + 1);
            }
            return false;
        });
    }

    /**
     * Shows a {@link SlimefunItem} in the menu, like the Slimefun guide does:
     * locked by permission or research, or clickable to view (or get it in cheat mode).
     */
    @ParametersAreNonnullByDefault
    static void addItem(ChestMenu menu, int slot, Player p, PlayerProfile profile, SlimefunGuideMode mode,
                        ItemGroup group, int page, SlimefunItem sfItem) {
        boolean survival = mode == SlimefunGuideMode.SURVIVAL_MODE;
        Research research = sfItem.getResearch();

        if (survival && !Slimefun.getPermissionsService().hasPermission(p, sfItem)) {
            List<String> message = Slimefun.getPermissionsService().getLore(sfItem);
            menu.addItem(slot, new CustomItemStack(ChestMenuUtils.getNoPermissionItem(), sfItem.getItemName(), message.toArray(new String[0])));
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        } else if (survival && research != null && !profile.hasUnlocked(research)) {
            String cost = VaultIntegration.isEnabled()
                ? String.format("%.2f", research.getCurrencyCost()) + " money"
                : research.getLevelCost() + " levels";

            menu.addItem(slot, new CustomItemStack(
                ChestMenuUtils.getNoPermissionItem(),
                "&f" + ChatColor.stripColor(sfItem.getItemName()),
                "&7" + sfItem.getId(),
                "&4&l" + Slimefun.getLocalization().getMessage(p, "guide.locked"),
                "",
                "&a> Click to unlock",
                "",
                "&7Cost: &b" + cost
            ));
            menu.addMenuClickHandler(slot, (pl, s, item, action) -> {
                research.unlockFromGuide(getGuide(mode), pl, profile, sfItem, group, page);
                return false;
            });
        } else {
            menu.addItem(slot, sfItem.getItem());
            menu.addMenuClickHandler(slot, (pl, s, item, action) -> {
                if (survival) {
                    SlimefunGuide.displayItem(profile, sfItem, true);
                } else if (pl.hasPermission("slimefun.cheat.items")) {
                    ItemStack clone = sfItem.getItem().clone();
                    if (action.isShiftClicked()) {
                        clone.setAmount(clone.getMaxStackSize());
                    }
                    pl.getInventory().addItem(clone);
                } else {
                    Slimefun.getLocalization().sendMessage(pl, "messages.no-permission", true);
                }
                return false;
            });
        }
    }

    @Nonnull
    static SlimefunGuideImplementation getGuide(@Nonnull SlimefunGuideMode mode) {
        return Slimefun.getRegistry().getSlimefunGuide(mode);
    }

    @FunctionalInterface
    interface PageOpener {
        void open(int page);
    }
}
