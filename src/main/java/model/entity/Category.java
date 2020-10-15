package model.entity;

public class Category {

    int id;
    String nameEng;
    String nameRu;

    public Category(String nameEng, String nameRu) {
        this.nameEng = nameEng;
        this.nameRu = nameRu;
    }

    public Category(int id, String nameEng, String nameRu) {
        this.id = id;
        this.nameEng = nameEng;
        this.nameRu = nameRu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nameEng='" + nameEng + '\'' +
                ", nameRu='" + nameRu + '\'' +
                '}';
    }
}
