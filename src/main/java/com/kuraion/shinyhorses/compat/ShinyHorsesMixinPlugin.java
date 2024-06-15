package com.kuraion.shinyhorses.compat;

import com.google.common.collect.ImmutableMap;
import net.minecraftforge.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class ShinyHorsesMixinPlugin implements IMixinConfigPlugin {
    private static final Map<String, Supplier<Boolean>> CONDITIONS = ImmutableMap.of(
        "com.kuraion.shinyhorses.mixin.KelpieArmorLayerMixin", () -> LoadingModList.get().getModFileById("simplekelpies") != null
    );
    @Override
    public boolean shouldApplyMixin(String target, String mixinToApply) {
        return CONDITIONS.getOrDefault(mixinToApply, () -> true).get();
    }

    @Override
    public void onLoad(String s) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }
}
