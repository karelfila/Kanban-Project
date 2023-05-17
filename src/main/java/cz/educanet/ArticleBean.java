package cz.educanet;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.util.List;

@ApplicationScoped
@Named
public class ArticleBean {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Kanban");

    public void increment(ArticleEntity articlePar) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createQuery("UPDATE ArticleEntity SET sloupec = :status WHERE id = :id");
        query.setParameter("status",articlePar.getSloupec().increment(articlePar.getSloupec()));
        query.setParameter("id", articlePar.getId());
        query.executeUpdate();

        et.commit();
        em.close();
    }

    public void decrement(ArticleEntity articlePar) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction et = em.getTransaction();
        et.begin();
        Query query = em.createQuery("UPDATE ArticleEntity SET sloupec = :status WHERE id = :id");
        query.setParameter("status",articlePar.getSloupec().decrement(articlePar.getSloupec()));
        query.setParameter("id", articlePar.getId());
        query.executeUpdate();

        et.commit();
        em.close();
    }

    public void addArticle(String title) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction et = em.getTransaction();
        et.begin();

        ArticleEntity article = new ArticleEntity();

        article.setTitle(title);
        article.setSloupec(Sloupec.Backlog);

        em.persist(article);
        et.commit();
        em.close();
    }

    public List<ArticleEntity> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<ArticleEntity> result
                = em.createQuery("SELECT article FROM ArticleEntity AS article", ArticleEntity.class);
        return result.getResultList();
    }

    public List<ArticleEntity> getBacklog() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT ae " +
                        "FROM ArticleEntity AS ae " +
                        "WHERE ae.sloupec = cz.educanet.Sloupec.Backlog", ArticleEntity.class);
        List<ArticleEntity> result = query.getResultList();

        em.close();
        return result;
    }

    public List<ArticleEntity> getInProgress() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT ae " +
                        "FROM ArticleEntity AS ae " +
                        "WHERE ae.sloupec = cz.educanet.Sloupec.InProgress", ArticleEntity.class);
        List<ArticleEntity> result = query.getResultList();

        em.close();
        return result;
    }
    public List<ArticleEntity> getInReview() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT ae " +
                        "FROM ArticleEntity AS ae " +
                        "WHERE ae.sloupec = cz.educanet.Sloupec.InReview", ArticleEntity.class);
        List<ArticleEntity> result = query.getResultList();

        em.close();
        return result;
    }

    public List<ArticleEntity> getTest() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT ae " +
                        "FROM ArticleEntity AS ae " +
                        "WHERE ae.sloupec = cz.educanet.Sloupec.Test", ArticleEntity.class);
        List<ArticleEntity> result = query.getResultList();

        em.close();
        return result;
    }

    public List<ArticleEntity> getFinished() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<ArticleEntity> query = em.createQuery(
                "SELECT ae " +
                        "FROM ArticleEntity AS ae " +
                        "WHERE ae.sloupec = cz.educanet.Sloupec.Test", ArticleEntity.class);
        List<ArticleEntity> result = query.getResultList();

        em.close();
        return result;
    }

    @PreDestroy
    public void onDestroy() {
        emf.close();
    }


}
