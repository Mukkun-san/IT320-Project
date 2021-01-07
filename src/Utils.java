
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mukkun
 */
public abstract class Utils {

    public static boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDateStr(String d) {
        return Pattern.compile("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19|20)\\d\\d$", Pattern.CASE_INSENSITIVE).matcher(d).matches();
    }

    public static Date dateFromStr(String str) {
        try {
            return new Date(new SimpleDateFormat("dd-MM-yyyy").parse(str).getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static String dateToStr(Date d) {
        try {
            return (new SimpleDateFormat("dd-MM-yyyy").format(d));
        } catch (Exception e) {
            return null;
        }
    }

    public static void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

}
