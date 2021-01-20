import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author 97646
 * @date 2020/12/11
 */
public class Test {

    public static void main(String[] args) {
//        byte[] ad = "00068702850".getBytes();
//        ad[10] = 0x00;
        System.out.println(System.getProperty("user.dir"));
        try {
            BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\pom.xml"));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            System.out.println(str);
        } catch (IOException e) {
        }
    }


}
