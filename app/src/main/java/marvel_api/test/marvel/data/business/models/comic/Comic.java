package marvel_api.test.marvel.data.business.models.comic;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import marvel_api.test.marvel.data.business.models._base.Summary;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class Comic implements Parcelable {

  @SerializedName("available") @Expose private String available;
  @SerializedName("returned") @Expose private String returned;
  @SerializedName("collectionURI") @Expose private String collectionURI;
  @SerializedName("items") @Expose private List<Summary> comicSummaryList = null;

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

  public List<Summary> getComicSummaryList() {
    if (comicSummaryList == null) {
      comicSummaryList = new ArrayList<>();
    }
    return comicSummaryList;
  }

  public void setComicSummary(List<Summary> comicSummaryList) {
    this.comicSummaryList = comicSummaryList;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.available);
    dest.writeString(this.returned);
    dest.writeString(this.collectionURI);
    dest.writeList(this.comicSummaryList);
  }

  public Comic() {
  }

  protected Comic(Parcel in) {
    this.available = in.readString();
    this.returned = in.readString();
    this.collectionURI = in.readString();
    this.comicSummaryList = new ArrayList<Summary>();
    in.readList(this.comicSummaryList, Summary.class.getClassLoader());
  }

  public static final Parcelable.Creator<Comic> CREATOR = new Parcelable.Creator<Comic>() {
    @Override public Comic createFromParcel(Parcel source) {
      return new Comic(source);
    }

    @Override public Comic[] newArray(int size) {
      return new Comic[size];
    }
  };
}
