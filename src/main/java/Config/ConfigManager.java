package Config;

import java.io.*;
import java.util.HashMap;

public class ConfigManager {
    public static boolean createConfig() {
        Writer w = null;
        try {
            File config = new File("config.yml");
            if(config.createNewFile()) {
                w = new BufferedWriter(new FileWriter(config));
                w.write("MySQL-HOST: null");
                w.write("MySQL-PORT: 3306");
                w.write("MySQL-DB: null");
                w.write("MySQL-USER: null");
                w.write("MySQL-PASSWORD: null");
                w.write("version: alpha-build#003");
                w.flush();
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                w.close();
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
            config = new File("config.yml");
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
