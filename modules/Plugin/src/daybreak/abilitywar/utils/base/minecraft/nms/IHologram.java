package daybreak.abilitywar.utils.base.minecraft.nms;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * 홀로그램 인터페이스
 * @author Daybreak 새벽
 */
public interface IHologram {
    
    /**
     * 홀로그램을 생성합니다.
     */
    void create();
    
    /**
     * 홀로그램을 제거합니다.
     */
    void remove();
    
    /**
     * 홀로그램의 위치를 설정합니다.
     */
    void setLocation(Location location);
    
    /**
     * 홀로그램의 텍스트를 설정합니다.
     */
    void setText(String text);
    
    /**
     * 플레이어에게 홀로그램을 보여줍니다.
     */
    void showTo(Player player);
    
    /**
     * 플레이어에게 홀로그램을 숨깁니다.
     */
    void hideFrom(Player player);
    
    /**
     * 홀로그램이 보이는지 확인합니다.
     */
    boolean isVisible();
    
    /**
     * 플레이어에게 홀로그램을 표시합니다.
     */
    void display(Player player);
    
    /**
     * 홀로그램을 등록 해제합니다.
     */
    void unregister();
    
    /**
     * 홀로그램을 텔레포트합니다.
     */
    void teleport(org.bukkit.World world, double x, double y, double z, float yaw, int pitch);
    
    /**
     * 플레이어에게 홀로그램을 숨깁니다.
     */
    void hide(Player player);
}
