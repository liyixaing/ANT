package com.example.administrator.mode.Utlis;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.mode.Fragment.homeFragment.ANTStoreActivity;
import com.example.administrator.mode.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.utils.Numeric;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import static android.content.Context.CLIPBOARD_SERVICE;

//检验
public class VerifyUtlis {
    public static final int MY_PERMISSION_REQUEST_CODE = 10000;
    //相册请求码
    public static final int ALBUM_REQUEST_CODE = 1;
    public static final int ALBUM_REQUEST_CODEA = 8;
    //相机请求码
    public static final int CAMERA_REQUEST_CODE = 2;
    public static final int SYSTEM_CAMERA_CODE = 4;
    //剪裁请求码
    public static final int CROP_REQUEST_CODE = 3;
    public static final int CROP_REQUEST_CODD = 5;
    private static long lastTime;
    private static long curTime;

    /**
     * 获取手机IMEI号
     * <p>
     * 需要动态权限: android.permission.READ_PHONE_STATE
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }

    public static boolean mClick() {
        if (System.currentTimeMillis() - lastTime >= 5000) {
            curTime = System.currentTimeMillis();
            lastTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }


    public static String stringChang(String s) {

        return s.replace("\\n","\n");

//        StringBuffer sb = new StringBuffer();
//        char[] c;
//        c = QueryMerchantApplyStatus.toCharArray();
//        for (int i = 0; i < c.length; i++) {
//            if (c[i] != '\\') {
//                if (c[i] != 'n') {
//                    sb.append(c[i]);
//                } else {
//                    sb.append("");
//                }
//            } else {
//                sb.append("\n");
//            }
//        }
//        return sb.toString();
    }


    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);

        }
        return versionName;
    }
    public static boolean isInstallApp(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName.toLowerCase(Locale.ENGLISH);
                if (pn.equals("com.rubychain.wallet")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 字符串保存到手机内存设备中
     *
     * @param str
     */
    public static String saveFile(String str, String fileName) {
        // 创建String对象保存文件名路径
        try {
            // 创建指定路径的文件
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            // 如果文件不存在
            if (file.exists()) {
                // 创建新的空文件
                file.delete();
            }
            file.createNewFile();
            // 获取文件的输出流对象
            FileOutputStream outStream = new FileOutputStream(file);
            // 获取字符串对象的byte数组并写入文件流
            outStream.write(str.getBytes());
            // 最后关闭文件输出流
            outStream.close();
            return file.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 删除已存储的文件
     */
    public static void deletefile(String fileName) {
        try {
            // 找到文件所在的路径并删除该文件
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件里面的内容
     *
     * @return
     */
    public static String getFile(String fileName) {
        try {
            // 创建文件
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            // 创建FileInputStream对象
            FileInputStream fis = new FileInputStream(file);
            // 创建字节数组 每次缓冲1M
            byte[] b = new byte[1024];
            int len = 0;// 一次读取1024字节大小，没有数据后返回-1.
            // 创建ByteArrayOutputStream对象
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 一次读取1024个字节，然后往字符输出流中写读取的字节数
            while ((len = fis.read(b)) != -1) {
                baos.write(b, 0, len);
            }
            // 将读取的字节总数生成字节数组
            byte[] data = baos.toByteArray();
            // 关闭字节输出流
            baos.close();
            // 关闭文件输入流
            fis.close();
            // 返回字符串对象
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static Bitmap createQRCodeBitmap(String content, int width, int height, String character_set,
                                            String error_correction_level, String margin, int color_black,
                                            int color_white, Bitmap logoBitmap, float logoPercent) {
        // 字符串内容判空
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        // 宽和高>=0
        if (width < 0 || height < 0) {
            return null;
        }
        try {
            /** 1.设置二维码相关配置,生成BitMatrix(位矩阵)对象 */
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            // 字符转码格式设置
            if (!TextUtils.isEmpty(character_set)) {
                hints.put(EncodeHintType.CHARACTER_SET, character_set);
            }
            // 容错率设置
            if (!TextUtils.isEmpty(error_correction_level)) {
                hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
            }
            // 空白边距设置
            if (!TextUtils.isEmpty(margin)) {
                hints.put(EncodeHintType.MARGIN, margin);
            }
            /** 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = color_black;//黑色色块像素设置
                    } else {
                        pixels[y * width + x] = color_white;// 白色色块像素设置
                    }
                }
            }

            /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

            /** 5.为二维码添加logo图标 */
            if (logoBitmap != null) {
                return addLogo(bitmap, logoBitmap, logoPercent);
            }
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static Bitmap addLogo(Bitmap srcBitmap, Bitmap logoBitmap, float logoPercent) {
        if (srcBitmap == null) {
            return null;
        }
        if (logoBitmap == null) {
            return srcBitmap;
        }
        //传值不合法时使用0.2F
        if (logoPercent < 0F || logoPercent > 1F) {
            logoPercent = 0.2F;
        }

        /** 1. 获取原图片和Logo图片各自的宽、高值 */
        int srcWidth = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();
        int logoWidth = logoBitmap.getWidth();
        int logoHeight = logoBitmap.getHeight();

        /** 2. 计算画布缩放的宽高比 */
        float scaleWidth = srcWidth * logoPercent / logoWidth;
        float scaleHeight = srcHeight * logoPercent / logoHeight;

        /** 3. 使用Canvas绘制,合成图片 */
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(srcBitmap, 0, 0, null);
        canvas.scale(scaleWidth, scaleHeight, srcWidth / 2, srcHeight / 2);
        canvas.drawBitmap(logoBitmap, srcWidth / 2 - logoWidth / 2, srcHeight / 2 - logoHeight / 2, null);

        return bitmap;
    }

    public static void copy(Context context, String copystring) {
        android.text.ClipboardManager cm = (android.text.ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        cm.setText(copystring + "");
        Toast.makeText(context, R.string.Out_copyok, Toast.LENGTH_SHORT).show();
    }


    public final static String md5(String content) {
        //用于加密的字符
        char[] md5String = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = content.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            //返回经过加密后的字符串
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

    public static String convert(char[] a) {
        char temp = a[1];
        a[1] = a[32 - 1];
        a[32 - 1] = temp;
        char temp2 = a[2];
        a[2] = a[32 - 2];
        a[32 - 2] = temp2;
        char temp11 = a[11];
        a[11] = a[32 - 11];
        a[32 - 11] = temp11;
        char temp12 = a[12];
        a[12] = a[32 - 12];
        a[32 - 12] = temp12;
        char temp25 = a[25];
        a[25] = a[32 - 25];
        a[32 - 25] = temp25;
        char temp27 = a[27];
        a[27] = a[32 - 27];
        a[32 - 27] = temp27;
        String str = new String(a);
        return str;
    }

    //邮箱校验
    public static boolean isEmaill(String idCard) {
        String reg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        if (!idCard.matches(reg)) {
//            System.out.println("Format Error!");
            return false;
        }
        return true;
    }

    public static String getFetureDate(long expire) {
        //PHP和Java时间戳存在三位位差，用000补齐
        if (String.valueOf(expire).length() == 10) {
            expire = expire * 1000;
        }
        Date date = new Date(expire);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        String result = format.format(date);
        if (result.startsWith("0")) {
            result = result.substring(1);
        }
        return result;
    }

    public static byte[] toHash(String key) {
        return Hash.sha256(key.getBytes());
    }

    //验证手机号码
  /*  public static boolean isMobelPhone(String tel) {
        if (tel == null || tel.length() < 11) {
            return false;
        }
        Pattern p = Pattern
                .compile("^1[3|4|5|7|8]\\d{9}$");
        Matcher m = p.matcher(tel);
        return m.matches();
    }*/

    public static String EncodeAuthKey(String timeStempStr) {
        //第一次Base64编码.encodeToString( timeStempStr.getBytes() );
        String base64EncodeStr_1 = Base64Utils.encode(timeStempStr.getBytes());
        StringBuilder changeTempBuilder = new StringBuilder(base64EncodeStr_1);
        if (!(
                charChange(1, 2, changeTempBuilder) &&
                        charChange(2, 3, changeTempBuilder) &&
                        charChange(3, 2, changeTempBuilder) &&
                        charChange(5, 2, changeTempBuilder)
        )) {
            return "";
        }

        //  String base64EncodeStr_2 = Base64Utils.encodeToString(changeTempBuilder.toString().getBytes());
        String base64EncodeStr_2 = Base64Utils.encode(changeTempBuilder.toString().getBytes());
        changeTempBuilder = new StringBuilder(base64EncodeStr_2);
        if (!(
                charChange(1, 2, changeTempBuilder) &&
                        charChange(2, 3, changeTempBuilder) &&
                        charChange(3, 2, changeTempBuilder) &&
                        charChange(5, 2, changeTempBuilder)
        )) {
            return "";
        }
        //第二次Base64解码
        return changeTempBuilder.toString();

    }

    public static String DecodeAuthKey(String encodeStr) {
        //第一次换位
        StringBuilder changeTempBuilder = new StringBuilder(encodeStr);

        if (!(
                charChange(5, 2, changeTempBuilder) &&
                        charChange(3, 2, changeTempBuilder) &&
                        charChange(2, 3, changeTempBuilder) &&
                        charChange(1, 2, changeTempBuilder)
        )) {
            return "";
        }
//        byte[] base64DecodeByte_1 = Base64Utils.decodeFromString(changeTempBuilder.toString());
        byte[] base64DecodeByte_1 = Base64Utils.decode(changeTempBuilder.toString());

        String base64DecodeStr_1 = new String(base64DecodeByte_1);

        changeTempBuilder = new StringBuilder(base64DecodeStr_1);

        if (!(
                charChange(5, 2, changeTempBuilder) &&
                        charChange(3, 2, changeTempBuilder) &&
                        charChange(2, 3, changeTempBuilder) &&
                        charChange(1, 2, changeTempBuilder)
        )) {
            return "";
        }

        //第二次Base64解码
//        byte[] base64DecodeByte_2 = Base64Utils.decodeFromString(changeTempBuilder.toString());

        byte[] base64DecodeByte_2 = Base64Utils.decode(changeTempBuilder.toString());

        return new String(base64DecodeByte_2);
    }

    public static boolean charChange(int index, int step, StringBuilder chars) {
        if (index <= 0 || step <= 0) {
            return false;
        }
        int length = chars.length();
        for (int i = 0; i < length; i += step) {
            int front = i;
            int back = i + index;

            if ((front > 0 && front < length - 2) &&
                    (back > 0 && back < length - 2) &&
                    front != back) {
                char sChar = chars.charAt(front);
                char dChar = chars.charAt(back);

                chars.replace(front, front + 1, String.valueOf(dChar));
                chars.replace(back, back + 1, String.valueOf(sChar));
            }
        }
        return true;
    }

    public static String getPathFromUri(final Context context, final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String path = null;
        if (scheme == null)
            path = uri.getPath();
        else {
            if (ContentResolver.SCHEME_FILE.equals(scheme)) {
                path = uri.getPath();
            } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
                Cursor cursor = context.getContentResolver()
                        .query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
                if (null != cursor) {
                    if (cursor.moveToFirst()) {
                        int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                        if (index > -1) {
                            path = cursor.getString(index);
                        }
                    }
                    cursor.close();
                }
            }
        }
        return path;
    }

    public static Intent take(Context context) {
        Uri imageUri;
        File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");        // Create the storage directory if it does not exist
        if (!imageStorageDir.exists()) {
            imageStorageDir.mkdirs();
        }
        File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(new ANTStoreActivity(), "com.example.app2.fileprovider", file);
        } else {
            imageUri = Uri.fromFile(file);
        }
        final List<Intent> cameraIntents = new ArrayList();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = context.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent i = new Intent(captureIntent);
            i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            i.setPackage(packageName);
            i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraIntents.add(i);

        }
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        Intent chooserIntent = Intent.createChooser(i, "Image Chooser");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
        return chooserIntent;
    }
}
