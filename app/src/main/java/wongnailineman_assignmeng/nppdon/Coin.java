package wongnailineman_assignmeng.nppdon;

public class Coin {
    private String name;
    private String description;
    private String imgUrl;
    private String iconType;


    public  Coin(){

    }

    public Coin(String name, String description, String imgUrl,String iconType) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.iconType = iconType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }
}
