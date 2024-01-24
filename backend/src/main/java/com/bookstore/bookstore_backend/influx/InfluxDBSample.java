// import java.util.Arrays;
// import com.influxdb.client.InfluxDBClient;
// import com.influxdb.client.InfluxDBClientFactory;
// import com.influxdb.client.domain.*;

// public class InfluxDBSample {

//     private static char[] token = "5iyC7-_sDQULhy7gYYRQhgPDInv7aXlI0TFPMQhd51l17A9jDdz2iOhGa91xRsbPQMQz9zTe6yK9Ls49fHJKLQ==".toCharArray();

//     public static void main(final String[] args) {

//         InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token);

//         //
//         // Create bucket "iot_bucket" with data retention set to 3,600 seconds
//         //
//         var retention = new BucketRetentionRules();
//         retention.setEverySeconds(3600);

//         Bucket bucket = influxDBClient.getBucketsApi().createBucket("iot-bucket", retention, "9f35c0d435b51094");

//         //
//         // Create access token to "iot_bucket"
//         //
//         PermissionResource resource = new PermissionResource();
//         resource.setId(bucket.getId());
//         resource.setOrgID("9f35c0d435b51094");
//         resource.setType(PermissionResource.TYPE_BUCKETS);

//         // Read permission
//         Permission read = new Permission();
//         read.setResource(resource);
//         read.setAction(Permission.ActionEnum.READ);

//         // Write permission
//         Permission write = new Permission();
//         write.setResource(resource);
//         write.setAction(Permission.ActionEnum.WRITE);

//         Authorization authorization = influxDBClient.getAuthorizationsApi()
//                 .createAuthorization("9f35c0d435b51094", Arrays.asList(read, write));

//         //
//         // Created token that can be use for writes to "iot_bucket"
//         //
//         String token = authorization.getToken();
//         System.out.println("Token: " + token);

//         influxDBClient.close();
//     }
// }