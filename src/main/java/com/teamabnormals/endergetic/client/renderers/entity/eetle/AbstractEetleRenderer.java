package com.teamabnormals.endergetic.client.renderers.entity.eetle;

import com.teamabnormals.endergetic.client.models.eetle.LeetleModel;
import com.teamabnormals.endergetic.client.renderers.entity.layer.EetleEmissiveLayer;
import com.teamabnormals.endergetic.common.entities.eetle.AbstractEetleEntity;
import com.teamabnormals.endergetic.core.EndergeticExpansion;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public abstract class AbstractEetleRenderer<E extends AbstractEetleEntity> extends MobRenderer<E, EntityModel<E>> {
	private static final ResourceLocation LEETLE_TEXTURE = new ResourceLocation(EndergeticExpansion.MOD_ID, "textures/entity/eetle/leetle.png");
	private final LeetleModel<E> leetleModel;
	private final EntityModel<E> adultModel;
	private final float adultShadowSize;

	public AbstractEetleRenderer(EntityRendererProvider.Context context, EntityModel<E> adultModel, ResourceLocation emissiveAdultTexture, float adultShadowSize) {
		super(context, adultModel, adultShadowSize);
		this.addLayer(new EetleEmissiveLayer<>(this, emissiveAdultTexture));
		this.leetleModel = new LeetleModel<>(context.bakeLayer(LeetleModel.LOCATION));
		this.adultModel = adultModel;
		this.adultShadowSize = adultShadowSize;
	}

	@Override
	public void render(E entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		this.shadowRadius = this.adultShadowSize;
		this.model = this.adultModel;
		if (entity.isBaby()) {
			this.shadowRadius = 0.7F;
			this.model = this.leetleModel;
		}
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(E entity) {
		if (entity.isBaby()) {
			return LEETLE_TEXTURE;
		}
		return this.getAdultTexture();
	}

	@Nonnull
	protected abstract ResourceLocation getAdultTexture();
}
