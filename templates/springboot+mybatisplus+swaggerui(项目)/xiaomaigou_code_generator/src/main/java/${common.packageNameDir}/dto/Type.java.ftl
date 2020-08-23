package ${common.packageName}.dto;

/**
 * 状态类型枚举
 *
 * @author ${common.author}
 * @version ${common.version}
 * @date ${common.dateTime}
 */
public enum Type {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 参数错误
     */
    BAD_REQUEST(400),
    /**
     * 鉴权失败
     */
    UNAUTHORIZED(401),
    /**
     * 权限拒绝
     */
    FORBIDDEN(403),
    /**
     * 未找到
     */
    NOT_FOUND(404),
    /**
     * 资源冲突
     */
    CONFILCT(409),
    /**
     * 错误
     */
    ERROR(500);
    private final Integer value;

    Type(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return this.value;
    }
}
