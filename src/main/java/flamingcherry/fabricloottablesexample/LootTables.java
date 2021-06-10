package flamingcherry.fabricloottablesexample;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTables {
    // Wither boss loot table.
    private static final Identifier WITHER = new Identifier("minecraft", "entities/wither");

    // Nether Fortress chests loot table.
    private static final Identifier NETHER_BRIDGE = new Identifier("minecraft", "chests/nether_bridge");

    // Anvil loot table.
    private static final Identifier ANVIL = new Identifier("minecraft", "blocks/anvil");


    public static void init() {
        // Modify Wither boss loot table.
        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            if (WITHER.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        // Amount of dropped item
                        .rolls(ConstantLootNumberProvider.create(16))
                        // Add coal to the Wither loot table.
                        .withEntry(ItemEntry.builder(Items.COAL).build())
                        // Add a condition so that the item drops out only if the Wither is killed by players.
                        .withCondition((KilledByPlayerLootCondition.builder().build()))
                        // Add a random item drop with a chance of 3% and a multiplier of 0.50%, if the weapon has a looting enchantment.
                        .withCondition(RandomChanceWithLootingLootCondition.builder(0.30f, 0.050f).build());
                supplier.withPool(poolBuilder.build());
            }
        }));

        // Modify Nether Fortress chests loot table.
        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            if (NETHER_BRIDGE.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        // The minimum and maximum amount that can appear in one cell of the chest.
                        // (But this is not accurate, I have not figured it out myself yet).
                        .rolls(UniformLootNumberProvider.create(0.0f, 3.0f))
                        // Add Blaze Rod and set its weight.
                        .withEntry(ItemEntry.builder(Items.BLAZE_ROD).weight(30).build());
                supplier.withPool(poolBuilder.build());
            }
        }));

        // Modify anvil loot table.
        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            if (ANVIL.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        // The amount of item that will drop out when mining an anvil.
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        // Item to drop.
                        .withEntry(ItemEntry.builder(Items.POTATO).build());
                supplier.withPool(poolBuilder.build());
            }
        }));
    }
}
