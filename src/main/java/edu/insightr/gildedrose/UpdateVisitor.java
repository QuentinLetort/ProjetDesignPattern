package edu.insightr.gildedrose;

public class UpdateVisitor implements IVisitor {
    @Override
    public void visit(Item item) {
        if(!Inventory.AGED_BRIE.equals(item.getName())
                &&  !Inventory.SULFURAS_HAND_OF_RAGNAROS.equals(item.getName())
                &&  !Inventory.BACKSTAGE_PASSES_TO_CONCERT.equals(item.getName())
                && !Inventory.CONJURED_MANA_CAKE.equals(item.getName())){

            updateItem(item);
        }

        if(Inventory.AGED_BRIE.equals(item.getName())){
            updateAgedBrie(item);
        }

        if(Inventory.CONJURED_MANA_CAKE.equals(item.getName())){
            updateConjured(item);
        }

        if(Inventory.BACKSTAGE_PASSES_TO_CONCERT.equals(item.getName())){
            updateBackstage(item);
        }
    }
    public void updateItem(Item item){

            if( item.getQuality() > 0){
                item.setQuality(item.getQuality() - 1);
            }
            if ( item.getQuality() > 0
                    && item.getSellIn() <= 0){
                item.setQuality(item.getQuality() -1);
            }
            item.setSellIn(item.getSellIn() -1);

        }

        public void updateAgedBrie(Item item){
            if( item.getQuality() < 50){
                item.setQuality(item.getQuality() +1);
            }
            item.setSellIn((item.getSellIn() -1));

        }

        public void updateConjured(Item item) {
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 2);
            }

            if (item.getQuality() > 0
                    && item.getSellIn() <= 0) {
                item.setQuality(item.getQuality() - 2);
            }
            item.setSellIn(item.getSellIn() - 1);
            if (item.getQuality() < 0) {
                item.setQuality(0);
            }
        }

        public void updateBackstage(Item item) {

            if (item.getSellIn() <= 0) {
                item.setQuality(item.getQuality() - item.getQuality());
            } else {
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

            item.setSellIn(item.getSellIn() - 1);


        }
}
