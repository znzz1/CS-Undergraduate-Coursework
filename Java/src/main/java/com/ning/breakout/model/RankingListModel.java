package com.ning.breakout.model;

import java.io.*;
import java.util.ArrayList;

/**
 * Model class of the ranking list, which provides functions to sort records and
 * stores the history top <b>5</b> records after sorting.
 *
 * @author Ning ZHU
 */
public class RankingListModel {

    private ArrayList<String> m_RankingList;


    /**
     * Returns the array list to store records.
     * <p>
     * Stores the history top <b>5</b> records after sorting.
     *
     * @return the array list to store records
     */
    public ArrayList<String> getRankingList() {
        return this.m_RankingList;
    }


    private void setRankingList(ArrayList<String> list) {
        this.m_RankingList = list;
    }


    /**
     * The class constructor initializes the empty array list which used to
     * store records.
     */
    public RankingListModel() {
        setRankingList(new ArrayList<>());
    }


    /**
     * Reads in the records from the history score file.
     * <p>
     * When calling this method, the new record has already been written to the
     * end of the file. In that case, six records (first five are the old top
     * <b>5</b> records while the last one is the new record) will be read into
     * the array list after calling this method.
     */
    public void readIn() {
        File file = new File(
            "src/main/resources/com/ning/breakout/historyScore.csv");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                getRankingList().add(tempString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Sorts the arraylist to get new top <b>5</b> records.
     * <p>
     * Compares the new record with the old records in turn, starting from the
     * smallest and gradually moving forward, until find the record whose score
     * bigger than the new one. Records the index and adds a copy of the new
     * record to the array list by the index. Now, there will be seven records
     * in the array list and the first six records are sorted.
     */
    private void sort() {
        String record = getRankingList().get(getRankingList().size() - 1);
        int SCORE = 0;
        String score = record.split(",")[SCORE];
        int rank = getRankingList().size() - 1;
        for (int i = rank; i > 0; i--) {
            String tmp = getRankingList().get(i - 1).split(",")[SCORE];
            if (Integer.parseInt(score) > Integer.parseInt(tmp)) {
                rank--;
            } else {
                break;
            }
        }
        getRankingList().add(rank, record);
    }


    /**
     * Writes the sorted records back to the history score file.
     * <p>
     * After sorting, there will be seven records in the array list of records.
     * The first six records in the list are sorted from highest to lowest by
     * the score of the record. Writes the first five records back to save the
     * history top <b>5</b> records.
     */
    public void writeBack() {
        sort();
        File file = new File(
            "src/main/resources/com/ning/breakout/historyScore.csv");
        BufferedWriter bw = null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            int TOP_FIVE = 5;
            for (int i = 0; i < TOP_FIVE; i++) {
                bw.write(getRankingList().get(i));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
