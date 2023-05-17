package cz.educanet;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

@Entity
@Named
@RequestScoped
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;
    @Column
    private Sloupec sloupec;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Sloupec getSloupec() {
        return sloupec;
    }

    public void setSloupec(Sloupec sloupec) {
        this.sloupec = sloupec;
    }
}
