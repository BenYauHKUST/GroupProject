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
        int bottom_num = Integer.parseInt(bottom_input);
        int top_num = Integer.parseInt(top_input);
        HashMap<String, RecommendItem> temp = new HashMap<>();
        for (QSItem item : QSList.list) {
            if (Integer.parseInt(item.getRank()) >= bottom_num && Integer.parseInt(item.getRank()) <= top_num
                    && item.getType().equals(type) && item.region.equals(region)) {
                if (temp.containsKey(item.getName()))
                    temp.get(item.getName()).update(item);
                else
                    temp.put(item.getName(), new RecommendItem(item));
            }
        }
        RecommendList = FXCollections.observableArrayList(temp.values());
        Comparator<RecommendItem> bestRankComparator = Comparator.comparingInt(RItem -> Integer.parseInt(RItem.getBestRank()));
        RecommendList.sort(bestRankComparator);
    }

    ObservableList<RecommendItem> getRecommendData() {
        // Show the most valuable university
        return RecommendList;
    }
}
