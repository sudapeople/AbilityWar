package daybreak.abilitywar.utils.base.minecraft.boundary;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * 경계선 데이터 클래스
 * @author Daybreak 새벽
 */
public class BoundaryData {
    
    private final Location min;
    private final Location max;
    private final Vector center;
    private final double width, height, depth;
    
    public BoundaryData(Location min, Location max) {
        this.min = min;
        this.max = max;
        this.center = new Vector(
            (min.getX() + max.getX()) / 2,
            (min.getY() + max.getY()) / 2,
            (min.getZ() + max.getZ()) / 2
        );
        this.width = Math.abs(max.getX() - min.getX());
        this.height = Math.abs(max.getY() - min.getY());
        this.depth = Math.abs(max.getZ() - min.getZ());
    }
    
    public Location getMin() {
        return min;
    }
    
    public Location getMax() {
        return max;
    }
    
    public Vector getCenter() {
        return center;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public double getDepth() {
        return depth;
    }
    
    public boolean contains(Location location) {
        return location.getX() >= min.getX() && location.getX() <= max.getX() &&
               location.getY() >= min.getY() && location.getY() <= max.getY() &&
               location.getZ() >= min.getZ() && location.getZ() <= max.getZ();
    }
    
    public boolean conflicts(BoundaryData other) {
        return this.min.getX() <= other.max.getX() && this.max.getX() >= other.min.getX() &&
               this.min.getY() <= other.max.getY() && this.max.getY() >= other.min.getY() &&
               this.min.getZ() <= other.max.getZ() && this.max.getZ() >= other.min.getZ();
    }
    
    public static BoundaryData of(org.bukkit.entity.EntityType entityType) {
        // 더미 구현 - 실제로는 엔티티 타입에 따른 경계선을 반환해야 함
        return new BoundaryData(new org.bukkit.Location(null, 0, 0, 0), new org.bukkit.Location(null, 1, 1, 1));
    }
}
