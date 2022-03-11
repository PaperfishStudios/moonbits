package net.paperfish.moonbits.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.BoatEntity.Type;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.entity.MBBoatTypes;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(BoatEntity.Type.class)
public class BoatTypeMixin {
    /*
     * This file looks very scary and ugly, but what it basically does is add our boat into the vanilla enum found in BoatEntity.java
     *
     * While it works and should work with any other mod that does the same thing, adding to enums is not recommended.
     * For any future situations where you are required to add to an enum, look into Fabric-ASM: https://github.com/Chocohead/Fabric-ASM
     */

    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static BoatEntity.Type newType(String internalName, int internalId, Block baseBlock, String name) {
        throw new AssertionError();
    }

    @SuppressWarnings("ShadowTarget")
    @Shadow @Final @Mutable
    private static BoatEntity.Type[] field_7724;

    @Inject(method = "<clinit>", at = @At(value = "FIELD",
            opcode = Opcodes.PUTSTATIC,
            target = "Lnet/minecraft/entity/vehicle/BoatEntity$Type;field_7724:[Lnet/minecraft/entity/vehicle/BoatEntity$Type;",
            shift = At.Shift.AFTER))
    private static void addCustomBoatType(CallbackInfo ci) {
        var types = new ArrayList<>(Arrays.asList(field_7724));
        var last = types.get(types.size() - 1);

        var juniper = newType("JUNIPER", last.ordinal() + 1, MBBlocks.JUNIPER_PLANKS, "juniper");
        MBBoatTypes.JUNIPER = juniper;
        types.add(juniper);
        var cedar = newType("CEDAR", last.ordinal() + 2, MBBlocks.CEDAR_PLANKS, "cedar");
        MBBoatTypes.CEDAR = cedar;
        types.add(cedar);

        field_7724 = types.toArray(new BoatEntity.Type[0]);
    }
}
