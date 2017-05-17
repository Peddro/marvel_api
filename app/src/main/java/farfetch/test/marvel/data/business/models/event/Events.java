
package farfetch.test.marvel.data.business.models.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import farfetch.test.marvel.data.business.models._base.Summary;
import java.util.ArrayList;
import java.util.List;

public class Events implements Parcelable {

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
    private List<Summary> eventSummaryList = null;

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

    public List<Summary> getEventSummaryList() {
        if (eventSummaryList == null) {
            eventSummaryList = new ArrayList<>();
        }
        return eventSummaryList;
    }

    public void setEventSummaryList(List<Summary> eventSummaryList) {
        this.eventSummaryList = eventSummaryList;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.available);
        dest.writeString(this.returned);
        dest.writeString(this.collectionURI);
        dest.writeTypedList(this.eventSummaryList);
    }

    public Events() {
    }

    protected Events(Parcel in) {
        this.available = in.readString();
        this.returned = in.readString();
        this.collectionURI = in.readString();
        this.eventSummaryList = in.createTypedArrayList(Summary.CREATOR);
    }

    public static final Parcelable.Creator<Events> CREATOR = new Parcelable.Creator<Events>() {
        @Override public Events createFromParcel(Parcel source) {
            return new Events(source);
        }

        @Override public Events[] newArray(int size) {
            return new Events[size];
        }
    };
}
