// import java.time.Instant;
// import java.util.Iterator;
// import java.util.List;

// import com.influxdb.client.InfluxDBClient;
// import com.influxdb.client.InfluxDBClientFactory;
// import com.influxdb.client.WriteApi;
// import com.influxdb.client.domain.WritePrecision;
// import com.influxdb.client.write.Point;
// import com.influxdb.query.FluxRecord;
// import com.influxdb.query.FluxTable;

// public class InfluxDB2Example {
//     public static void main(final String[] args) {

//         // You can generate a Token from the "Tokens Tab" in the UI
//         String token = "5iyC7-_sDQULhy7gYYRQhgPDInv7aXlI0TFPMQhd51l17A9jDdz2iOhGa91xRsbPQMQz9zTe6yK9Ls49fHJKLQ==";
//         String bucket = "iot-bucket";
//         String org = "SJTU";

//         InfluxDBClient client = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());

//         String data = "mem,host=host1 used_percent=23.43234543";
//         try (WriteApi writeApi = client.getWriteApi()) {
//             writeApi.writeRecord(bucket, org, WritePrecision.NS, data);
//         }

//         Point point = Point
//                 .measurement("mem")
//                 .addTag("host", "host1")
//                 .addField("used_percent", 23.43234543)
//                 .time(Instant.now(), WritePrecision.NS);

//         try (WriteApi writeApi = client.getWriteApi()) {
//             writeApi.writePoint(bucket, org, point);
//         }

//         Mem mem = new Mem();
//         mem.host = "host1";
//         mem.used_percent = 23.43234543;
//         mem.time = Instant.now();

//         try (WriteApi writeApi = client.getWriteApi()) {
//             writeApi.writeMeasurement(bucket, org, WritePrecision.NS, mem);
//         }

//         String query = String.format("from(bucket: \"%s\") |> range(start: -1h)", bucket);
//         List<FluxTable> tables = client.getQueryApi().query(query, org);
//         Iterator<FluxTable> it = tables.iterator();
//         while (it.hasNext()) {
//             FluxTable ft = it.next();
//             List<FluxRecord> records = ft.getRecords();
//             for ( FluxRecord record: records) {
//                 System.out.println(record.getValues());
//             }
//         }
//     }
// }