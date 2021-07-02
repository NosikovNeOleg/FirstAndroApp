package room.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class City {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "City")
    public String nameCity;

}
