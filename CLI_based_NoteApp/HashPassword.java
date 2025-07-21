import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class HashPassword {

    private String hash;
    private String salt;

    public String hashPassword(String password, String salt) throws Exception{
        byte[] saltByte = Base64.getDecoder().decode(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltByte, 1000000, 256);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = f.generateSecret(spec).getEncoded();
        System.out.println(Base64.getEncoder().encodeToString(hash));
        return Base64.getEncoder().encodeToString(hash);
    }

    public String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    public void setHash(String hash){
        this.hash = hash;
    }

    public String getSalt(){
        return salt;
    }

    public String getHash(){
        return hash;
    }
}
