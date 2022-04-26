package coda.purrfectsmoothies.common.blocks.entities;

import coda.purrfectsmoothies.PurrfectSmoothies;
import coda.purrfectsmoothies.registry.PSBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.List;

public class BlenderBlockEntity extends RandomizableContainerBlockEntity implements IAnimatable {
    private NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
    private final AnimationFactory factory = new AnimationFactory(this);
    public static int slots = 5;
    public int blendingTicks;

    public BlenderBlockEntity(BlockPos pos, BlockState state) {
        super(PSBlockEntities.BLENDER.get(), pos, state);
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + PurrfectSmoothies.MOD_ID + ".blender");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return null;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items);
        }
    }

    public int countItems(List<ItemStack> stacks) {
        List<ItemStack> currentStacks = new ArrayList<>();

        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                currentStacks.add(stack);
            }
        }

        return currentStacks.size();
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public int getContainerSize() {
        return slots;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < getContainerSize(); i++) {
            if (!getItem(i).isEmpty()) return false;
        }
        return true;
    }

    // todo - add isBlending
    public boolean isBlending() {
        return !isEmpty() && level.hasNeighborSignal(worldPosition);
    }

    public static void tick(Level level, BlockPos position, BlockState state, BlenderBlockEntity blender) {
        if (blender.isBlending()) {
            if (++blender.blendingTicks >= 100) {

                blender.clearContent();

                blender.blendingTicks = 0;
                ItemEntity item = EntityType.ITEM.create(level);
                item.setItem(new ItemStack(Items.POTION));
                item.moveTo(position, 0F, 0F);

                blender.level.addFreshEntity(item);
            }
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 2, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
/*
        if (isBlending()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.blender.blade_spin", true));
        }
*/
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
