package daybreak.abilitywar.game.manager.gui;

import com.google.common.collect.ImmutableMap;
import daybreak.abilitywar.ability.AbilityFactory;
import daybreak.abilitywar.ability.AbilityFactory.AbilityRegistration;
import daybreak.abilitywar.ability.AbilityFactory.AbilityRegistration.Flag;
import daybreak.abilitywar.ability.AbilityManifest;
import daybreak.abilitywar.ability.AbilityManifest.Rank;
import daybreak.abilitywar.ability.AbilityManifest.Species;
import daybreak.abilitywar.config.Configuration;
import daybreak.abilitywar.config.Configuration.Settings;
import daybreak.abilitywar.game.manager.AbilityList;
import daybreak.abilitywar.utils.base.Messager;
import daybreak.abilitywar.utils.base.logging.Logger;
import daybreak.abilitywar.utils.base.minecraft.item.builder.ItemBuilder;
import daybreak.abilitywar.utils.library.MaterialX;
import daybreak.abilitywar.utils.library.SoundLib;
import daybreak.abilitywar.utils.library.item.ItemLib;
import daybreak.abilitywar.utils.library.item.ItemLib.ItemColor;
import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.logging.Level;

/**
 * 능력 금지 GUI
 */
public class BlackListGUI implements Listener {

	private static final ItemStack PREVIOUS_PAGE = new ItemBuilder(MaterialX.ARROW)
			.displayName(ChatColor.AQUA + "이전 페이지")
			.build();

	private static final ItemStack NEXT_PAGE = new ItemBuilder(MaterialX.ARROW)
			.displayName(ChatColor.AQUA + "다음 페이지")
			.build();

	private static final Logger logger = Logger.getLogger(BlackListGUI.class.getName());

	private static final ImmutableMap<Rank, MaterialX> materials = ImmutableMap.<Rank, MaterialX>builder()
			.put(Rank.C, MaterialX.YELLOW_WOOL)
			.put(Rank.B, MaterialX.CYAN_WOOL)
			.put(Rank.A, MaterialX.LIME_WOOL)
			.put(Rank.S, MaterialX.PINK_WOOL)
			.put(Rank.L, MaterialX.ORANGE_WOOL)
			.build();

	private final Player p;

	public BlackListGUI(Player p, Plugin Plugin) {
		this.p = p;
		Bukkit.getPluginManager().registerEvents(this, Plugin);
	}

	private int playerPage = 1;

	private Inventory gui;

	public Set<String> getAbilityNames() {
		Set<String> set = new TreeSet<>();
		set.addAll(AbilityList.nameValues());
		set.addAll(Settings.getBlackList());
		return set;
	}

	public void openGUI(int page) {
		Set<String> abilityNames = getAbilityNames();
		int maxPage = ((abilityNames.size() - 1) / 36) + 1;
		if (maxPage < page) page = 1;
		if (page < 1) page = 1;
		gui = Bukkit.createInventory(null, 54, "§c§l✖ §8§l능력 블랙리스트 §c§l✖");
		playerPage = page;
		int count = 0;

		for (final String name : abilityNames) {
			final ItemStack stack;
			final AbilityRegistration registration = AbilityFactory.getByName(name);

			if (Settings.isBlacklisted(name)) {
				stack = ItemLib.WOOL.getItemStack(ItemColor.RED);
				final ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName("§b" + name);
				List<String> lore = new ArrayList<>();
				if (registration != null) {
					final StringJoiner joiner = new StringJoiner(ChatColor.WHITE + ", ");
					if (registration.hasFlag(Flag.ACTIVE_SKILL)) joiner.add(ChatColor.GREEN + "액티브");
					if (registration.hasFlag(Flag.TARGET_SKILL)) joiner.add(ChatColor.GOLD + "타게팅");
					if (registration.hasFlag(Flag.BETA)) joiner.add(ChatColor.DARK_AQUA + "베타");
					final AbilityManifest manifest = registration.getManifest();
					lore.add("§f등급: " + manifest.rank().getRankName());
					lore.add("§f종류: " + manifest.species().getSpeciesName());
					lore.add(joiner.toString());
					lore.add("");
				}
				lore.add("§7이 능력은 능력을 추첨할 때 예외됩니다.");
				lore.add("§b» §f예외 처리를 해제하려면 클릭하세요.");
				meta.setLore(lore);
				stack.setItemMeta(meta);
			} else {
				stack = ItemLib.WOOL.getItemStack(ItemColor.LIME);
				final ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName("§b" + name);
				List<String> lore = new ArrayList<>();
				if (registration != null) {
					final StringJoiner joiner = new StringJoiner(ChatColor.WHITE + ", ");
					if (registration.hasFlag(Flag.ACTIVE_SKILL)) joiner.add(ChatColor.GREEN + "액티브");
					if (registration.hasFlag(Flag.TARGET_SKILL)) joiner.add(ChatColor.GOLD + "타게팅");
					if (registration.hasFlag(Flag.BETA)) joiner.add(ChatColor.DARK_AQUA + "베타");
					final AbilityManifest manifest = registration.getManifest();
					lore.add("§f등급: " + manifest.rank().getRankName());
					lore.add("§f종류: " + manifest.species().getSpeciesName());
					lore.add(joiner.toString());
					lore.add("");
				}
				lore.add("§7이 능력은 능력을 추첨할 때 예외되지 않습니다.");
				lore.add("§b» §f예외 처리를 하려면 클릭하세요.");
				meta.setLore(lore);
				stack.setItemMeta(meta);
			}

			if (count / 36 == page - 1) {
				gui.setItem(count % 36, stack);
			}
			count++;
		}

		int rankIndex = 38;
		final Rank[] ranks = Rank.values();
		ArrayUtils.reverse(ranks);
		for (Rank rank : ranks) {
			final MaterialX material = materials.get(rank);
			if (material == null) continue;
			final ItemStack stack = material.createItem();
			final ItemMeta meta = stack.getItemMeta();
			final String name = rank.getRankName();
			meta.setDisplayName(name);
			meta.setLore(Messager.asList(
					"§f모든 " + name + " §f능력을 예외 처리 하려면 좌클릭,",
					"§f모든 " + name + " §f능력을 예외 처리 해제하려면 우클릭을 해주세요."));
			stack.setItemMeta(meta);
			gui.setItem(rankIndex++, stack);
		}

		if (page > 1) gui.setItem(48, PREVIOUS_PAGE);
		if (page != maxPage) gui.setItem(50, NEXT_PAGE);

		ItemStack stack = new ItemStack(Material.PAPER, 1);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName("§6페이지 §e" + page + " §6/ §e" + maxPage);
		stack.setItemMeta(meta);
		gui.setItem(49, stack);

		p.openInventory(gui);
	}

