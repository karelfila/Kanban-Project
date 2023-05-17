package cz.educanet;

public enum Sloupec {
    Backlog,
    InProgress,
    InReview,
    Test,
    Finished;

    public Sloupec increment(Sloupec currentColumn){
        if (currentColumn == Backlog)
            return InProgress;
        else if (currentColumn == InProgress)
            return InReview;
        else if (currentColumn == InReview)
            return Test;
        else if (currentColumn == Test)
            return Finished;

        return currentColumn;
    }

    public Sloupec decrement(Sloupec currentColumn){
        if (currentColumn == Finished)
            return Test;
        else if (currentColumn == Test)
            return InReview;
        else if (currentColumn == InReview)
            return InProgress;
        else if (currentColumn == InProgress)
            return Backlog;

        return currentColumn;
    }


}
