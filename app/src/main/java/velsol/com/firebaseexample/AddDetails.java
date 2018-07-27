package velsol.com.firebaseexample;

/**
 * Created by Velsol 170016 on 7/9/2018.
 */

public class AddDetails
{
    String image,number,area;

    public AddDetails()
    {
    }
    public AddDetails(String image, String number, String area) {
        this.image = image;
        this.number = number;
        this.area = area;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
