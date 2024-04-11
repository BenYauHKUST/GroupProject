package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T3Analysis {
    public ObservableList<RecommendItem> RecommendList = FXCollections.observableArrayList();

    T3Analysis (String top_input, String bottom_input, String type, String region) {
        bottom_input = Integer.parseInt(bottom_input);
        top_input = Integer.parseInt(top_input);
        HashMap<string, RecommendItem> temp = HashMap<>();
        for (QSItem item : QSList.list) {
            if (Integer.parseInt(item.getRank()) >= bottom_input && Integer.parseInt(item.getRank()) <= top_input
                    && item.getType().equals(type) && item.region.equals(region)) {
                if (temp.containsKey(item.getName()))
                    temp[item.getName()].update(item);
                else
                    temp[item.getName()] = RecommendItem(item);
            }
        }
        RecommendList = FXCollections.observableArrayList(temp.values());
        Comparator<RecommendItem> bestRankComparator = Comparator.comparingInt(RItem -> Integer.parseInt(RItem.getBestRank()));
        RecommendList.sort(bestRankComparator);
        /*
            Your Code Here.
            Collect the QSItem which fit the score range, type and region into the RecommendItem.
            Sort the RecommendList by bestRank.
            Hint: QSList.list is a static property and you can use "update" function in RecommendItem.
         */
    }

    ObservableList<RecommendItem> getRecommendData() {
        // Show the most valuable university
        return RecommendList;
    }
}
