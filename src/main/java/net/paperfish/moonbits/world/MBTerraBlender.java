package net.paperfish.moonbits.world;

import net.paperfish.moonbits.Moonbits;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class MBTerraBlender implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new MBAutumnRegion());
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Moonbits.MODID, MBSurfaceRules.OVERWORLD_RULES);
    }
}
