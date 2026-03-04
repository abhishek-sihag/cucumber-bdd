package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyUtil {
    public static final Logger LOG = LogManager.getLogger(PropertyUtil.class);
    private static PropertyUtil instance;
    private static final Properties properties = new Properties();

    private PropertyUtil(){
        loadBaseProperties();
    }

    public static PropertyUtil getInstance(){
        if(instance==null){
            synchronized (PropertyUtil.class){
                if(instance == null){
                    instance = new PropertyUtil();
                }
            }
        }
        return instance;
    }

    public String get(String key){
        return properties.getProperty(key);
    }

    public String get(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }

    public Properties getAll(){
        return properties;
    }

    private void loadFile(String filename) {
        try {
            InputStream in = tryFileSystemThenClasspath(filename);
            if (in != null) {
                Properties fileProps = new Properties();
                fileProps.load(in);
                properties.putAll(fileProps);
            }
        } catch (IOException e) {
            LOG.warn("Unable to load properties from: {}", filename, e);
        }
    }

    private InputStream tryFileSystemThenClasspath(String filename) throws IOException {
        // Load from file system first
        try {
            return new FileInputStream(filename);
        } catch (IOException ignored) {}

        // Then classpath
        return PropertyUtil.class.getClassLoader().getResourceAsStream(filename);
    }

    private void loadBaseProperties(){
        loadFile("src/test/resources/properties/default.properties");
        loadFile("src/test/resources/properties/apiSpecs.properties");
        loadFile("src/test/resources/properties/db.properties");
        loadFile("application.properties");
    }

    public void loadTestProperties(String testName) {
        String testFile = "src/test/resources/properties/" + testName + ".properties";
        loadFile(testFile);
    }
}
