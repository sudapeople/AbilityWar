package daybreak.abilitywar.utils.base.minecraft.boundary;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * 중앙 기준 경계선 박스 클래스
 * @author Daybreak 새벽
 */
public class CenteredBoundingBox extends BoundingBox {
    
    private final Location center;
    private final double radius;
    
    public CenteredBoundingBox(Location center, double radius) {
        super(
            center.getX() - radius,
            center.getY() - radius,
            center.getZ() - radius,
            center.getX() + radius,
            center.getY() + radius,
            center.getZ() + radius
        );
        this.center = center;
        this.radius = radius;
    }
    
    public CenteredBoundingBox(Location center, double radiusX, double radiusY, double radiusZ) {
        super(
            center.getX() - radiusX,
            center.getY() - radiusY,
            center.getZ() - radiusZ,
            center.getX() + radiusX,
            center.getY() + radiusY,
            center.getZ() + radiusZ
        );
        this.center = center;
        this.radius = Math.max(Math.max(radiusX, radiusY), radiusZ);
    }
    
    public Location getCenterLocation() {
        return center;
    }
    
    @Override
    public Vector getCenter() {
        return center.toVector();
    }
    
    /**
     * 중심점을 설정합니다.
     */
    public void setCenter(Location newCenter) {
        // 새로운 중심점으로 업데이트
        this.center = newCenter;
    }
    
    public double getRadius() {
        return radius;
    }
    
    public boolean contains(Location location) {
        return location.distance(center) <= radius;
    }
    
    public boolean contains(Vector vector) {
        return vector.distance(center.toVector()) <= radius;
    }
    
    public static CenteredBoundingBox of(Location center, double radius) {
        return new CenteredBoundingBox(center, radius);
    }
    
    public static CenteredBoundingBox of(Location center, double radiusX, double radiusY, double radiusZ) {
        return new CenteredBoundingBox(center, radiusX, radiusY, radiusZ);
    }
    
    public static CenteredBoundingBox of(Vector center, int x1, int y1, int z1, int x2, int y2, int z2) {
        Location loc = new Location(null, center.getX(), center.getY(), center.getZ());
        double radius = Math.max(Math.max(x2-x1, y2-y1), z2-z1) / 2.0;
        return new CenteredBoundingBox(loc, radius);
    }
}
