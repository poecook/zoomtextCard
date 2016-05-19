package mysharesdkdemo.weshpe.com.zoomtestcard;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * Created by drummor on 2016/5/19.
 */
public class zip4jDemo
{
    private void test(){
        try {
            ZipFile zipFile = new ZipFile("");
            zipFile.extractAll("");
            if(zipFile.isEncrypted()){
                zipFile.setPassword("");
            }
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
