package daybreak.abilitywar.utils.base.minecraft.boundary;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * 경계선 박스 클래스
 * @author Daybreak 새벽
 */
public class BoundingBox {
    
    protected final double minX, minY, minZ;
    protected final double maxX, maxY, maxZ;
    
    public BoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = Math.min(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.minZ = Math.min(minZ, maxZ);
        this.maxX = Math.max(minX, maxX);
        this.maxY = Math.max(minY, maxY);
        this.maxZ = Math.max(minZ, maxZ);
    }
    
    public BoundingBox(Location min, Location max) {
        this(min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ());
    }
    
    public static BoundingBox of(Location location, double size) {
        double halfSize = size / 2.0;
        return new BoundingBox(
            location.getX() - halfSize,
            location.getY() - halfSize,
            location.getZ() - halfSize,
            location.getX() + halfSize,
            location.getY() + halfSize,
            location.getZ() + halfSize
        );
    }
    
    public static <T> BoundingBox of(T entity) {
        if (entity instanceof org.bukkit.entity.Entity) {
            org.bukkit.entity.Entity e = (org.bukkit.entity.Entity) entity;
            return new BoundingBox(
                e.getLocation().getX() - e.getWidth() / 2,
                e.getLocation().getY(),
                e.getLocation().getZ() - e.getWidth() / 2,
                e.getLocation().getX() + e.getWidth() / 2,
                e.getLocation().getY() + e.getHeight(),
                e.getLocation().getZ() + e.getWidth() / 2
            );
        }
        return null;
    }
    
    public static BoundingBox of(Location min, Location max) {
        return new BoundingBox(min, max);
    }
    
    public static BoundingBox of(Location location, int x1, int y1, int z1, int x2, int y2, int z2) {
        return new BoundingBox(
            location.getX() + x1, location.getY() + y1, location.getZ() + z1,
            location.getX() + x2, location.getY() + y2, location.getZ() + z2
        );
    }
    
    public boolean contains(Location location) {
        return location.getX() >= minX && location.getX() <= maxX &&
               location.getY() >= minY && location.getY() <= maxY &&
               location.getZ() >= minZ && location.getZ() <= maxZ;
    }
    
    public boolean intersects(BoundingBox other) {
        return this.minX <= other.maxX && this.maxX >= other.minX &&
               this.minY <= other.maxY && this.maxY >= other.minY &&
               this.minZ <= other.maxZ && this.maxZ >= other.minZ;
    }
    
    public boolean conflicts(BoundingBox other) {
        return intersects(other);
    }
    
    public <T> boolean conflicts(T other) {
        if (other instanceof BoundingBox) {
            return conflicts((BoundingBox) other);
        }
        return false;
    }
    
    public double getMinX() { return minX; }
    public double getMinY() { return minY; }
    public double getMinZ() { return minZ; }
    public double getMaxX() { return maxX; }
    public double getMaxY() { return maxY; }
    public double getMaxZ() { return maxZ; }
    
    public Vector getCenter() {
        return new Vector((minX + maxX) / 2, (minY + maxY) / 2, (minZ + maxZ) / 2);
    }
    
    public double getWidth() { return maxX - minX; }
    public double getHeight() { return maxY - minY; }
    public double getDepth() { return maxZ - minZ; }
    
    /**
     * 바운딩박스를 리사이즈합니다.
     */
    public BoundingBox resize(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }
    
    /**
     * 바운딩박스를 확장합니다.
     */
    public BoundingBox expand(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new BoundingBox(this.minX - minX, this.minY - minY, this.minZ - minZ, 
                              this.maxX + maxX, this.maxY + maxY, this.maxZ + maxZ);
    }
    

    
    /**
     * 바운딩박스를 확장합니다.
     */
    public BoundingBox expand(double x, double y, double z) {
        return expand(x, y, z, x, y, z);
    }
    
    /**
     * 바운딩박스를 확장합니다.
     */
    public BoundingBox expand(double amount) {
        return expand(amount, amount, amount);
    }
    
    /**
     * 바운딩박스를 확장합니다.
     */
    public BoundingBox expand(double x, double y, double z, double w) {
        return expand(x, y, z, x, y, z);
    }
    
    /**
     * 바운딩박스를 벡터 방향으로 확장합니다.
     */
    public BoundingBox expand(Vector vector, double amount) {
        return expand(vector.getX() * amount, vector.getY() * amount, vector.getZ() * amount);
    }
    
    /**
     * 바운딩박스를 벡터로 확장합니다.
     */
    public BoundingBox expand(Vector vector) {
        return expand(vector.getX(), vector.getY(), vector.getZ());
    }
    
    /**
     * 바운딩박스를 블록페이스 방향으로 확장합니다.
     */
    public BoundingBox expand(org.bukkit.block.BlockFace face, double amount) {
        return expand(face.getModX() * amount, face.getModY() * amount, face.getModZ() * amount);
    }
    
    /**
     * 바운딩박스를 방향으로 확장합니다.
     */
    public BoundingBox expandDirectional(double x, double y, double z) {
        return expand(x, y, z);
    }
    
    /**
     * 바운딩박스를 벡터 방향으로 확장합니다.
     */
    public BoundingBox expandDirectional(Vector vector) {
        return expand(vector);
    }
    
    /**
     * 바운딩박스를 이동시킵니다.
     */
    public BoundingBox shift(double x, double y, double z) {
        return new BoundingBox(minX + x, minY + y, minZ + z, maxX + x, maxY + y, maxZ + z);
    }
    
    /**
     * 바운딩박스를 벡터로 이동시킵니다.
     */
    public BoundingBox shift(Vector vector) {
        return shift(vector.getX(), vector.getY(), vector.getZ());
    }
    
    /**
     * 레이트레이스를 수행합니다.
     */
    public daybreak.abilitywar.utils.base.minecraft.raytrace.RayTrace rayTrace(Vector start, Vector direction, int maxDistance) {
        return daybreak.abilitywar.utils.base.minecraft.raytrace.RayTrace.of(
            new org.bukkit.Location(null, start.getX(), start.getY(), start.getZ()),
            direction, maxDistance);
    }
    
    /**
     * 레이트레이스를 수행합니다.
     */
    public daybreak.abilitywar.utils.base.minecraft.raytrace.RayTrace rayTrace(Vector start, Vector direction) {
        return daybreak.abilitywar.utils.base.minecraft.raytrace.RayTrace.of(
            new org.bukkit.Location(null, start.getX(), start.getY(), start.getZ()),
            direction, 100);
    }
}
