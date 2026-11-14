# SimpleMaterialGenerators - SMG

SMG is a Slimefun addon that saves you from exploring the world to collect basic
materials: material generators produce them for you instead.

## Requirements

- [Slimefun4](https://github.com/Slimefun/Slimefun4) — built against the latest official
  build from the `experimental` branch (Minecraft 1.16 – 1.21.x)
- A Spigot or Paper server supported by that Slimefun build

## Usage

A generator needs a chest placed directly on top of it to work. This is a multiblock structure.

Once the chest is in place, the generator keeps producing items without any further input.

A generator's **speed** is measured in Slimefun ticks. By default, 1 tick = 0.5 seconds.
Each generator's speed can be changed with the `rate` setting in
`plugins/Slimefun/Items.yml`.

## Available generators

There are generators for the following items:

- Cobblestone
- Stone
- Smooth Stone
- Gravel
- Sand
- Glass
- Netherrack
- Soul Sand
- Redstone
- Obsidian

More may be added in the future.

## Building

The newest Slimefun builds cannot be fetched from JitPack, so install Slimefun into your
local Maven repository first, then build the addon:

```sh
scripts/install-slimefun.sh
mvn package
```

The jar is written to `target/`.
