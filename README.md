# SimpleMaterialGenerators - SMG

SMG is a Slimefun addon that saves you from exploring the world to collect basic
materials: material generators produce them for you instead.

## Requirements

- [SlimefunGuguProject's Slimefun4](https://github.com/SlimefunGuguProject/Slimefun4)
  (Minecraft 1.16 - 26.3), download it from [resources.guizhanss.com](https://resources.guizhanss.com/plugin/Slimefun4)
- Java 21 or newer
- A Spigot or Paper server supported by that Slimefun build

The official Slimefun4 is not supported: this addon uses the block storage API of the
SlimefunGuguProject fork.

## Usage

Place a generator and it starts producing items right away. No chest or other setup
is needed.

Right-click a generator to open its 54-slot inventory. The top row shows the
generator's progress, and the 45 slots below hold the output. When the output is
full, the generator pauses until items are taken out.

The output can be taken out by hand, by Slimefun cargo nodes, or by the
[Networks](https://github.com/SlimefunGuguProject/Networks) addon (for example a
Network Grabber). Nothing can be inserted into a generator.

Breaking a generator drops the items stored in it.

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

```sh
mvn package
```

The jar is written to `target/`.
