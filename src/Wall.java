import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

interface Structure {
    // zwraca dowolny element o podanym kolorze
    Optional<List<Block>> findBlockByColor(String color);

    // zwraca wszystkie elementy z danego materiału
    List<Block> findBlocksByMaterial(String material);

    //zwraca liczbę wszystkich elementów tworzących strukturę
    int count();
}

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall() {
        this.blocks = new ArrayList<>();
    }

    @Override
    public Optional<List<Block>> findBlockByColor(String color) {
        List<Block> blocksColor = blocks.stream()
                .filter(block -> block.getColor().equals(color))
                .collect(Collectors.toList());

        if (blocksColor.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(blocksColor);
        }
//
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> blocksByMaterials = blocks.stream().filter(block -> material.equals(block.getMaterial())).collect(Collectors.toList());
        return blocksByMaterials;
    }

    @Override
    public int count() {
        return blocks.size();
    }

    public void addBlockToList(Block block) {
        blocks.add(block);
    }
}

interface Block {
    String getColor();

    String getMaterial();
}

interface CompositeBlock extends Block {
    List<Block> getBlocks();
}

class BlockDto implements Block {
    private String color;
    private String material;

    public BlockDto(String color, String material) {
        this.color = color;
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "BlockDto{" +
                "color='" + color + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}

class Main {
    public static void main(String[] args) {
        Wall wall = new Wall();
        Block block1 = new BlockDto("red", "metal");
        Block block2 = new BlockDto("yellow", "wood");
        Block block3 = new BlockDto("green", "plastic");
        Block block5 = new BlockDto("red", "wood");
        wall.addBlockToList(block1);
        wall.addBlockToList(block2);
        wall.addBlockToList(block3);
        wall.addBlockToList(block5);
//        List<Block> metalBlocks = wall.findBlocksByMaterial("metal");

        String color = "red";
        Optional<List<Block>> redBlocks = wall.findBlockByColor(color);
        if (redBlocks.isPresent()) {
            System.out.println("Blocks which contains " + color + " : " + redBlocks.get());
        } else {
            System.out.println("didn't find block which has " + color + " color");
        }
    }
//        System.out.println("Bloki z materiału metal: " + metalBlocks.toString());
//        System.out.println("Bloki z koloru red: " + redBlocks.toString());

}

