package uwu.llkc.cnc.datagen.providers;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import uwu.llkc.cnc.CNCMod;
import uwu.llkc.cnc.common.init.ItemRegistry;
import uwu.llkc.cnc.common.items.properties.SeedPacketProperty;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CNCMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistry.PLANT_FOOD.get());
        withExistingParent("seed_packet", "item/generated")
                .texture("layer0", CNCMod.rl("item/seed_packet/seed_packet_empty")).override()
                .model(basicItem(CNCMod.rl("seed_packet/seed_packet_peashooter")))
                .predicate(SeedPacketProperty.ID, 0.01f).end();
    }
}
