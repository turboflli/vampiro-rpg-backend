package vampire.city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import vampire.city.model.BlockDTO;
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
    
    public BlockDTO save(BlockDTO dto, User user) {
        Blocks block = blockMapper.fromDTO(dto, user);
        return this.blockMapper.toDTO(blocksRepository.save(block));
    }

    public List<BlockDTO> findByUser(User user) {
        List<Blocks> blocks = this.blocksRepository.findByUser(user);
        return blocks.stream().map(b -> this.blockMapper.toDTO(b))
                .collect(Collectors.toList());
    }
}
