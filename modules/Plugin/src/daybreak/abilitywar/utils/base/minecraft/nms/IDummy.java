package daybreak.abilitywar.utils.base.minecraft.nms;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * 더미 엔티티 인터페이스
 * @author Daybreak 새벽
 */
public interface IDummy {
    
    /**
     * 더미를 생성합니다.
     */
    void create();
    
    /**
     * 더미를 제거합니다.
     */
    void remove();
    
    /**
     * 더미의 위치를 설정합니다.
     */
    void setLocation(Location location);
    
    /**
     * 더미의 스킨을 설정합니다.
     */
    void setSkin(String skinValue, String skinSignature);
    
    /**
     * 더미의 이름을 설정합니다.
     */
    void setName(String name);
    
    /**
     * 플레이어에게 더미를 보여줍니다.
     */
    void showTo(Player player);
    
    /**
     * 플레이어에게 더미를 숨깁니다.
     */
    void hideFrom(Player player);
    
    /**
     * 더미가 보이는지 확인합니다.
     */
    boolean isVisible();
    
    /**
     * 더미의 위치를 가져옵니다.
     */
    Location getLocation();
}
