package daybreak.abilitywar.utils.base.minecraft.entity.health;

import daybreak.abilitywar.utils.base.minecraft.entity.health.event.PlayerSetHealthEvent;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class Healths {

	private Healths() {}

	public static double setHealth(final Player player, final double health) {
		final PlayerSetHealthEvent event = new PlayerSetHealthEvent(player, health);
		Bukkit.getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			final AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
			if (attribute != null) {
				player.setHealth(Math.min(event.getHealth(), attribute.getValue()));
			}
		}
		return player.getHealth();
	}

	public static double getMaxHealth(final Player player) {
		final AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		if (attribute != null) {
			return attribute.getValue();
		}
		return 20.0; // 기본값
	}
	
	public static void setMaxHealth(final Player player, final double maxHealth) {
		final AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		if (attribute != null) {
			attribute.setBaseValue(maxHealth);
		}
	}

}
