import com.java.encrypt.EncryptUtils;

public class EncryptTest {
    public static void main(String[] args) {
        String s = EncryptUtils.INSTANCE.DefaultBase64EncodeAsStr("你好");
        System.out.println(s);
        String s1 = EncryptUtils.INSTANCE.DefaultBase64DecodeAsStr(s);
        System.out.println(s1);
    }
}
