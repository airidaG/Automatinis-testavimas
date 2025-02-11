import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreenCartTests extends BaseTest {

    private final String TO_SEARCH = "at";
    private final String TO_SEARCH_MORE_RESULTS = "A";
    private final String INVALID_SEARCH = "z";
    private final String NO_DATA_MESSAGE = "No data";
    private final String PAGE_SIZE_5 = "5";
    private final String PAGE_SIZE_10 = "10";
    private final String PAGE_SIZE_20 = "20";


    @Test
    void pageSizeTest() {
        greenCartPage.selectPageSize(PAGE_SIZE_5);
        assertThat(greenCartPage.getItemNameList().size())
                .isEqualTo(Integer.parseInt(PAGE_SIZE_5));

        greenCartPage.selectPageSize(PAGE_SIZE_10);
        assertThat(greenCartPage.getItemNameList().size())
                .isEqualTo(Integer.parseInt(PAGE_SIZE_10));

        greenCartPage.selectPageSize(PAGE_SIZE_20);
        assertThat(greenCartPage.getItemNameList().size() > Integer.parseInt(PAGE_SIZE_10)
                && greenCartPage.getItemNameList().size() <= Integer.parseInt(PAGE_SIZE_20)).isTrue();
    }

    @Test
    void searchFunctionalityTest() {
        greenCartPage.selectPageSize(PAGE_SIZE_20);
        greenCartPage.enterASearch(TO_SEARCH);
        assertThat(greenCartPage.doesResultContain(TO_SEARCH)).isTrue();
    }

    @Test
    void searchFunctionalityTestWithPages() {
        greenCartPage.selectPageSize(PAGE_SIZE_5);
        greenCartPage.enterASearch(TO_SEARCH_MORE_RESULTS);

        assertThat(greenCartPage.resultContains(TO_SEARCH_MORE_RESULTS, greenCartPage.getNameList())).isTrue();
    }

    @Test
    void invalidSearchTest() {
        greenCartPage.selectPageSize(PAGE_SIZE_20);
        greenCartPage.enterASearch(INVALID_SEARCH);

        assertThat(greenCartPage.getItemNameList())
                .contains(NO_DATA_MESSAGE)
                .doesNotContain(INVALID_SEARCH);
    }

    @Test
    void sortByNameTest() {
        greenCartPage.selectPageSize(PAGE_SIZE_20);
        greenCartPage.sortBy(greenCartPage.getSortByNameOption());
        assertThat(greenCartPage.getFullList(greenCartPage.getNameList())).isSorted();
    }

    @Test
    void sortByPriceTest() {
        greenCartPage.selectPageSize(PAGE_SIZE_20);
        greenCartPage.sortByPrice();
        assertThat(greenCartPage.getItemPriceList()).isSorted();
    }
//--OR-- use more dynamic methods:
//    @Test
//    void sortByPriceTest() {
//        greenCartPage.selectPageSize(PAGE_SIZE_20);
//        greenCartPage.sortBy(greenCartPage.getSortByPriceOption());
//        assertThat(greenCartPage.getFullList(greenCartPage.getPriceList())).isSorted();
//    }

    @Test
    void sortByDiscountTest() {
        greenCartPage.selectPageSize(PAGE_SIZE_20);
        greenCartPage.sortBy(greenCartPage.getSortByDiscountOption());
        assertThat(greenCartPage.getItemDiscountList()).isSorted();
    }

    @Test
    void sortByNameAcrossMultiplePagesTest() {
        greenCartPage.selectPageSize(PAGE_SIZE_5);
        greenCartPage.sortBy(greenCartPage.getSortByNameOption());
        assertThat(greenCartPage.getFullList(greenCartPage.getNameList())).isSorted();
    }

    @Test
    void sortByPriceAcrossMultiplePagesTest() {
        greenCartPage.selectPageSize(PAGE_SIZE_5);
        greenCartPage.sortBy(greenCartPage.getSortByPriceOption());
        assertThat(greenCartPage.getFullList(greenCartPage.getPriceList())).isSorted();
    }

    @Test
    void sortByDiscountAcrossMultiplePagesTest() {
        greenCartPage.selectPageSize(PAGE_SIZE_5);
        greenCartPage.sortBy(greenCartPage.getSortByDiscountOption());
        assertThat(greenCartPage.getFullList(greenCartPage.getDiscountList())).isSorted();
    }
}
