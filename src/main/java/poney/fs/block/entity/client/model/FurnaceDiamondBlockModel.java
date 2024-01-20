package poney.fs.block.entity.client.model;

import poney.fs.util.enums.FurnaceLevel;

public class FurnaceDiamondBlockModel extends AbstractFurnaceFsBlockModel {

    @Override
    public FurnaceLevel getLevel() {
        return FurnaceLevel.DIAMOND;
    }
}
