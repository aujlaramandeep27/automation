package utils;


import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class FileUtils {
	
	public InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
	
	public File getFileFromResource(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
       
		try {
			return Paths.get(resource.toURI()).toFile();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("file not found! " + fileName);
		}

    }
	
	public String getFilePathFromResource(String fileName) {

        return getFileFromResource(fileName).getAbsolutePath();
       
    }

}
