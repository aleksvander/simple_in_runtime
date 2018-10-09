package ru.alex.novotelnov.solution.search;

import org.springframework.stereotype.Component;
import ru.alex.novotelnov.solution.search.enums.SearchType;

@Component
public class Search {
    private SearchType selectedSearchType = SearchType.TITLE;

    public SearchType getSelectedSearchType() {
        return selectedSearchType;
    }

    public void setSelectedSearchType(SearchType selectedSearchType) {
        this.selectedSearchType = selectedSearchType;
    }

    public SearchType[] getSearchTypes()
    {
        return SearchType.values();
    }
}
