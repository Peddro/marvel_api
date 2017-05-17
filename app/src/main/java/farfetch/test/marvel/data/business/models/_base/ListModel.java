package farfetch.test.marvel.data.business.models._base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public abstract class ListModel<T> {

  @SerializedName("offset")
  @Expose
  private String offset;
  @SerializedName("limit")
  @Expose
  private String limit;
  @SerializedName("total")
  @Expose
  private String total;
  @SerializedName("count")
  @Expose
  private String count;

  @SerializedName("results")
  @Expose
  private List<T> modelList = null;

  public List<T> getList() {
    return modelList;
  }

  public void setList(List<T> list) {
    this.modelList = list;
  }


  public String getOffset() {
    return offset;
  }

  public void setOffset(String offset) {
    this.offset = offset;
  }

  public String getLimit() {
    return limit;
  }

  public void setLimit(String limit) {
    this.limit = limit;
  }

  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

}
