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
        String targetFunc = "pushEntity";
        String targetDesc = "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/BufferBuilder;)V";

        for (MethodNode methodNode : targetClass.methods) {
            if (methodNode.name.equals(targetFunc) && methodNode.desc.equals(targetDesc)) {
                InsnList list = methodNode.instructions;
                AbstractInsnNode targetPos = list.getFirst();
                while (!(targetPos.getOpcode() == Opcodes.INVOKESTATIC && ((MethodInsnNode) targetPos).name.equals("getBlockAliasId") && ((MethodInsnNode) targetPos).desc.equals("(II)I"))) {
                    targetPos = targetPos.getNext();
                }
                targetPos = targetPos.getNext();

                InsnList newList = new InsnList();

                newList.add(new VarInsnNode(Opcodes.ILOAD, 5));
                newList.add(new VarInsnNode(Opcodes.ALOAD, 0));
                newList.add(new VarInsnNode(Opcodes.ALOAD, 2));
                newList.add(new VarInsnNode(Opcodes.ALOAD, 1));
                newList.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                        "io/github/singlerr/cob/core/ChiselsAndBitsHandler",
                        "mapBlockId",
                        "(ILnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;)I",
                        false));
                newList.add(new VarInsnNode(Opcodes.ISTORE, 5));
                list.insert(targetPos, newList);
            }
        }
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
