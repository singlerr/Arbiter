package io.github.singlerr.cob.patcher;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class ArbiterMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        if (!targetClassName.equals("net.minecraft.client.renderer.BlockModelRenderer"))
            return;

        String parentFunc = "renderModel";
        String parentDesc = "(Lnet/minecraft/world/IBlockDisplayReader;Lnet/minecraft/client/renderer/model/IBakedModel;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lcom/mojang/blaze3d/matrix/MatrixStack;Lcom/mojang/blaze3d/vertex/IVertexBuilder;ZLjava/util/Random;JILnet/minecraftforge/client/model/data/IModelData;)Z";

        String targetFunc = "pushEntity";
        String targetDesc = "(Lnet/minecraft/block/BlockState;Lcom/mojang/blaze3d/vertex/IVertexBuilder;)V";

        for (MethodNode methodNode : targetClass.methods) {
            if (methodNode.name.equals(parentFunc) && methodNode.desc.equals(parentDesc)) {

                InsnList list = methodNode.instructions;
                AbstractInsnNode targetPos = list.getFirst();
                while (!(targetPos.getOpcode() == Opcodes.INVOKESTATIC && ((MethodInsnNode) targetPos).name.equals(targetFunc) && ((MethodInsnNode) targetPos).desc.equals(targetDesc))) {
                    targetPos = targetPos.getNext();
                }
                //    ALOAD 3
                //    ALOAD 6
                //    INVOKESTATIC net/optifine/shaders/SVertexBuilder.pushEntity (Lnet/minecraft/block/BlockState;Lcom/mojang/blaze3d/vertex/IVertexBuilder;)V
                targetPos = targetPos.getPrevious().getPrevious(); //ALOAD 3
                //pushEntity(BlockState state, IBlockDisplayReader world, BlockPos pos, IVertexBuilder builder)
                ((VarInsnNode) targetPos).var = 3;
                targetPos = targetPos.getNext();
                ((VarInsnNode) targetPos).var = 1;

                MethodInsnNode callNode = (MethodInsnNode) targetPos.getNext();

                InsnList newList = new InsnList();
                newList.add(new VarInsnNode(Opcodes.ALOAD, 4));
                newList.add(new VarInsnNode(Opcodes.ALOAD, 6));

                list.insert(targetPos, newList);

                callNode.owner = "io/github/singlerr/cob/core/OptifineRedirect";
                callNode.desc = "(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/IBlockDisplayReader;Lnet/minecraft/util/math/BlockPos;Lcom/mojang/blaze3d/vertex/IVertexBuilder;)V";
            }
        }
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
