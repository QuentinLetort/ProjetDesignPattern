package edu.insightr.gildedrose;

public class AgedBrieStrategy implements UpdateQualityStrategy {
    @Override
    public void updateQuality(Item item) {
        if( item.getQuality() < 50){
            item.setQuality(item.getQuality() +1);
        }
        item.setSellIn((item.getSellIn() -1));
    }
}
