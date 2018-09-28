package ParkingAppDBandModel.database;

public class DatabaseContract {

    public static final String DB_NAME = "location.db";

    public abstract class ParkingSpotTable {
        public static final String TABLE_NAME = "parking_spot_table7";

        public static final String TYPE = "type";
        public static final String SPOT_NAME = "spot_name";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String PRICE = "price";
        public static final String HANDICAP = "handicap";
        public static final String LOT_TYPE = "lot_type";
    }
}
