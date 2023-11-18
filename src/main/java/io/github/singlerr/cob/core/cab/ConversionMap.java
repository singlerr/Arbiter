package io.github.singlerr.cob.core.cab;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ConversionMap {

    private final Map<Class<?>, Converter> conversionMap;

    public ConversionMap() {
        this.conversionMap = new HashMap<>();
    }

    public void put(Class<?> type, Converter converter) {
        this.conversionMap.put(type, converter);
    }

    public Converter getConverter(Class<?> type) {
        return conversionMap.get(type);
    }

    public Optional<Converter> findAccepts(IBlockState blockState, IBlockAccess world, BlockPos pos) {
        return conversionMap.values().stream().filter(c -> c.accept(blockState, world, pos)).findAny();
    }
}
