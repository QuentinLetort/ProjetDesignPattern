package edu.insightr.gildedrose;

public class ConjuredStrategy implements UpdateQualityStrategy {
    @Override
    public void updateQuality(Item item) {
        if( item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 2);
        }
        if ( item.getQuality() >0
                && item.getSellIn() <= 0){
            item.setQuality(item.getQuality() -2);
        }
        if(item.getQuality()<0){
            item.setQuality(0);
        }
        item.setSellIn(item.getSellIn() -1);
    }
}
