package group.project;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class serviceHolder {
    private String service;
    private String rate;
    //private Drawable image;

    public serviceHolder(String service, String rate) {
        this.service = service;
        this.rate = rate;
        //this.image = image;
    }

    public serviceHolder(){

    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    //public Drawable getImage() {
   //     return image;
  //  }

   // public void setImage(Drawable image) {
   //     this.image = image;
   // }
}
