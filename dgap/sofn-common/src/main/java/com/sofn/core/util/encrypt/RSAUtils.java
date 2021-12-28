package com.sofn.core.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidParameterException;
import java.security.interfaces.RSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import javax.crypto.Cipher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sofn.core.util.RedisUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * RSA算法加密/解密工具类。
 * 初次使用时将生成若干个密钥对，公钥和私钥缓存在自定义缓存中
 * RSA速度  :
 * 由于进行的都是大数计算，使得RSA最快的情况也比DES慢上100倍，无论 是软件还是硬件实现。  
 * 速度一直是RSA的缺陷。一般来说只用于少量数据 加密。
 * 当你需要更改公钥和私钥时，请删除__RSA_PAIR.txt,以及修改generateKeyPair()方法中的
 * new SecureRandom()构造数字生成器
 */
public final class RSAUtils {
	/** 日志记录对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtils.class);

    /** 密钥大小 */
    private static final int KEY_SIZE = 1024;
    /** 算法名称 */
    private static final String ALGORITHOM = "RSA";
    /** 默认的安全服务提供者 */
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    //定义各系统的所代表的枚举变量
    public enum Module{
        //追溯系统
        TTS,
        //监管系统
        ASMS,
        //执法系统
        ALES,
        //后台系统
        SYS,
        //检测系统
        ADS,
        //分析决策系统
        DSS,
        //门户系统
        PORTAL
    }
    
    /** 定义rsa密钥缓存 */
//    private static Map<String,KeyPair> rsaMap;
    /** 初始化密钥组，案例初始化  */
    static {
//    	rsaMap=new HashMap(){{
//    		/** 初始化后台管理系统密钥 */
//    		this.put("system", generateKeyPair());
//    	}};
        //初始化生成密钥组
        init();
    }

    /**
     * 初始化生成各系统的密钥组
     */
    public static void init() {
        if(RedisUtil.exists(ALGORITHOM,-1))
            return;
        HashMap<String,RSAKeyPair> rsaMap=new HashMap<String, RSAKeyPair>();
        //追溯系统
        KeyPair kp=generateKeyPair();
        RSAPublicKey pubKey= (RSAPublicKey) kp.getPublic();
        RSAPrivateKey priKey=(RSAPrivateKey)kp.getPrivate();
        System.out.println(pubKey.getModulus().equals(priKey.getModulus()));
        rsaMap.put(Module.TTS.name(), new RSAKeyPair(pubKey.getModulus(),priKey.getPrivateExponent(),pubKey.getPublicExponent()));
        //监管系统
        kp=generateKeyPair();
        pubKey= (RSAPublicKey) kp.getPublic();
        priKey=(RSAPrivateKey)kp.getPrivate();
        rsaMap.put(Module.ASMS.name(), new RSAKeyPair(pubKey.getModulus(),priKey.getPrivateExponent(),pubKey.getPublicExponent()));
        //执法系统
        kp=generateKeyPair();
        pubKey= (RSAPublicKey) kp.getPublic();
        priKey=(RSAPrivateKey)kp.getPrivate();
        rsaMap.put(Module.ALES.name(), new RSAKeyPair(pubKey.getModulus(),priKey.getPrivateExponent(),pubKey.getPublicExponent()));
        //后台系统
        kp=generateKeyPair();
        pubKey= (RSAPublicKey) kp.getPublic();
        priKey=(RSAPrivateKey)kp.getPrivate();
        rsaMap.put(Module.SYS.name(), new RSAKeyPair(pubKey.getModulus(),priKey.getPrivateExponent(),pubKey.getPublicExponent()));
        //检测系统
        kp=generateKeyPair();
        pubKey= (RSAPublicKey) kp.getPublic();
        priKey=(RSAPrivateKey)kp.getPrivate();
        rsaMap.put(Module.ADS.name(), new RSAKeyPair(pubKey.getModulus(),priKey.getPrivateExponent(),pubKey.getPublicExponent()));
        //分析决策系统
        kp=generateKeyPair();
        pubKey= (RSAPublicKey) kp.getPublic();
        priKey=(RSAPrivateKey)kp.getPrivate();
        rsaMap.put(Module.DSS.name(), new RSAKeyPair(pubKey.getModulus(),priKey.getPrivateExponent(),pubKey.getPublicExponent()));
        //门户系统
        kp=generateKeyPair();
        pubKey= (RSAPublicKey) kp.getPublic();
        priKey=(RSAPrivateKey)kp.getPrivate();
        rsaMap.put(Module.PORTAL.name(), new RSAKeyPair(pubKey.getModulus(),priKey.getPrivateExponent(),pubKey.getPublicExponent()));
        RedisUtil.setForever(ALGORITHOM,rsaMap);
    }
    /**
     * 获取公钥
     * 根据系统关键字获取该系统的公钥对象
     * @param sysName 系统名称
     * @return
     */
    public static RSAPublicKey getRSAPublicKey(String sysName){
        HashMap<String,RSAKeyPair> rsaMap= (HashMap<String, RSAKeyPair>) RedisUtil.get(ALGORITHOM,-1);
    	if(rsaMap.containsKey(sysName)){
            RSAKeyPair kp=(RSAKeyPair)rsaMap.get(sysName);
    		return getRSAPublidKey(kp.getHexModulus(),kp.getHexPublicExponent());
    	}
    	return null;
    }

