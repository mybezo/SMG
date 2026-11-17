package me.waleks.simplematerialgenerators.guide;

import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.GuideHistory;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideImplementation;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.guide.SurvivalSlimefunGuide;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Helpers to draw this addon's own guide menus the same way as the Slimefun guide.
 */
final class GuideMenus {

    static final int FIRST_SLOT = 9;
    static final int LAST_SLOT = 44;

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

        if (mode == SlimefunGuideMode.SURVIVAL_MODE && history.size() > 1) {
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

    @Nonnull
    private static SlimefunGuideImplementation getGuide(@Nonnull SlimefunGuideMode mode) {
        return Slimefun.getRegistry().getSlimefunGuide(mode);
    }
}
