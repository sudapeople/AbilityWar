package daybreak.abilitywar.game.list.mix.synergy.list;

import daybreak.abilitywar.ability.AbilityManifest;
import daybreak.abilitywar.ability.AbilityManifest.Rank;
import daybreak.abilitywar.ability.AbilityManifest.Species;
import daybreak.abilitywar.ability.SubscribeEvent;
import daybreak.abilitywar.ability.decorator.ActiveHandler;
import daybreak.abilitywar.config.ability.AbilitySettings.SettingObject;
import daybreak.abilitywar.game.AbstractGame.Participant;
import daybreak.abilitywar.game.list.mix.synergy.Synergy;
import daybreak.abilitywar.game.module.DeathManager;
import daybreak.abilitywar.game.team.interfaces.Teamable;
import daybreak.abilitywar.utils.base.Formatter;
import daybreak.abilitywar.utils.base.concurrent.TimeUnit;
import daybreak.abilitywar.utils.base.math.LocationUtil;
import daybreak.abilitywar.utils.base.minecraft.FallingBlocks;
import daybreak.abilitywar.utils.base.minecraft.FallingBlocks.Behavior;
import daybreak.abilitywar.utils.base.minecraft.version.ServerVersion;
import daybreak.abilitywar.utils.library.SoundLib;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

@AbilityManifest(name = "암흑 암살자", rank = Rank.S, species = Species.HUMAN, explain = {
		"철괴를 우클릭하면 주변의 생명체들을 끌고 공중으로 올라가 각각 4번씩 공격한 후",
		"바라보는 방향으로 날아가 내려 찍으며 주변의 플레이어들에게",
		"대미지를 입히고 날려보냅니다. $[COOLDOWN_CONFIG]"
})
public class NexAssassin extends Synergy implements ActiveHandler {

	public static final SettingObject<Integer> COOLDOWN_CONFIG = synergySettings.new SettingObject<Integer>(NexAssassin.class, "cooldown", 120, "# 쿨타임") {

		@Override
		public boolean condition(Integer value) {
			return value >= 0;
		}

		@Override
		public String toString() {
			return Formatter.formatCooldown(getValue());
		}

	};

	public static final SettingObject<Integer> NEX_DAMAGE_CONFIG = synergySettings.new SettingObject<Integer>(NexAssassin.class, "nex-damage", 20, "# 대미지") {

		@Override
		public boolean condition(Integer value) {
			return value >= 1;
		}

	};

	public static final SettingObject<Integer> DISTANCE_CONFIG = synergySettings.new SettingObject<Integer>(NexAssassin.class, "distance", 10,
			"# 스킬 대미지") {

		@Override
		public boolean condition(Integer value) {
			return value > 0;
		}

	};

	public static final SettingObject<Integer> TeleportCountConfig = synergySettings.new SettingObject<Integer>(NexAssassin.class, "teleport-count", 6,
			"# 능력 사용 시 텔레포트 횟수") {

		@Override
		public boolean condition(Integer value) {
			return value >= 1;
		}

	};

	public static final SettingObject<Integer> DAMAGE_CONFIG = synergySettings.new SettingObject<Integer>(NexAssassin.class, "assassin-damage", 9,
			"# 스킬 대미지") {

		@Override
		public boolean condition(Integer value) {
			return value >= 0;
		}

	};

	private final Predicate<Entity> predicate = new Predicate<Entity>() {
		@Override
		public boolean test(Entity entity) {
			if (entity.equals(getPlayer())) return false;
			if (entity instanceof Player) {
				if (!getGame().isParticipating(entity.getUniqueId())
						|| (getGame() instanceof DeathManager.Handler && ((DeathManager.Handler) getGame()).getDeathManager().isExcluded(entity.getUniqueId()))
						|| !getGame().getParticipant(entity.getUniqueId()).attributes().TARGETABLE.getValue()) {
					return false;
				}
				if (getGame() instanceof Teamable) {
					final Teamable teamGame = (Teamable) getGame();
					final Participant entityParticipant = teamGame.getParticipant(entity.getUniqueId()), participant = getParticipant();
					return !teamGame.hasTeam(entityParticipant) || !teamGame.hasTeam(participant) || (!teamGame.getTeam(entityParticipant).equals(teamGame.getTeam(participant)));
				}
			}
			return true;
		}
	};

