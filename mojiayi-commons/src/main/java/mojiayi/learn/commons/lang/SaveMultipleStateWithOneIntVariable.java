package mojiayi.learn.commons.lang;

/**
 * 用二进制位来表示状态， state的二进制表示下，从右边数起，依次是第0位，第1位，第2位。
 * 第0位 表示扫微信二维码关注公众号活动
 * 第1位 表示分享链接到QQ群活动
 * 第2位 表示分享链接到论坛活动
 * 第3位 表示分享链接到微信朋友圈活动
 * 第4位 表示充50送2元红包活动
 * 第5位 表示充100送5元红包活动
 */
public class SaveMultipleStateWithOneIntVariable {
	/**
	 * 更新公众号关注状态为已关注
	 * @param state
	 * @return
	 */
	public static int addSubscribleWechat(int state) {
        return state | 0x1;
    }

	/**
	 * 判断是否已关注公众号
	 * @param state
	 * @return
	 */
    public static boolean isSubscribleWechat(int state) {
        return (state & 0x1) > 0;
    }

    public static int addShareQQ(int state) {
        return state | 0x2;
    }

    public static boolean isShareQQ(int state) {
        return (state & 0x2) > 0;
    }

    public static int addShareBBS(int state) {
        return state | 0x4;
    }

    public static boolean isShareBBS(int state) {
        return (state & 0x4) > 0;
    }

    public static int addShareWechat(int state) {
        return state | 0x8;
    }

    public static boolean isShareWechat(int state) {
        return (state & 0x8) > 0;
    }

    public static int addRecharge50(int state) {
        return state | 0x10;
    }

    public static boolean isRecharge50(int state) {
        return (state & 0x10) > 0;
    }

    public static int addRecharge100(int state) {
        return state | 0x20;
    }

    public static boolean isRecharge100(int state) {
        return (state & 0x20) > 0;
    }
}
