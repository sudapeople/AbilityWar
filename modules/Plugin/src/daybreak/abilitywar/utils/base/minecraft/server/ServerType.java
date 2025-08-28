package daybreak.abilitywar.utils.base.minecraft.server;

import org.bukkit.Bukkit;

/**
 * 서버 타입 관리 클래스
 * @author Daybreak 새벽
 */
public class ServerType {
    
    public static final ServerType PAPER = new ServerType("Paper");
    public static final ServerType SPIGOT = new ServerType("Spigot");
    public static final ServerType CRAFTBUKKIT = new ServerType("CraftBukkit");
    
    private final String name;
    
    private ServerType(String name) {
        this.name = name;
    }
    
    public static ServerType getServerType() {
        String serverName = Bukkit.getName();
        if (serverName.contains("Paper")) {
            return PAPER;
        } else if (serverName.contains("Spigot")) {
            return SPIGOT;
        } else {
            return CRAFTBUKKIT;
        }
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    /**
     * 서버 타입의 이름을 반환합니다.
     */
    public String name() {
        return name;
    }
}