	private final Cooldown cooldownTimer = new Cooldown(COOLDOWN_CONFIG.getValue());
	private final int damage = DAMAGE_CONFIG.getValue();
	private final int distance = DISTANCE_CONFIG.getValue();
	private final AbilityTimer fallBlockTimer = new AbilityTimer(5) {

		Location center;

		@Override
		public void onStart() {
			this.center = getPlayer().getLocation();
		}

		@SuppressWarnings("deprecation")
		@Override
		public void run(int count) {
			int distance = 6 - count;

			if (true) { // Paper 1.21.8에서는 항상 true
				for (Block block : LocationUtil.getBlocks2D(center, distance, true, true, true)) {
					if (block.getType() == Material.AIR) block = block.getRelative(BlockFace.DOWN);
					if (block.getType() == Material.AIR) continue;
					Location location = block.getLocation().add(0, 1, 0);
					FallingBlocks.spawnFallingBlock(location, block.getType(), false, getPlayer().getLocation().toVector().subtract(location.toVector()).multiply(-0.1).setY(Math.random()), Behavior.FALSE);
				}
			} else {
				for (Block block : LocationUtil.getBlocks2D(center, distance, true, true, true)) {
					if (block.getType() == Material.AIR) block = block.getRelative(BlockFace.DOWN);
					if (block.getType() == Material.AIR) continue;
					Location location = block.getLocation().add(0, 1, 0);
					FallingBlocks.spawnFallingBlock(location, block.getType(), block.getData(), false, getPlayer().getLocation().toVector().subtract(location.toVector()).multiply(-0.1).setY(Math.random()), Behavior.FALSE);
				}
			}

			for (Damageable damageable : LocationUtil.getNearbyEntities(Damageable.class, center, 5, 5, predicate)) {
				if (!damageable.equals(getPlayer())) {
					damageable.setVelocity(center.toVector().subtract(damageable.getLocation().toVector()).multiply(-1).setY(0.6));
				}
			}
		}

	}.setPeriod(TimeUnit.TICKS, 4).register();
	private Map<Damageable, Vector> entities = null;
	private final AbilityTimer follow = new AbilityTimer() {
		@Override
		protected void run(int count) {
			if (entities != null) {
				for (Entry<Damageable, Vector> entry : entities.entrySet()) {
					Damageable entity = entry.getKey();
					Vector diff = entry.getValue();
					entity.setVelocity(getPlayer().getLocation().toVector().add(diff).subtract(entity.getLocation().toVector()).multiply(0.7));
					entity.setFallDistance(0);
				}
			}
		}
	}.setPeriod(TimeUnit.TICKS, 1).register();
	private boolean noFallDamage = false;
	private boolean skillEnabled = false;
	private final AbilityTimer assassinSkill = new AbilityTimer() {

		private LinkedList<Damageable> damageables;
		private int assassin;

		@Override
		public void onStart() {
			assassin = 3;
			if (entities != null) {
				damageables = new LinkedList<>(entities.keySet());
				if (true) { // Paper 1.21.8에서는 항상 true
					for (Damageable entity : entities.keySet()) {
						entity.setGravity(false);
					}
				}
			}
		}

		@Override
		public void run(int count) {
			if (damageables != null) {
				if (!damageables.isEmpty()) {
					Damageable e = damageables.remove();
					getPlayer().teleport(e.getLocation().clone().setDirection(getPlayer().getLocation().getDirection()));
					e.damage(damage, getPlayer());
					if (e instanceof LivingEntity) ((LivingEntity) e).setNoDamageTicks(0);
					SoundLib.ENTITY_PLAYER_ATTACK_SWEEP.playSound(getPlayer());
					SoundLib.ENTITY_EXPERIENCE_ORB_PICKUP.playSound(getPlayer());
				} else {
					if (assassin != 0) {
						for (Damageable damageable : new ArrayList<>(entities.keySet())) {
							if (damageable.isDead()) {
								entities.remove(damageable);
							}
						}
						damageables = new LinkedList<>(entities.keySet());
						assassin--;
					} else {
						stop(false);
					}
				}
			}
		}

		@Override
		public void onEnd() {
			if (entities != null && ServerVersion.getVersion() >= 10) {
				for (Damageable entity : entities.keySet()) {
					entity.setGravity(true);
				}
			}
			skillEnabled = true;
			Vector playerDirection = getPlayer().getLocation().getDirection();
			getPlayer().setVelocity(getPlayer().getVelocity().add(playerDirection.normalize().multiply(8).setY(-4)));
		}

		@Override
		public void onSilentEnd() {
			if (entities != null && ServerVersion.getVersion() >= 10) {
				for (Damageable entity : entities.keySet()) {
					entity.setGravity(true);
				}
			}
			follow.stop(false);
		}

	}.setPeriod(TimeUnit.TICKS, 3).register();
	private final AbilityTimer nexSkill = new AbilityTimer(4) {

		@Override
		public void onStart() {
			follow.start();
			noFallDamage = true;
			getPlayer().setVelocity(getPlayer().getVelocity().add(new Vector(0, 4, 0)));
		}

		@Override
		public void run(int count) {
		}

		@Override
		public void onEnd() {
			assassinSkill.start();
		}

		@Override
		public void onSilentEnd() {
			follow.stop(false);
		}

	}.setPeriod(TimeUnit.TICKS, 10).register();