    /**
     * 获取私钥
     * 根据系统关键字获取该系统的私钥对象
     * @param sysName 系统名称
     * @return
     */
    public static RSAPrivateKey getRSAPrivateKey(String sysName){
        HashMap<String,RSAKeyPair> rsaMap= (HashMap<String, RSAKeyPair>) RedisUtil.get(ALGORITHOM,-1);
        if(rsaMap.containsKey(sysName)){
            RSAKeyPair kp=(RSAKeyPair)rsaMap.get(sysName);
            return getRSAPrivateKey(kp.getHexModulus(),kp.getHexPrivateExponent());
        }
        return null;
    }
    /**
     * 根据系统名对应的枚举获取私钥
     * 根据系统关键字获取该系统的私钥对象
     * @param m 系统名称对应的枚举
     * @return
     */
    public static RSAPrivateKey getRSAPrivateKey(Module m){
        HashMap<String,RSAKeyPair> rsaMap= (HashMap<String, RSAKeyPair>) RedisUtil.get(ALGORITHOM,-1);
        if(rsaMap.containsKey(m.name())){
            RSAKeyPair kp=(RSAKeyPair)rsaMap.get(m.name());
            return getRSAPrivateKey(kp.getHexModulus(),kp.getHexPrivateExponent());
        }
        return null;
    }
    
