
package marvel_api.test.marvel.data.business.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail implements Parcelable {

    public static final String PORTRAIT_SMALL = "portrait_small";
    public static final String PORTRAIT_MEDIUM = "portrait_medium";

    public static final String LANDSCAPE_SMALL = "landscape_small";
    public static final String LANDSCAPE_MEDIUM = "landscape_medium";
    public static final String LANDSCAPE_LARGE = "landscape_large";

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("extension")
    @Expose
    private String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFullPath(String portrait) {
        return String.format("%s/%s.%s", path, portrait, extension);
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.extension);
    }

    public Thumbnail() {
    }

    protected Thumbnail(Parcel in) {
        this.path = in.readString();
        this.extension = in.readString();
    }

    public static final Parcelable.Creator<Thumbnail> CREATOR = new Parcelable.Creator<Thumbnail>() {
        @Override public Thumbnail createFromParcel(Parcel source) {
            return new Thumbnail(source);
        }

        @Override public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    };
}
