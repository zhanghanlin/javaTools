package com.demo.java.utils.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import static com.demo.java.commons.Config.CHARSET;

/**
 * velocity生成HTML
 *
 * @author zhanghanlin
 */
public class VmUtils {

    public static boolean vm2Html(VelocityContext context) {
        String path = "/data/web";
        String vmPath = "/data/web/vm";
        try {
            Properties p = new Properties();
            p.clear();
            //这是模板所在路径
            p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, vmPath);
            p.setProperty(VelocityEngine.INPUT_ENCODING, CHARSET);
            p.setProperty(VelocityEngine.OUTPUT_ENCODING, CHARSET);
            p.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogSystem");

            VelocityEngine ve = new VelocityEngine();
            ve.init(p);
            Velocity.init();

            Template template = ve.getTemplate("test.vm", CHARSET);
            createdPath(path);
            FileOutputStream fos = new FileOutputStream(path + "/test.html");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, CHARSET));
            template.merge(context, writer);

            writer.flush();
            writer.close();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Create File
     *
     * @param path
     */
    private static void createdPath(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
