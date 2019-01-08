package Config;

import java.io.*;
import java.util.HashMap;

public class ConfigManager {
    public static boolean createConfig() {
        BufferedWriter bw = null;
        try {
            File config = new File("d\\GIM");
            if(config.createNewFile()) {
                bw = new BufferedWriter(new FileWriter(config));
                bw.write("MySQL-HOST: null");
                bw.write("MySQL-PORT: 3306");
                bw.write("MySQL-DB: null");
                bw.write("MySQL-USER: null");
                bw.write("MySQL-PASSWORD: null");
                bw.newLine();
                bw.write("version: alpha-build#002");
                bw.flush();
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch(IOException e2) {
                e2.printStackTrace();
            } catch(NullPointerException e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }

    public static HashMap<String, String> loadSQLConfigs() {
        BufferedReader br = null;
        File config;
        HashMap<String, String> sqlconfigs= new HashMap<String, String>();
         try {
            if(createConfig()) {
                System.out.println("[G.I.M.S.] SQLの設定をロードしようとしましたが、config.ymlが存在しないため、生成しました。");
            }
            config = new File("d\\GIM");
            br = new BufferedReader(new FileReader(config));
            String read = br.readLine();
            while(read != null) {
                if(read.startsWith("MySQL-HOST")) {
                    sqlconfigs.put("HOST",read.replace("MySQL-HOST: ", ""));
                } else if(read.startsWith("MySQL-PORT")) {
                    sqlconfigs.put("PORT",read.replace("MySQL-PORT: ", ""));
                } else if(read.startsWith("MySQL-DB")) {
                    sqlconfigs.put("DB",read.replace("MySQL-DB: ", ""));
                } else if(read.startsWith("MySQL-USER")) {
                    sqlconfigs.put("USER",read.replace("MySQL-USER: ", ""));
                } else if(read.startsWith("MySQL-PASSWORD")) {
                    sqlconfigs.put("PASSWORD",read.replace("MySQL-PASSWORD: ", ""));
                }
                br.close();
                return sqlconfigs;
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
