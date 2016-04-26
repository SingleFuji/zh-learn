package zh_practise.io;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String src = "00000000000000090991";
//        String tar = String.format("", src);
//        System.out.println(tar);
        int tmp = Integer.parseInt(src);
        String res = new BigDecimal(tmp).multiply(new BigDecimal("0.01"/*百分比*/)).toString();
        System.out.println(res);
//        String ftmp = String.format("%.3f", tmp);
//        System.out.println(ftmp);
        System.out.println(String.format("%07d", 88));
        
    }
}
