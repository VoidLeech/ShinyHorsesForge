package com.kuraion.shinyhorses.compat;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.altias.simplekelpies.entity.custom.KelpieEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class SimpleKelpiesCompat {
    public static void checkKelpieHook(Enchantment enchantmentIn, LivingEntity entityIn, CallbackInfoReturnable<Integer> cir) {
        if (entityIn instanceof KelpieEntity) {
            ItemStack armor = ((KelpieEntity) entityIn).getArmor();
            if (armor.getItem() instanceof HorseArmorItem) {
                int level = ((HorseArmorItem) armor.getItem()).getEnchantmentLevel(armor, enchantmentIn);
                cir.setReturnValue(level);
            }
        }
    }

    public static VertexConsumer renderKelpieArmorGlintHook(VertexConsumer old, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, KelpieEntity kelpie, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack stack = kelpie.getArmor();
        HorseArmorItem horseArmorItem = (HorseArmorItem) stack.getItem();
        boolean glint = horseArmorItem.isFoil(stack);
        if (glint) {
            ResourceLocation texture = horseArmorItem.getTexture();
            return VertexMultiConsumer.create(
                    bufferIn.getBuffer(RenderType.entityGlint()),
                    bufferIn.getBuffer(RenderType.entityCutoutNoCull(texture))
            );
        }
        return old;
    }
}
