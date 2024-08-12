package util;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class ImageUtil {
    public static String getProductImageSrc(ResultSet rs) throws SQLException {
        Blob imageBlob = rs.getBlob("image");
        byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
        return "data:image/jpeg;base64," + new String(Base64.getEncoder().encode(imageBytes));
    }
}
