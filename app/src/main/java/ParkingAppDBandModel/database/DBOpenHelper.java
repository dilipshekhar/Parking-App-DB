package ParkingAppDBandModel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final Integer DATABASE_VERSION = 5;
    private static final String TEXT_TYPE = "TEXT";
    private static final String INTEGER_TYPE = "INTEGER";
    private static final String COMMA = ",";

    private static final String CREATE_PARKING_SPOT_TABLE = "CREATE TABLE "
            + DatabaseContract.ParkingSpotTable.TABLE_NAME + " (spot_name TEXT, latitude TEXT, longitude TEXT);";

    public static final String DROP_PARKING_SPOT_TABLE = "DROP TABLE IF EXISTS " + DatabaseContract.ParkingSpotTable.TABLE_NAME;
    public DBOpenHelper(Context context) {
        super(context, DatabaseContract.DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DBOpenHelper", "create db");
        sqLiteDatabase.execSQL(CREATE_PARKING_SPOT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_PARKING_SPOT_TABLE);
        onCreate(sqLiteDatabase);
    }
}
