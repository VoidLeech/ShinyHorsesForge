package com.kuraion.shinyhorses.mixin;

import com.kuraion.shinyhorses.compat.SimpleKelpiesCompat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.altias.simplekelpies.entity.client.KelpieArmorLayer;
import net.altias.simplekelpies.entity.custom.KelpieEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(KelpieArmorLayer.class)
public class KelpieArmorLayerMixin {

    @ModifyVariable(method = "render*",at = @At(value = "INVOKE_ASSIGN",target = "Lnet/minecraft/client/renderer/MultiBufferSource;getBuffer(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;"))
    private VertexConsumer renderHorseArmorGlint(VertexConsumer old, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, KelpieEntity entitylivingbaseIn,
                                                 float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        return SimpleKelpiesCompat.renderKelpieArmorGlintHook(old, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
    }
}
