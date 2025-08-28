package daybreak.abilitywar.utils.base.minecraft.boundary;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * 경계선 클래스
 * @author Daybreak 새벽
 */
public class Boundary {
    
    private final BoundingBox boundingBox;
    
    public Boundary(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }
    
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
    
    public boolean contains(Location location) {
        return boundingBox.contains(location);
    }
    
    public boolean intersects(Boundary other) {
        return boundingBox.intersects(other.boundingBox);
    }
    
    public Vector getCenter() {
        return boundingBox.getCenter();
    }
    
    /**
     * 경계선을 생성합니다.
     */
    public static Boundary of(BoundingBox boundingBox) {
        return new Boundary(boundingBox);
    }
    
    /**
     * 경계선을 생성합니다.
     */
    public static Boundary of(Location min, Location max) {
        return new Boundary(new BoundingBox(min, max));
    }
}
