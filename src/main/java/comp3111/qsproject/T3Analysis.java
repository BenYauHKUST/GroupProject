package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.List;
import java.util.Map;

public class T3Analysis {
    public ObservableList<RecommendItem> RecommendList = FXCollections.observableArrayList();
    T3Analysis (String top_input, String bottom_input, String type, String region, String countryInput) {
        String country = countryInput != null? countryInput : "All";
        int bottom_num = Integer.parseInt(bottom_input);
        int top_num = Integer.parseInt(top_input);
        HashMap<String, RecommendItem> temp = new HashMap<>();
        String mostRecentYear = QSList.list.getLast().year;
        for (int i = QSList.list.size()-1; i >= 0; i--) {
            QSItem item = QSList.list.get(i);
            int rank = Integer.parseInt(item.getRank());
            if (rank <= bottom_num && rank >= top_num)
                if (type.equals("All") || item.getType().equals(type))
                    if (region.equals("All") || item.region.equals(region))
                        if (country.equals("All") || item.country.equals(country)) {
                            if (temp.containsKey(item.getName()))
                                temp.get(item.getName()).update(item);
                            else if (mostRecentYear.equals(item.year))
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
