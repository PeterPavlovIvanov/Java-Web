package projectdefence.committer.demo.models.bindings;

import org.hibernate.validator.constraints.Length;

public class SearchUsersBindModel {
    private String searchString;

    public SearchUsersBindModel() {
    }

    public SearchUsersBindModel(String searchString) {
        this.searchString = searchString;
    }

    @Length(min = 1, message = "Enter at least 1 symbol.")
    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
