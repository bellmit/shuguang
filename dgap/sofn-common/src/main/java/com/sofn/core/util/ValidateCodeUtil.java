package com.sofn.core.util;

import com.sofn.core.config.Global;
import com.sofn.core.constant.ApiConstants;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.UUID;

/**
 * Created by: dong4j.
 * Date: 2016-09-18.
 * Time: 15:51.
 * Description: 验证码工具类
 */
public final class ValidateCodeUtil {
    /**
     * The constant random.
     */
    private static Random random = new Random();
    /**
     * The constant randString.
     */
    private static String randString = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";// 随机产生的字符串
    /**
     * The constant width.
     */
    private static int width = 80;// 图片宽
    /** 
     * The constant height.
     */
    private static int height = 26;// 图片高

    /**
     * Gets font.
     * 获得字体
     *
     * @return the font
     */
    private static Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * Gets rand color.
     * 获得颜色
     *
     * @param fc the fc
     * @param bc the bc
     * @return the rand color
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * Drow string string.
     * 绘制字符串
     *
     * @param g          the g
     * @param randomChar the random char
     * @param i          the
     * @return the string
     */
//private static String drowString(Graphics g, String randomString, int i) {
    //    g.setFont(getFont());
    //    g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
    //            .nextInt(121)));
    //    String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
    //    randomString += rand;
    //    g.translate(random.nextInt(3), random.nextInt(3));
    //    g.drawString(rand, 13 * i, 16);
    //    return randomString;
    //}

    private static void drowString(Graphics g, char randomChar, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        //String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        //randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(randomChar + "", 13 * i, 16);
    }

    /**
     * Drow line.
     * 绘制干扰线
     *
     * @param g the g
     */
    private static void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * Gets random string.
     * 获取随机的字符
     *
     * @param num the num
     * @return the random string
     */
    public static String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

    /**
     * Generator random string string.
     *
     * @param num the num
     * @return the string
     */
    public static String generatorRandomString(int num){
        String randomString = "";
        for(int i = 1; i <= num; i++){
            randomString += String.valueOf(getRandomString(random.nextInt(randString.length())));
        }
        return randomString;
    }


    /**
     * 生成随机图片
     *
     * @param request  the request
     * @param response the response
     * @param key      the key
     */
    public static void getRandcode(HttpServletRequest request, HttpServletResponse response, String key) {

        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        int lineSize = 40;
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        //SpringDataRedisUtils.set(key,randomString);

        // 绘制随机字符
        String randomString = (String) RedisUtil.get(key);
        char[] charRandom = randomString.toCharArray();
        for(int i = 1; i <= randomString.length() ; i ++){
            drowString(g, charRandom[i - 1], i);
        }
        System.out.println("randomString=="+randomString);
        g.dispose();
        try {
            ByteArrayOutputStream tmp = new ByteArrayOutputStream();
            ImageIO.write(image, "png", tmp);
            tmp.close();
            Integer contentLength = tmp.size();
            response.setHeader("content-length", contentLength + "");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(tmp.toByteArray());// 将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


    /**
     * Check code boolean.
     * 验证码合法性验证
     *
     * @param key   the key
     * @param value the value
     * @return the boolean
     */
    public static boolean checkCode(String key,String value){
        // 如果是测试环境且为1azp 则直接返回 true 仅供压力测试用，测试结束后会取消该验证码
        if(value.equals("1azp") && !Global.getConfig("sofn.environment").contains("pro")){
            return true;
        }
        String randomString = (String) RedisUtil.get(key);
        return StringUtils.isNotBlank(randomString) && randomString.equals(value);
    }

    /**
     * Get uuid string.
     *
     * @param key the key
     * @param len the len
     * @return the string
     */
    public static String getUUID(String key, int len){
        // 生成4位随机字符串
        String validateCode = ValidateCodeUtil.generatorRandomString(len);
        // 生成UUID
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-","");
        // 存入redis
        if (ApiConstants.VALIDATE_CODE_SAVE_TIME <= 0 ) {
            RedisUtil.setForever(uuid, validateCode);
        } else {
            RedisUtil.set(uuid,validateCode, ApiConstants.VALIDATE_CODE_SAVE_TIME);
        }

        return uuid;
    }
}
