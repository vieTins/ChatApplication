/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

/**
 *
 * @author FPTSHOP
 */
// Lớp mã hóa và giải mã Mật khẩu 
public class SecurityKeyPairGenerator {

   public void generateKeyPair(String publicKeyPath, String privateKeyPath) {
    try {
        SecureRandom sr = new SecureRandom();
        // Thuật toán sinh khóa - RSA
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        // Độ dài khóa 1024 bit
        kpg.initialize(1024, sr);
        // Khởi tạo cặp khóa
        KeyPair kp = kpg.genKeyPair();
        // Public key
        PublicKey publicKey = kp.getPublic();
        // Private Key
        PrivateKey privateKey = kp.getPrivate();

        // Kiểm tra và tạo tệp nếu không tồn tại
        File publicKeyFile = createKeyFile(new File(publicKeyPath));
        File privateKeyFile = createKeyFile(new File(privateKeyPath));

        // Lưu Public key nếu tệp chưa tồn tại
        if (publicKeyFile != null) {
            try (FileOutputStream fos = new FileOutputStream(publicKeyFile)) {
                fos.write(publicKey.getEncoded());
            }
        }
        // Lưu Private Key nếu tệp chưa tồn tại
        if (privateKeyFile != null) {
            try (FileOutputStream fos = new FileOutputStream(privateKeyFile)) {
                fos.write(privateKey.getEncoded());
            }
        }
        System.out.println("Generate Key Successfully");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private static File createKeyFile(File file) throws IOException {
    if (!file.exists()) {
        file.createNewFile();
        return file;
    }
    // Trả về null nếu tệp đã tồn tại để bỏ qua ghi tệp
    return null;
}

    public PublicKey getPublicKey(String filePath) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
//        đọc file chứa public key 
        FileInputStream fis = new FileInputStream(filePath);
        byte[] b = new byte[fis.available()];
        fis.read(b);
//            tạo Pulic key từ byte array 
        X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }
//    mã hóa dữ liệu 

    public String encrypt(String message, PublicKey publicKey) throws Exception {
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrpytOut = c.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encrpytOut);
    }
//    giải mã dữ liệu 

    public PrivateKey getPrivateKey(String filePath) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] b = fis.readAllBytes();
//        tạo private key từ byte array 
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

    public String decrypt(String encrpytedMessage, PrivateKey privateKey) throws Exception {
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptOut = c.doFinal(Base64.getDecoder().decode(encrpytedMessage));
        return new String(decryptOut);
    }
}
