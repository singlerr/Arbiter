package io.github.singlerr.cob.patcher;

import io.github.singlerr.cob.core.ChiselsAndBitsHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public final class PatchApplier implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        /**
         * Patch {@link net.optifine.shaders.SVertexBuilder#pushEntity(IBlockState, BlockPos, IBlockAccess, BufferBuilder)} to {@link ChiselsAndBitsHandler#mapBlockId(int, IBlockState, IBlockAccess, BlockPos)}
         */
        if (transformedName.equals("net.optifine.shaders.SVertexBuilder")) {
            try {
                ClassNode classNode = new ClassNode();
                ClassReader classReader = new ClassReader(basicClass);
                classReader.accept(classNode, 0);
                String targetFunc = "pushEntity";
                String targetDesc = "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/BufferBuilder;)V";
                for (MethodNode methodNode : classNode.methods) {
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
                                "io/github/singlerr/cob/core/ChiselOptifineBridgeManager",
                                "mapBlockId",
                                "(ILnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;)I",
                                false));
                        newList.add(new VarInsnNode(Opcodes.ISTORE, 5));
                        list.insert(targetPos, newList);
                        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                        classNode.accept(writer);
                        System.out.println("Successfully patched " + targetFunc);
                        return writer.toByteArray();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return basicClass;
    }
}