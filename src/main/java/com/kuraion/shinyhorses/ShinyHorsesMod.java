package com.kuraion.shinyhorses;

import com.kuraion.shinyhorses.compat.SimpleKelpiesCompat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mod(ShinyHorsesMod.MODID)
public class ShinyHorsesMod {

	public static final String MODID = "shinyhorses";

	public static VertexConsumer renderHorseArmorGlintHook(VertexConsumer old, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Horse horse,
														   float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack stack = horse.getArmor();
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

	public static void checkHorseHook(Enchantment enchantmentIn, LivingEntity entityIn, CallbackInfoReturnable<Integer> cir) {
		if (entityIn instanceof Horse) {
			ItemStack armor = ((Horse) entityIn).getArmor();
			if (armor.getItem() instanceof HorseArmorItem) {
				int level = ((HorseArmorItem) armor.getItem()).getEnchantmentLevel(armor, enchantmentIn);
				cir.setReturnValue(level);
			}
		}
		if (ModList.get().isLoaded("simplekelpies")){
			SimpleKelpiesCompat.checkKelpieHook(enchantmentIn, entityIn, cir);
		}
	}
}
