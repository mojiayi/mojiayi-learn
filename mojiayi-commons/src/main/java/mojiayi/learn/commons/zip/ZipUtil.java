package mojiayi.learn.commons.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang.StringUtils;

/**
 * @author mojiayi 将一个字符串按照GZIP方式压缩和解压缩
 *
 */
public class ZipUtil {
	private static final String ISO_8859_1 = "ISO-8859-1";

	/**
	 * GZIP使用系统默认字符编码压缩数据
	 * 
	 * @param str
	 * @return
	 */
	public static String gZip(String str) {
		try {
			if (StringUtils.isBlank(str)) {
				return str;
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes());
			gzip.close();
			return out.toString(ISO_8859_1);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * GZIP使用指定字符编码压缩数据
	 * 
	 * @param str
	 * @param charsetName
	 * @return
	 */
	public static String gZip(String str, String charsetName) {
		try {
			if (StringUtils.isBlank(str)) {
				return str;
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes(charsetName));
			gzip.close();
			return out.toString(ISO_8859_1);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * GZIP使用系统默认字符编码解压缩数据
	 * 
	 * @param str
	 * @return
	 */
	public static String unGzip(String str) {
		try {
			if (StringUtils.isBlank(str)) {
				return str;
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes(ISO_8859_1));
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			return out.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * GZIP使用指定字符编码解压缩数据
	 * 
	 * @param str
	 * @param charsetName
	 * @return
	 */
	public static String unGzip(String str, String charsetName) {
		try {
			if (StringUtils.isBlank(str)) {
				return str;
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes(ISO_8859_1));
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			return out.toString(charsetName);
		} catch (Exception ex) {
			return "";
		}
	}
}