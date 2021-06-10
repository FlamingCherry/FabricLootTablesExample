package flamingcherry.fabricloottablesexample;

import net.fabricmc.api.ModInitializer;

public class FabricLootTablesExample implements ModInitializer {
    public static final String MOD_ID = "tablesexamples";

    @Override
    public void onInitialize() {
        LootTables.init();
    }
}
