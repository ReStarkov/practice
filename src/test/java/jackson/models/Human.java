package jackson.models;

public class Human {
    private String name;
    private String sex;
    private String profession;

    public Human(String name, String sex, String profession) {
        this.name = name;
        this.sex = sex;
        this.profession = profession;
    }

    public Human() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
