package ${common.packageName}.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description
 *
 * @author ${common.author}
 * @version ${common.version}
 * @date ${common.dateTime}
 */
public class IpUtils {

    private static final Logger logger = LoggerFactory.getLogger(IpUtils.class);

    public static String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("获取IP地址失败!", e);
            return StringUtils.EMPTY;
        }
    }
}
