package edu.insightr.gildedrose;

public class BackstagePassesStrategy implements UpdateQualityStrategy {

    @Override
    public void updateQuality(Item item) {
        if (item.getSellIn() <= 0){
            item.setQuality(item.getQuality() - item.getQuality());
        }
        else {
            if (item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }
            if (item.getSellIn() < 11 && item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }
            if (item.getSellIn() < 6 && item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }
        }

        item.setSellIn((item.getSellIn() -1));

    }
}
