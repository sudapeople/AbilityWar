package daybreak.abilitywar.utils.base.minecraft.raytrace;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * 레이트레이스 클래스
 * @author Daybreak 새벽
 */
public class RayTrace {
    
    private final Location origin;
    private final Vector direction;
    private final double maxDistance;
    
    public RayTrace(Location origin, Vector direction, double maxDistance) {
        this.origin = origin;
        this.direction = direction.normalize();
        this.maxDistance = maxDistance;
    }
    
    public Location getOrigin() {
        return origin;
    }
    
    public Vector getDirection() {
        return direction;
    }
    
    public double getMaxDistance() {
        return maxDistance;
    }
    
    /**
     * 레이트레이스를 생성합니다.
     */
    public static RayTrace of(Location origin, Vector direction, double maxDistance) {
        return new RayTrace(origin, direction, maxDistance);
    }
    
    /**
     * 레이트레이스를 생성합니다.
     */
    public static RayTrace of(Location origin, Location target) {
        Vector direction = target.toVector().subtract(origin.toVector()).normalize();
        double distance = origin.distance(target);
        return new RayTrace(origin, direction, distance);
    }
    
    /**
     * 블록과의 충돌을 확인합니다.
     */
    public static boolean hitsBlock(org.bukkit.World world, double x1, double y1, double z1, double x2, double y2, double z2) {
        // 간단한 구현 - 실제로는 더 정교한 레이트레이스가 필요
        return world.getBlockAt((int)x1, (int)y1, (int)z1).getType().isSolid() ||
               world.getBlockAt((int)x2, (int)y2, (int)z2).getType().isSolid();
    }
}
