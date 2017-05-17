
package farfetch.test.marvel.data.business.models.series;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import farfetch.test.marvel.data.business.models._base.Summary;
import java.util.ArrayList;
import java.util.List;

public class Series implements Parcelable {

    @SerializedName("available")
    @Expose
    private String available;
    @SerializedName("returned")
    @Expose
    private String returned;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private List<Summary> seriesSummaryList = null;

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Summary> getSeriesSummaryList() {
        if (seriesSummaryList == null) {
            seriesSummaryList = new ArrayList<>();
        }
        return seriesSummaryList;
    }

    public void setSeriesSummaryList(List<Summary> seriesSummaryList) {
        this.seriesSummaryList = seriesSummaryList;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.available);
        dest.writeString(this.returned);
        dest.writeString(this.collectionURI);
        dest.writeTypedList(this.seriesSummaryList);
    }

    public Series() {
    }

    protected Series(Parcel in) {
        this.available = in.readString();
        this.returned = in.readString();
        this.collectionURI = in.readString();
        this.seriesSummaryList = in.createTypedArrayList(Summary.CREATOR);
    }

    public static final Parcelable.Creator<Series> CREATOR = new Parcelable.Creator<Series>() {
        @Override public Series createFromParcel(Parcel source) {
            return new Series(source);
        }

        @Override public Series[] newArray(int size) {
            return new Series[size];
        }
    };
}
