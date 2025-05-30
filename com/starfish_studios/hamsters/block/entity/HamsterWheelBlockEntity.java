package com.starfish_studios.hamsters.block.entity;

import com.starfish_studios.hamsters.block.HamsterWheelBlock;
import com.starfish_studios.hamsters.registry.HamstersBlockEntities;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class HamsterWheelBlockEntity extends class_2586 implements GeoBlockEntity {
   protected static final RawAnimation SPIN = RawAnimation.begin().thenLoop("animation.sf_nba.hamster_wheel.spin");
   private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

   public HamsterWheelBlockEntity(class_2338 pos, class_2680 state) {
      super(HamstersBlockEntities.HAMSTER_WHEEL, pos, state);
   }

   public void registerControllers(ControllerRegistrar controllerRegistrar) {
      controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "controller", 0, this::controller)});
   }

   private <E extends HamsterWheelBlockEntity> PlayState controller(AnimationState<E> event) {
      assert this.field_11863 != null;

      class_2338 blockPos = this.method_11016();
      if (HamsterWheelBlock.isOccupied(this.field_11863, blockPos)) {
         event.getController().setAnimation(SPIN);
         return PlayState.CONTINUE;
      } else {
         return PlayState.STOP;
      }
   }

   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.cache;
   }
}
