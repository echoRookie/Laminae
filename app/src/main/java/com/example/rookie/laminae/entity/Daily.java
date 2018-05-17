package com.example.rookie.laminae.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by rookie on 2018/5/15.
 */
public class Daily {
    public List<IssueList> issueList;
    public String nextPageUrl;

    public static class IssueList {
        public long releaseTime;
        public String type;
        public long date;
        public long publishTime;
        public int count;
        public List<ItemList> itemList ;
        public class ItemList {
            public String type;
            public Data data;
            public class Data{
                public String dataType;
                public int id;
                public String title;
                public String description;
                public String libraty;
                public String category;
                public Cover cover;
                  public class Cover{
                      public String feed;
                      public String detail;
                      public String blurred;
                  }
                public Author author;
                  public class Author{
                      public int id;
                      public String icon;
                      public String name;
                  }
                public String playUrl;
                public int duration;
            }
        }
    }
}
