package comp3111.qsproject;

/**
 * A class to store the item information for the RecommendList.
 * @author <a href=mailto:ytyuad@connect.ust.hk>YU YU TAO</a>
 * @version 1.0
 */
public class RecommendItem {
    String name;

    String bestYear;

    String bestRank;

    String recentYear;

    String recentRank;

    /**
     * Initialize a RecommendItem with the given QSItem
     * @param item a QSItem used for generation of RecommendItem
     */
    RecommendItem(QSItem item) {
        name = item.name;
        bestYear = item.year;
        bestRank = item.rank;
        recentYear = item.year;
        recentRank = item.rank;
    }

    /**
     * To update the RecommendItem given a new QSItem in a different year
     * @param item a QSItem used for update of RecommendItem
     */
    void update(QSItem item) {
        assert (item.name.equals(name));
        if (Integer.parseInt(item.getRank()) < Integer.parseInt(bestRank)) {
            bestYear = item.year;
            bestRank = item.getRank();
        }
        if (Integer.parseInt(item.year) > Integer.parseInt(recentYear)) {
            recentYear = item.year;
            recentRank = item.getRank();
        }
    }

    public String getName() { return name; }

    public String getBestYear() { return bestYear; }

    public String getBestRank() { return bestRank; }

    public String getRecentYear() { return recentYear; }

    public String getRecentRank() { return recentRank; }
}
