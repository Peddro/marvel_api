
package farfetch.test.marvel.data.business.models.story;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import farfetch.test.marvel.data.business.models._base.Summary;
import java.util.ArrayList;
import java.util.List;

public class Story implements Parcelable {

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
    private List<Summary> storiesSummaryList = null;

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

    public List<Summary> getStoriesSummaryList() {
        if (storiesSummaryList == null) {
            storiesSummaryList = new ArrayList<>();
        }
        return storiesSummaryList;
    }

    public void setStoriesSummaryList(List<Summary> storiesSummaryList) {
        this.storiesSummaryList = storiesSummaryList;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.available);
        dest.writeString(this.returned);
        dest.writeString(this.collectionURI);
        dest.writeTypedList(this.storiesSummaryList);
    }

    public Story() {
    }

    protected Story(Parcel in) {
        this.available = in.readString();
        this.returned = in.readString();
        this.collectionURI = in.readString();
        this.storiesSummaryList = in.createTypedArrayList(Summary.CREATOR);
    }

    public static final Parcelable.Creator<Story> CREATOR = new Parcelable.Creator<Story>() {
        @Override public Story createFromParcel(Parcel source) {
            return new Story(source);
        }

        @Override public Story[] newArray(int size) {
            return new Story[size];
        }
    };
}
