package com.ning.breakout.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RankingListModelTest {

    @Test
    void testRecordsAfterSorting() {
        RankingListModel rankingList = new RankingListModel();
        assertEquals(0, rankingList.getRankingList().size());

        rankingList.readIn();
        rankingList.writeBack();

        for (int i = 0; i < rankingList.getRankingList().size() - 1; i++) {
            int former = Integer.parseInt(
                rankingList.getRankingList().get(i).split(",")[0]);
            int latter = Integer.parseInt(
                rankingList.getRankingList().get(i + 1).split(",")[0]);

            assertTrue(former >= latter);
        }
    }

}