	public NexAssassin(Participant participant) {
		super(participant);
	}

	@Override
	public boolean ActiveSkill(@NotNull Material material, @NotNull ClickType clickType) {
		if (material == Material.IRON_INGOT) {
			if (clickType == ClickType.RIGHT_CLICK) {
				if (!nexSkill.isRunning() && !assassinSkill.isRunning() && !cooldownTimer.isCooldown()) {
					this.entities = new HashMap<>();
					for (Damageable damageable : LocationUtil.getNearbyEntities(Damageable.class, getPlayer().getLocation(), distance, distance, predicate)) {
						entities.put(damageable, damageable.getLocation().toVector().subtract(getPlayer().getLocation().toVector()));
					}
					if (entities.size() > 0) {
						for (Player player : LocationUtil.getNearbyEntities(Player.class, getPlayer().getLocation(), 5, 5, null)) {
							SoundLib.ENTITY_WITHER_SPAWN.playSound(player);
						}
						SoundLib.ENTITY_WITHER_SPAWN.playSound(getPlayer());
						nexSkill.start();
						cooldownTimer.start();
						return true;
					} else {
						getPlayer().sendMessage("§f" + distance + "칸 이내에 §a엔티티§f가 존재하지 않습니다.");
					}
				}
			}
		}

		return false;
	}

	@SubscribeEvent
	public void onEntityDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getEntity().equals(getPlayer())) {
				if (noFallDamage) {
					if (e.getCause().equals(DamageCause.FALL)) {
						e.setCancelled(true);
						noFallDamage = false;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerMove(PlayerMoveEvent e) {
		if (e.getPlayer().equals(getPlayer())) {
			if (skillEnabled) {
				Block b = getPlayer().getLocation().getBlock();
				Block db = getPlayer().getLocation().subtract(0, 1, 0).getBlock();

				if (!b.getType().equals(Material.AIR) || !db.getType().equals(Material.AIR)) {
					skillEnabled = false;
					final double damage = NEX_DAMAGE_CONFIG.getValue();
					for (Damageable d : LocationUtil.getNearbyEntities(Damageable.class, getPlayer().getLocation(), 5, 5, predicate)) {
						if (d instanceof Player) SoundLib.ENTITY_GENERIC_EXPLODE.playSound((Player) d);
						d.damage(damage, getPlayer());
					}
					SoundLib.ENTITY_GENERIC_EXPLODE.playSound(getPlayer());

					fallBlockTimer.start();
					follow.stop(false);
				}
			}
		}
	}

}
