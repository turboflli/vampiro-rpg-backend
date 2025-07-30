package vampire.city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import vampire.city.model.BlocksDTO;
import vampire.city.repositories.BlocksRepository;
import vampire.city.model.User;
import vampire.city.mapper.BlockMapper;
import vampire.city.model.Blocks;

@Service
public class BlocksService {

    @Autowired
    private BlocksRepository blocksRepository;
    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private DataSource dataSource;
    
    public BlocksDTO save(BlocksDTO dto, User user) {
        Blocks block = blockMapper.fromDTO(dto, user);
        return this.blockMapper.toDTO(blocksRepository.save(block));
    }

    public List<BlocksDTO> findByUser(User user) {
        List<Blocks> blocks = this.blocksRepository.findByUser(user);
        return blocks.stream().map(b -> this.blockMapper.toDTO(b))
                .collect(Collectors.toList());
    }

    public void initiateBlocksColor(Integer id) {
        String sql = "UPDATE blocks b SET color = pv.color FROM point_view pv WHERE b.id = ? AND ST_Contains(b.shape, pv.point)";

    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)
    ) {
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Blocos atualizados id: " + id);
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao atualizar cor dos blocos", e);
    }
    }
}
