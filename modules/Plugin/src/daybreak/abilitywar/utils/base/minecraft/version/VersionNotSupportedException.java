package daybreak.abilitywar.utils.base.minecraft.version;

/**
 * 버전이 지원되지 않을 때 발생하는 예외
 * @author Daybreak 새벽
 */
public class VersionNotSupportedException extends Exception {
    
    public VersionNotSupportedException() {
        super("This version is not supported");
    }
    
    public VersionNotSupportedException(String message) {
        super(message);
    }
    
    public VersionNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
