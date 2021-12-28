/*
CryptoJS v3.1.2
code.google.com/p/crypto-js
(c) 2009-2013 by Jeff Mott. All rights reserved.
code.google.com/p/crypto-js/wiki/License
*/
/**
 * Electronic Codebook block mode.
 */
CryptoJS.mode.ECB = (function () {
    var ECB = CryptoJS.lib.BlockCipherMode.extend();

    ECB.Encryptor = ECB.extend({
        processBlock: function (words, offset) {
            this._cipher.encryptBlock(words, offset);
        }
    });

    ECB.Decryptor = ECB.extend({
        processBlock: function (words, offset) {
            this._cipher.decryptBlock(words, offset);
        }
    });

    return ECB;
}());

/**
 * RSA加密
 * @param data 要加密的字符串
 * @param publicKey 公钥对象
 */
function encodeRSA(data,publicKey){
    var modulus = publicKey.modulus, exponent = publicKey.exponent;
    //根据公钥系数生成公钥对象
    var rsaKey = RSAUtils.getKeyPair(exponent, '', modulus);
    //rsa对key加密
    var encodeKey=RSAUtils.encryptedString(rsaKey, data);
    return encodeKey;
}
/**
 * DES加密
 * @param data json对象
 * @param desKey 加密key
 * @returns {*}
 */
function encodeDES(data,desKey){
    var dataStr=JSON.stringify(data);
    //des加密
    var keyHex = CryptoJS.enc.Utf8.parse(desKey);
    var encrypted = CryptoJS.DES.encrypt(dataStr, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    //加密后的数据
    var encodeText =  encrypted.toString();
    return encodeText;
}
/**
 * DES解密
 * @param data
 * @param desKey
 */
function decryptDES(data,desKey){
    var keyHex = CryptoJS.enc.Utf8.parse(desKey);
    // direct decrypt ciphertext
    var decrypted = CryptoJS.DES.decrypt({
        ciphertext: CryptoJS.enc.Base64.parse(data)
    }, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    var result=decrypted.toString(CryptoJS.enc.Utf8);
    return result;
}
