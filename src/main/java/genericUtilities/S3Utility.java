package genericUtilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

public class S3Utility {

	private final S3Client s3Client;

    public S3Utility() {

        // Change this to your AWS region
        Region region = Region.AP_SOUTH_1;

        s3Client = S3Client.builder()
                .region(region)
                .build();
    }

    /**
     * Downloads a file from S3 and returns the local file path.
     *
     * @param bucketName S3 bucket name
     * @param s3Key      S3 object key
     * @param localFile  Local file path
     * @return downloaded file path
     */
    public String downloadFile(String bucketName,
                               String s3Key,
                               String localFile) {

        try {

            Path destination = Paths.get(localFile);

            // Create parent directory if it doesn't exist
            if (destination.getParent() != null) {
                Files.createDirectories(destination.getParent());
            }

            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(s3Key)
                    .build();

            s3Client.getObject(request, destination);

            System.out.println("========================================");
            System.out.println("S3 File Downloaded Successfully");
            System.out.println("Bucket : " + bucketName);
            System.out.println("S3 Key : " + s3Key);
            System.out.println("Local  : " + destination.toAbsolutePath());
            System.out.println("========================================");

            return destination.toAbsolutePath().toString();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to download file from S3: " + s3Key,
                    e);
        }
    }

    /**
     * Close S3 client.
     */
    public void close() {

        if (s3Client != null) {
            s3Client.close();
        }
    }
	
}
