package io.github.singlerr.cob.core.cab;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

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

    public Optional<Converter> findAccepts(BlockState blockState, IBlockDisplayReader world, BlockPos pos) {
        return conversionMap.values().stream().filter(c -> c.accept(blockState, world, pos)).findAny();
    }
}