	@EventHandler
	private void onInventoryClose(InventoryCloseEvent e) {
		if (e.getInventory().equals(this.gui)) {
			HandlerList.unregisterAll(this);
			try {
				Configuration.update();
			} catch (IOException | InvalidConfigurationException e1) {
				logger.log(Level.SEVERE, "콘피그를 업데이트하는 도중 오류가 발생하였습니다.");
			}
		}
	}

	@EventHandler
	private void onQuit(PlayerQuitEvent e) {
		if (e.getPlayer().getUniqueId().equals(p.getUniqueId())) {
			HandlerList.unregisterAll(this);
			try {
				Configuration.update();
			} catch (IOException | InvalidConfigurationException e1) {
				logger.log(Level.SEVERE, "콘피그를 업데이트하는 도중 오류가 발생하였습니다.");
			}
		}
	}

	@EventHandler
	private void onInventoryClick(InventoryClickEvent e) {
		if (e.getInventory().equals(gui)) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "이전 페이지")) {
					openGUI(playerPage - 1);
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "다음 페이지")) {
					openGUI(playerPage + 1);
				} else {
					String itemName = e.getCurrentItem().getItemMeta().getDisplayName();

					if (ItemLib.WOOL.compareType(e.getCurrentItem().getType())) {
						String stripItemName = ChatColor.stripColor(itemName);
						if (MaterialX.RED_WOOL.compare(e.getCurrentItem())) {
							Settings.removeBlacklist(stripItemName);
							SoundLib.ENTITY_EXPERIENCE_ORB_PICKUP.playSound(p);
							openGUI(playerPage);
						} else if (MaterialX.LIME_WOOL.compare(e.getCurrentItem())) {
							Settings.addBlacklist(stripItemName);
							SoundLib.BLOCK_ANVIL_LAND.playSound(p);
							openGUI(playerPage);
						}
					} else {
						for (Rank r : Rank.values()) {
							if (itemName.equals(r.getRankName())) {
								blacklist(e.getClick(), AbilityList.nameValues(r));
							}
						}

						for (Species s : Species.values()) {
							if (itemName.equals(s.getSpeciesName())) {
								blacklist(e.getClick(), AbilityList.nameValues(s));
							}
						}
					}
				}
			}
		}
	}

	private void blacklist(ClickType clickType, Collection<String> abilityNames) {
		if (clickType == ClickType.LEFT) {
			Settings.addBlacklist(abilityNames);
			SoundLib.BLOCK_ANVIL_LAND.playSound(p);
		} else if (clickType == ClickType.RIGHT) {
			Settings.removeBlacklist(abilityNames);
			SoundLib.ENTITY_EXPERIENCE_ORB_PICKUP.playSound(p);
		}
		openGUI(playerPage);
	}

}