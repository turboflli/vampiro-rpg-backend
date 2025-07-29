package vampire.city.mapper;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Component;

import vampire.city.model.Blocks;
import vampire.city.model.BlockDTO;
import vampire.city.model.User;

@Component
public class BlockMapper {

    private static final GeometryFactory factory = new GeometryFactory();


    public BlockDTO toDTO(Blocks block) {
        BlockDTO blockDTO = new BlockDTO();
        blockDTO.setId(block.getId());
        List<List<Integer>> coordinates = new ArrayList<>();
        for (Coordinate c : block.getShape().getCoordinates()) {
            coordinates.add(List.of((int) c.getY(), (int) c.getX()));
        }
        blockDTO.setCoordinates(coordinates);
        blockDTO.setColor(block.getColor());
        return blockDTO;
    }

    public Blocks fromDTO(BlockDTO blockDTO, User user) {
        Blocks block = new Blocks();
        block.setId(blockDTO.getId());
        block.setShape(toPolygon(blockDTO.getCoordinates()));
        block.setColor(blockDTO.getColor());
        block.setUser(user);
        return block;
    }

    private Polygon toPolygon(List<List<Integer>> coords) {
        Coordinate[] coordinates = coords.stream()
            .map(p -> new Coordinate(p.get(1), p.get(0)))
            .toArray(Coordinate[]::new);

        // precisa fechar o polígono: primeiro == último
        if (!coordinates[0].equals2D(coordinates[coordinates.length - 1])) {
            Coordinate[] closed = new Coordinate[coordinates.length + 1];
            System.arraycopy(coordinates, 0, closed, 0, coordinates.length);
            closed[coordinates.length] = coordinates[0];
            coordinates = closed;
        }

        LinearRing ring = factory.createLinearRing(coordinates);
        Polygon polygon = factory.createPolygon(ring);
        polygon.setSRID(4326);
        return polygon;
    }
}
