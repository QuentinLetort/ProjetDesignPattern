package edu.insightr.gildedrose;

public class UpdateQualityFactory {
    public UpdateQualityStrategy getUpdateQualityStrategy(Item item){
        String name=item.getName();
        if (name!=null){
            if(name.contains("Aged Brie")) return new AgedBrieStrategy();
            if(name.contains("Backstage Passes")) return new BackstagePassesStrategy();
            if(name.contains("Sulfuras"))  return new SulfurasStrategy();
            if(name.contains("Conjured")) return new ConjuredStrategy();
            else return new BasicItemStrategy();
        }
        else return null;
    }
}
