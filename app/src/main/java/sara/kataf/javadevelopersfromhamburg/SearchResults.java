package sara.kataf.javadevelopersfromhamburg;

import java.util.List;

//this class is to used for showing
// the results of the json parse
public class SearchResults {
    private int total_count;
    private boolean incomplete_results;
    private List<User> items;
    private String text_matches;

    public int getTotal_count() {
        return total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public List<User> getItems() {
        return items;
    }
}
