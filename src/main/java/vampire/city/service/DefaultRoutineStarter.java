package vampire.city.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vampire.city.RoutineProperties;
import vampire.city.repositories.CharacterRepository;
import vampire.city.repositories.PlaceRepository;
import vampire.city.repositories.RoutineRepository;
import vampire.city.model.Character;
import vampire.city.model.Place;
import vampire.city.model.Routine;
import vampire.city.model.SubPlace;

@Service
@Transactional
public class DefaultRoutineStarter {

    @Autowired
    private RoutineProperties routineProperties;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private RoutineRepository routineRepository;

    private final LocalTime startTime = LocalTime.of(18, 0);
    private final LocalTime endTime = LocalTime.of(6, 0);
    private final List<String> prepositions = List.of("da", "do", "'s", "of", "'");
    
    public void createDefaultRoutineForCharacter(Integer id) {
        if (!this.hasRoutineForCharacter(id)) {
            Character character = this.characterRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Personagem nao encontrado"));
                String sql = "select name, id from \n" + //
                                        "(\n" + //
                                        "select unnest(array[p.name] || array_agg(sp.name)) as name, p.id from place p inner join sub_place sp on p.id = sp.place_id group by  p.id\n" + //
                                        ") \n" + //
                                        "place_union where place_union.name ilike '%"+ character.getName() +"%' and (";
                String sqlCount = "select count(*) as count from \n" + //
                "(\n" + //
                "select unnest(array[p.name] || array_agg(sp.name)) as name, p.id from place p inner join sub_place sp on p.id = sp.place_id group by  p.id\n" + //
                ") \n" + //
                "place_union where place_union.name ilike '%"+ character.getName() +"%' and (";
                List<String> keywords = routineProperties.getKeywords();
                for (int i = 0; i < keywords.size(); i++) {
                    sql += " place_union.name ilike '%" + keywords.get(i) + "%'";
                    sqlCount += " place_union.name ilike '%" + keywords.get(i) + "%'";
                    if (i < keywords.size() - 1) {
                        sql += " or";
                        sqlCount += " or";
                    }
                }
                sql += ")";
                sqlCount += ")";
                try (
                    Connection conn = dataSource.getConnection();
                    PreparedStatement pstmtCount = conn.prepareStatement(sqlCount)
                ) {
                    pstmtCount.executeQuery();
                    ResultSet resultCount = pstmtCount.getResultSet();
                    resultCount.next();
                    int count = resultCount.getInt("count");
                    if (count == 1) {
                        try (
                            PreparedStatement pstmt = conn.prepareStatement(sql)
                        ) {
                            pstmt.executeQuery();
                            ResultSet result = pstmt.getResultSet();
                            result.next();
                            int placeId = result.getInt("id");
                            result.close();
                            resultCount.close();
                            pstmt.close();
                            pstmtCount.close();
                            conn.close();
                            Place place = this.placeRepository.findById(placeId)
                                    .orElseThrow(() -> new IllegalArgumentException("Lugar nao encontrado"));
                            this.createDefaultRoutines(character, place);
                        }
                    } else if (count > 1) {
                        resultCount.close();
                        pstmtCount.close();
                        conn.close();
                        throw new IllegalArgumentException("Mais de um lugar encontrado para o personagem");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Erro ao verificar se existe rotina para o lugar", e);
                }
        }
        
    }

    private void createDefaultRoutines(Character character, Place place) {
        for (int i =1; i<=7; i++) {
            Routine routine = new Routine(character, place, i, this.startTime, this.endTime, "em casa");
            this.routineRepository.save(routine);
        }
    }

    public void createDefaultRoutineForPlace(Integer id) {
        Place place = this.placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lugar nao encontrado"));
        if (this.placeNameContainsKeyword(place.getName())) {
            String characterName = findCharacterNameFromPlace(place.getName());
            Optional<Character> character = this.characterRepository.findByNameIgnoreCase(characterName);
            if (character.isPresent()) {
                if (!this.hasRoutineForCharacter(character.get().getId())) {
                    this.createDefaultRoutines(character.get(), place);
                }
            }
        } else {
            for (SubPlace subPlace : place.getSubPlaces()) {
                if (this.placeNameContainsKeyword(subPlace.getName())) {
                    String characterName = findCharacterNameFromPlace(subPlace.getName());
                    Optional<Character> character = this.characterRepository.findByNameIgnoreCase(characterName);
                    if (character.isPresent()) {
                        if (!this.hasRoutineForCharacter(character.get().getId())) {
                            this.createDefaultRoutines(character.get(), place);
                            break;
                        }
                    }
                }
            }
        }
    }
    

    private boolean placeNameContainsKeyword(String name) {
        List<String> keywords = routineProperties.getKeywords();
        for (String keyword : keywords) {
            if (name.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasRoutineForCharacter(Integer id) {
        String sql = "SELECT count(*) as count FROM routine WHERE character_id = ? limit 1";
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            pstmt.executeQuery();
            ResultSet result = pstmt.getResultSet();
            if (result.next()) {
                int count = result.getInt("count");
                result.close();
                pstmt.close();
                conn.close();
                return count > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar se existe rotina para o personagem", e);
        }
        return false;
    }

    private String findCharacterNameFromPlace(String placeName) {
        String characterName = placeName;
        for (String preposition : prepositions) {
            characterName = characterName.replace(preposition+" ", " ");
        }
        for (String keyword : routineProperties.getKeywords()) {
            characterName = characterName.replace(keyword, " ");
        }
        characterName = characterName.trim();
        return characterName;
    }    
}
