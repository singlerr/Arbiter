# ChiselOptifineBridge

---

Just put the mod into your mods file with Optifine mod

Currently supported:

SEUS PTGI E12
SEUS PTGI E11
SEUS PTGI HRR 3

-----

TODO:

1. By changing value of tex (gbuffers_water.fsh), you can change transparent blocks' color (e.g. stained glass, flatcoloredblocks)
To accomplish this, we need to set new two uniform values, which are indicating that a block currently rendering is flatcoloredblock and its color.
2. By passing chiseled block data(precisely, each texture source of the block), we can give more accurate light value to each chiseled bit.(in shadow.fsh)