    /**
     * 生成并返回RSA密钥对。
     */
    private static synchronized KeyPair generateKeyPair() {
        try {
        	KeyPairGenerator keyPairGen=KeyPairGenerator.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        	keyPairGen.initialize(KEY_SIZE);
        	KeyPair oneKeyPair = keyPairGen.generateKeyPair();
            return oneKeyPair;
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("KeyPairGenerator not find this algorithm", ex);
        }catch (InvalidParameterException ex) {
            LOGGER.error("KeyPairGenerator does not support a key length of " + KEY_SIZE + ".", ex);
        } catch (NullPointerException ex) {
            LOGGER.error("RSAUtils#KEY_PAIR_GEN is null, can not generate KeyPairGenerator instance.",
                    ex);
        }
        return null;
    }
    
    
    /**
     * 根据给定的系数和专用指数构造一个RSA专用的公钥对象。
     * 
     * @param modulus 系数。
     * @param publicExponent 专用指数。
     * @return RSA专用公钥对象。
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) {
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(modulus),
                new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER).generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("KeyPairGenerator not find this algorithm", ex);
		} catch (InvalidKeySpecException ex) {
            LOGGER.error("RSAPublicKeySpec is unavailable.", ex);
        } catch (NullPointerException ex) {
            LOGGER.error("RSAUtils#KEY_FACTORY is null, can not generate KeyFactory instance.", ex);
        } 
        return null;
    }
    
    /**
     * 根据给定的系数和专用指数构造一个RSA专用的私钥对象。
     * 
     * @param modulus 系数。
     * @param privateExponent 专用指数。
     * @return RSA专用私钥对象。
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) {
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus),
                new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER).generatePrivate(privateKeySpec);
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("KeyPairGenerator not find this algorithm", ex);
		} catch (InvalidKeySpecException ex) {
            LOGGER.error("RSAPrivateKeySpec is unavailable.", ex);
        } catch (NullPointerException ex) {
            LOGGER.error("RSAUtils#KEY_FACTORY is null, can not generate KeyFactory instance.", ex);
        }
        return null;
    }
    /**
     * 根据给定的16进制系数和专用指数字符串构造一个RSA专用的私钥对象。
     * 
     * @param hexModulus 系数。
     * @param hexPrivateExponent 专用指数。
     * @return RSA专用私钥对象。
     */
    public static RSAPrivateKey getRSAPrivateKey(String hexModulus, String hexPrivateExponent) {
        if(StringUtils.isBlank(hexModulus) || StringUtils.isBlank(hexPrivateExponent)) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("hexModulus and hexPrivateExponent cannot be empty. RSAPrivateKey value is null to return.");
            }
            return null;
        }
        byte[] modulus = null;
        byte[] privateExponent = null;
        try {
            modulus = Hex.decodeHex(hexModulus.toCharArray());
            privateExponent = Hex.decodeHex(hexPrivateExponent.toCharArray());
        } catch(DecoderException ex) {
            LOGGER.error("hexModulus or hexPrivateExponent value is invalid. return null(RSAPrivateKey).");
        }
        if(modulus != null && privateExponent != null) {
            return generateRSAPrivateKey(modulus, privateExponent);
        }
        return null;
    }
     
    /**
     * 根据给定的16进制系数和专用指数字符串构造一个RSA专用的公钥对象。
     * 
     * @param hexModulus 系数。
     * @param hexPublicExponent 专用指数。
     * @return RSA专用公钥对象。
     */
    public static RSAPublicKey getRSAPublidKey(String hexModulus, String hexPublicExponent) {
        if(StringUtils.isBlank(hexModulus) || StringUtils.isBlank(hexPublicExponent)) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("hexModulus and hexPublicExponent cannot be empty. return null(RSAPublicKey).");
            }
            return null;
        }
        byte[] modulus = null;
        byte[] publicExponent = null;
        try {
            modulus = Hex.decodeHex(hexModulus.toCharArray());
            publicExponent = Hex.decodeHex(hexPublicExponent.toCharArray());
        } catch(DecoderException ex) {
            LOGGER.error("hexModulus or hexPublicExponent value is invalid. return null(RSAPublicKey).");
        }
        if(modulus != null && publicExponent != null) {
            return generateRSAPublicKey(modulus, publicExponent);
        }
        return null;
    }
    
    /**
     * 使用指定的公钥加密二进制数据。
     * 
     * @param publicKey 给定的公钥。
     * @param data 要加密的数据。
     * @return 加密后的数据。
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.ENCRYPT_MODE, publicKey);
        return ci.doFinal(data);
    }    
    
    /**
     * 使用指定的公钥解密二进制数据。
     * 
     * @param publicKey 给定的公钥。
     * @param data 要加密的数据。
     * @return 加密后的数据。
     */
    public static byte[] decrypt(PublicKey publicKey, byte[] data) throws Exception {
    	Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.DECRYPT_MODE, publicKey);
        return ci.doFinal(data);
    }

    /**
     * 使用指定的私钥解密二进制数据。
     * 
     * @param privateKey 给定的私钥。
     * @param data 要解密的数据。
     * @return 原数据。
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.DECRYPT_MODE, privateKey);
        return ci.doFinal(data);
    }

    /**
     * 使用指定的私钥加密二进制数据。
     * 
     * @param privateKey 给定的私钥。
     * @param data 要解密的数据。
     * @return 原数据。
     */
    public static byte[] encrypt(PrivateKey privateKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.ENCRYPT_MODE, privateKey);
        return ci.doFinal(data);
    }
    
    /**
     * 使用给定的公钥加密给定的字符串。用于js
     * <p />
     * 若 {@code publicKey} 为 {@code null}，或者 {@code plaintext} 为 {@code null} 则返回 {@code
     * null}。
     * 
     * @param publicKey 给定的公钥。
     * @param plaintext 需要加密的字符串。
     * @return 给定字符串的密文。
     */
    public static String encryptString(PublicKey publicKey, String plaintext) {
        if (publicKey == null || plaintext == null) {
            return null;
        }
        byte[] data = plaintext.getBytes();
        try {
            byte[] en_data = encrypt(publicKey, data);
            return new String(Hex.encodeHex(en_data));
        } catch (Exception ex) {
            LOGGER.error(ex.getCause().getMessage());
        }
        return null;
    }

    /**
     * 使用给定的私钥加密给定的字符串。用于js
     * <p />
     * 若 {@code publicKey} 为 {@code null}，或者 {@code plaintext} 为 {@code null} 则返回 {@code
     * null}。
     * 
     * @param privateKey 给定的私钥。
     * @param plaintext 需要加密的字符串。
     * @return 给定字符串的密文。
     */
    public static String encryptString(PrivateKey privateKey, String plaintext) {
        if (privateKey == null || plaintext == null) {
            return null;
        }
        byte[] data = plaintext.getBytes();
        try {
            byte[] en_data = encrypt(privateKey, data);
            return new String(Hex.encodeHex(en_data));
        } catch (Exception ex) {
            LOGGER.error(ex.getCause().getMessage());
        }
        return null;
    }
    /**
     * 使用给定的私钥解密给定的字符串。用于js
     * <p />
     * 若私钥为 {@code null}，或者 {@code encrypttext} 为 {@code null}或空字符串则返回 {@code null}。
     * 私钥不匹配时，返回 {@code null}。
     * 
     * @param privateKey 给定的私钥。
     * @param encrypttext 密文。
     * @return 原文字符串。
     */
    public static String decryptString(PrivateKey privateKey, String encrypttext) {
        if (privateKey == null || StringUtils.isBlank(encrypttext)) {
            return null;
        }
        try {
            byte[] en_data = Hex.decodeHex(encrypttext.toCharArray());
            byte[] data = decrypt(privateKey, en_data);
            //因解密后数据被颠倒，需反序
            char[] dataChars=new String(data).toCharArray();
            StringBuilder sb=new StringBuilder();
            for(int i=dataChars.length-1;i>-1;i--){
            	sb.append(dataChars[i]);
            }
            return sb.toString();
        } catch (Exception ex) {
            LOGGER.error(String.format("\"%s\" Decryption failed. Cause: %s", encrypttext, ex.getCause().getMessage()));
        }
        return null;
    }

    /**
     * 使用给定的私钥解密给定的字符串
     * <p />
     * 若私钥为 {@code null}，或者 {@code encrypttext} 为 {@code null}或空字符串则返回 {@code null}。
     * 私钥不匹配时，返回 {@code null}。
     *
     * @param m 系统对应的枚举。
     * @param encrypttext 密文。
     * @return 原文字符串。
     */
    public static String decryptString(Module m, String encrypttext) {
        PrivateKey privateKey=RSAUtils.getRSAPrivateKey(m);
        if (privateKey == null || StringUtils.isBlank(encrypttext)) {
            return null;
        }
        try {
            byte[] en_data = Hex.decodeHex(encrypttext.toCharArray());
            byte[] data = decrypt(privateKey, en_data);
            //因解密后数据被颠倒，需反序
            char[] dataChars=new String(data).toCharArray();
            StringBuilder sb=new StringBuilder();
            for(int i=dataChars.length-1;i>-1;i--){
                sb.append(dataChars[i]);
            }
            return sb.toString();
        } catch (Exception ex) {
            LOGGER.error(String.format("\"%s\" Decryption failed. Cause: %s", encrypttext, ex.getCause().getMessage()));
        }
        return null;
    }
    /**
     * 使用给定的公钥解密给定的字符串。用于js
     * <p />
     * 若公钥为 {@code null}，或者 {@code encrypttext} 为 {@code null}或空字符串则返回 {@code null}。
     * 公钥不匹配时，返回 {@code null}。
     * 
     * @param publicKey 给定的公钥。
     * @param encrypttext 密文。
     * @return 原文字符串。
     */
    public static String decryptString(PublicKey publicKey, String encrypttext) {
        if (publicKey == null || StringUtils.isBlank(encrypttext)) {
            return null;
        }
        try {
            byte[] en_data = Hex.decodeHex(encrypttext.toCharArray());
            byte[] data = decrypt(publicKey, en_data);
            return new String(data);
        } catch (Exception ex) {
            LOGGER.error(String.format("\"%s\" Decryption failed. Cause: %s", encrypttext, ex.getCause().getMessage()));
        }
        return null;
    }

    /**
     * 根据系统名获取公钥的系数对象Map，用于封装为json
     * @param sysName 系统名称
     * @return
     */
    public static PublicKeyMap getPublicKeyMap(String sysName) {
        PublicKeyMap publicKeyMap = new PublicKeyMap();
        RSAPublicKey rsaPublicKey = getRSAPublicKey(sysName);
        publicKeyMap.setModulus(new String(Hex.encodeHex(rsaPublicKey.getModulus().toByteArray())));//系数
        publicKeyMap.setExponent(new String(Hex.encodeHex(rsaPublicKey.getPublicExponent().toByteArray())));//指数
        return publicKeyMap;
    }
    /**
     * 根据系统名的枚举获取公钥的系数对象Map，用于封装为json
     * @param m 系统名称枚举
     * @return
     */
    public static PublicKeyMap getPublicKeyMap(Module m) {
        //判断Redis里RSA缓存是否为空，若为空则重新初始化
        if(!RedisUtil.exists(ALGORITHOM,-1)) {
            init();
        }

        HashMap<String,RSAKeyPair> rsaMap= (HashMap<String, RSAKeyPair>) RedisUtil.get(ALGORITHOM,-1);
        if(rsaMap.containsKey(m.name())){
            RSAKeyPair kp=(RSAKeyPair)rsaMap.get(m.name());
            return new PublicKeyMap(kp.getHexModulus(),kp.getHexPublicExponent());
        }
        return null;
    }

    //-------------------------------------------------------------------------------

    /**
     * 解密客户端提交过来的密文
     * @param ciphertext 加密后的内容密文
     * @param m 所属系统的枚举
     * @param desKey 加密后的desKey
     * @return 解密后的密文封装为Json对象
     */
    public  static JSONObject decrypt(String ciphertext,Module m,String desKey) throws Exception {
        //解密对称加密的key
        desKey = RSAUtils.decryptString(m, desKey);
        //des解密明文
        byte[] textByte = Base64.decodeBase64(ciphertext);
        String decryptResult = new String(DESUtils.decrypt(textByte, desKey.getBytes()), "utf-8");
        //将字符串转换为JSON对象
        JSONObject json= JSON.parseObject(decryptResult);
        return json;
    }
    /**
     * 解密客户端提交过来的密文
     * @param text 需要加密的内容
     * @param m 所属系统的枚举
     * @param desKey 加密后的desKey
     * @return  加密后的密文
     */
    public  static String encode(String text,Module m,String desKey) throws UnsupportedEncodingException {
        //解密对称加密的key
        desKey = RSAUtils.decryptString(m, desKey);
        //使用客户端传过来的随机key将返回数据进行des加密
        byte[] textResult = DESUtils.encrypt(text.getBytes("utf-8"), desKey.getBytes());
        //将加密后二进制码转换为base64字符串
        String textEncrypt=Base64.encodeBase64String(textResult);
        return textEncrypt;
    }


 }
