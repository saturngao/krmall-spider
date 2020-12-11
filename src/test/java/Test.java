/**
 * @author 97646
 * @date 2020/12/11
 */
public class Test {

    public static void main(String[] args) {
        byte[] ad = "00068702850".getBytes();
        ad[10] = 0x00;
    }
}
