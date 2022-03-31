package net.paperfish.moonbits.world;

import net.minecraft.util.Identifier;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.world.gen.*;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class MBTerraBlender implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new MBBiomeProvider());
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Moonbits.MODID, MBSurfaceRules.OVERWORLD_RULES);
    }
}
