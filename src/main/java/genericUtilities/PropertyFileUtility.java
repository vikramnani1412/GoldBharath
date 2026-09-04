package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class consists of generic methods related to property file
 * @author vikram
 *
 */
public class PropertyFileUtility {

	/**
	 * This method will read the value for a given key from the
	 * common property file
	 * @param key the property key to look up
	 * @return the property value
	 * @throws IOException if the property file cannot be read
	 * @throws IllegalArgumentException if the key is not found in the file
	 */
	public String readDataFromPropertyFile(String key) throws IOException {
	    Properties prop = new Properties();

	    // ✅ Load directly from classpath (src/test/resources)
	    try (InputStream is = getClass().getClassLoader().getResourceAsStream("CommonData.properties")) {
	        if (is == null) {
	            throw new FileNotFoundException("CommonData.properties not found in resources");
	        }
	        prop.load(is);
	    }

	    // ✅ Get the property value
	    String value = prop.getProperty(key);

	    if (value == null) {
	        throw new IllegalArgumentException(
	            "Property key '" + key + "' not found in CommonData.properties"
	        );
	    }

	    return value;
	}
}