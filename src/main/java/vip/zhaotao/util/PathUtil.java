package vip.zhaotao.util;

/**
 * 路径工具
 */
public class PathUtil {

    /**
     * Windows系统返回当前用户的桌面路径；其他系统返回当前用户的用户目录路径；
     *
     * @return
     */
    public static String getDesktopOrUserHome() {
        StringBuilder path = new StringBuilder(System.getProperty("user.home").replace("\\", "/"));
        if (System.getProperty("os.name").contains("Windows")) {
            path.append("/Desktop");
        }
        path.append("/");
        return path.toString();
    }
}
