package daybreak.abilitywar.utils.base.minecraft.server;

/**
 * 서버가 지원되지 않을 때 발생하는 예외
 * @author Daybreak 새벽
 */
public class ServerNotSupportedException extends Exception {
    
    public ServerNotSupportedException() {
        super("This server type is not supported");
    }
    
    public ServerNotSupportedException(ServerType[] supportedServers) {
        super("This server type is not supported. Supported: " + java.util.Arrays.toString(supportedServers));
    }
    
    public ServerNotSupportedException(String message) {
        super(message);
    }
    
    public ServerNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 지원되는 서버 타입들을 가져옵니다.
     */
    public ServerType[] getSupported() {
        return new ServerType[0]; // 기본값 반환
    }
}
