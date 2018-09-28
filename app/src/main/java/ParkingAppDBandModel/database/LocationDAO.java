package ParkingAppDBandModel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import ParkingAppDBandModel.models.PlaceInfo;

public class LocationDAO {

    private static LocationDAO instance = null;


    public static LocationDAO getInstance() {
        if (instance == null) {
            instance = new LocationDAO();
        }
        return instance;
    }

    public boolean storeLocations(Context context, PlaceInfo place) {
        try {
            SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();
            db.beginTransaction();

            ContentValues cv = new ContentValues();
            cv.put(DatabaseContract.ParkingSpotTable.LATITUDE, Double.toString(place.getLatlng().latitude));
            cv.put(DatabaseContract.ParkingSpotTable.LONGITUDE, Double.toString(place.getLatlng().longitude));
            cv.put(DatabaseContract.ParkingSpotTable.SPOT_NAME, place.getName());

            db.insert(DatabaseContract.ParkingSpotTable.TABLE_NAME,null, cv);

            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();

        } catch (Exception e) {
            return false;

        }
        return true;
    }

    public List<PlaceInfo> getPlacesFromDB(Context context) {
        SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();
        String[] cols = {"spot_name", "latitude", "longitude"};

        Cursor cursor = db.query(DatabaseContract.ParkingSpotTable.TABLE_NAME, cols, null, null, null, null, null);
        List<PlaceInfo> placeInfoList= new ArrayList<>();
        cursor.moveToFirst();
        while(cursor.moveToNext()) {

            String spot_name = cursor.getString(cursor.getColumnIndex(DatabaseContract.ParkingSpotTable.SPOT_NAME));
            String latitude = cursor.getString(cursor.getColumnIndex(DatabaseContract.ParkingSpotTable.LATITUDE));
            String longitude = cursor.getString(cursor.getColumnIndex(DatabaseContract.ParkingSpotTable.LONGITUDE));

            LatLng ll = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
            PlaceInfo p = new PlaceInfo(spot_name, null, null, null, null, ll, 0, null);
            placeInfoList.add(p);


        }
        cursor.close();
        db.close();
        return placeInfoList;

    }
}
