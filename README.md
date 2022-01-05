# Blocks to Parts

A library for [LibMultiPart](https://github.com/AlexIIL/LibMultiPart) parts
that are based on existing blocks.

## Part base class
`BasePart` is a subclass of `AbstractPart` that tries to delegate
as much work as possible to a backing block state that the part mimics.

The class additionally has some utility methods such as
`getWorld` and `getPos` for quickly getting the location of the part.

## Categories

Part categories are like *hardcoded tags*. A `Categorizable` part can belong
to multiple different categories such as "buttons" and "redstone components".

The main benefit of categories is declaring overlapping parts.
For example, if you want a part category to be able to overlap with
Vanilla Parts' carpet parts, you can mark that in the `CategorySet`
of the part.

A default implementation of the overlapping logic is available in `BasePart`.

## Part models

BTP has `PartModelKey` implementations based on the block models
of the backing "vanilla" (or modded) blocks.

> The "vanilla" naming used here is inaccurate, but consistent.
> It always refers to the backing blocks of parts.

- `StaticVanillaModelKey` caches the part model.
- `DynamicVanillaModelKey` refreshes the backing block state each time
  it's rendered.

Most parts should use `StaticVanillaModelKey`. `DynamicVanillaModelKey` is
useful for parts that change their visuals based on players using the part,
for example.
