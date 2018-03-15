package publiccloud.example.com.popgridview;

/**
 * Created by mac on 18/3/13.
 */

public class GvDate {
    private String name;
    private String img;//图标
    private  boolean state;
    private  int num;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public GvDate(int num,boolean state,String name, String icon) {
        this.num=num;
        this.state=state;
        this.name = name;
        this.img = icon;
    }

    public GvDate(String name, String icon) {
        this.name = name;
        this.img = icon;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "GvDate{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", state=" + state +
                ", num=" + num +
                '}';
    }
}
