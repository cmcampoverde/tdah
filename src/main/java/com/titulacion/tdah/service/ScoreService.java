package com.titulacion.tdah.service;

import com.titulacion.tdah.domain.Score;
import com.titulacion.tdah.domain.Score_;
import com.titulacion.tdah.repository.ScoreRepository;
import com.titulacion.tdah.service.dto.ScoreDTO;
import com.titulacion.tdah.service.mapper.ScoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Score}.
 */
@Service
@Transactional
public class ScoreService {

    private final Logger log = LoggerFactory.getLogger(ScoreService.class);

    private final ScoreRepository scoreRepository;

    private final ScoreMapper scoreMapper;

    public ScoreService(ScoreRepository scoreRepository, ScoreMapper scoreMapper) {
        this.scoreRepository = scoreRepository;
        this.scoreMapper = scoreMapper;
    }

    /**
     * Save a score.
     *
     * @param scoreDTO the entity to save.
     * @return the persisted entity.
     */
    public ScoreDTO save(ScoreDTO scoreDTO) {
        log.debug("Request to save Score : {}", scoreDTO);
        Score score = scoreMapper.toEntity(scoreDTO);
        score = scoreRepository.save(score);
        return scoreMapper.toDto(score);
    }

    /**
     * Get all the scores.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ScoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Scores");
        return scoreRepository.findAll(pageable)
            .map(scoreMapper::toDto);
    }

    /**
     * Get lower  scores of each level
     *
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ResultLower> findLowersScores() {
        log.debug("Request to get lower Scores");
        List<ResultLower> resultLowerList = new ArrayList<>();
        resultLowerList.add(new ResultLower(1, getLowersByLevelByGame(1)));
        resultLowerList.add(new ResultLower(2, getLowersByLevelByGame(2)));
        resultLowerList.add(new ResultLower(3, getLowersByLevelByGame(3)));

        return resultLowerList;
    }

    public static class ResultLower implements Serializable {
        int idGame;
        List<Score> scores;

        ResultLower(int idGame, List<Score> scores){
            this.idGame = idGame;
            this.scores = scores;
        }

        public int getIdGame() {
            return idGame;
        }

        public void setIdGame(int idGame) {
            this.idGame = idGame;
        }

        public List<Score> getScores() {
            return scores;
        }

        public void setScores(List<Score> scores) {
            this.scores = scores;
        }
    }


    public List<Score> getLowersByLevelByGame(int idGame) {
        List<Score> allScores = scoreRepository.findAll();
        List<Score> scores = allScores.stream().filter(score -> score.getGame().getId() == idGame).collect(Collectors.toList());

        Comparator<Score> comparator = new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return (int)(o1.getTime() - o2.getTime());
            }
        };

        Optional<Score> level1 = scores.stream().filter(score -> score.getLevel() == 1 ).min(comparator);
        Optional<Score> level2 = scores.stream().filter(score -> score.getLevel() == 2 ).min(comparator);
        Optional<Score> level3 = scores.stream().filter(score -> score.getLevel() == 3 ).min(comparator);

        Score newAux = new Score();

        List<Score> lowers = new ArrayList<Score>();
        if(level1.isPresent()){
            newAux = level1.get();
            newAux.setPatient(null);
            lowers.add(newAux);
        } else {
            newAux.setLevel(1);
            newAux.setTime(0.0);
            lowers.add(newAux);
        }

        if(level2.isPresent()){
            newAux = level2.get();
            newAux.setPatient(null);
            lowers.add(newAux);
        } else {
            newAux.setLevel(2);
            newAux.setTime(0.0);
            lowers.add(newAux);
        }

        if(level3.isPresent()){
            newAux = level3.get();
            newAux.setPatient(null);
            lowers.add(newAux);
        } else {
            newAux.setLevel(3);
            newAux.setTime(0.0);
            lowers.add(newAux);
        }
        return lowers;
    }

    /**
     * Get one score by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ScoreDTO> findOne(Long id) {
        log.debug("Request to get Score : {}", id);
        return scoreRepository.findById(id)
            .map(scoreMapper::toDto);
    }

    /**
     * Delete the score by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Score : {}", id);
        scoreRepository.deleteById(id);
    }

    public List<ResultLastLevel> getLastLevelsByGame() {
        log.debug("Request to get last levels by Game");
        List<Score> scores = scoreRepository.findAll();

        Comparator<Score> comparator = new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return o1.getLevel() - o2.getLevel();
            }
        };

        Optional<Score> level1 = scores.stream().filter(score -> score.getGame().getId() == 1 ).max(comparator);
        Optional<Score> level2 = scores.stream().filter(score -> score.getGame().getId() == 2 ).max(comparator);
        Optional<Score> level3 = scores.stream().filter(score -> score.getGame().getId() == 3 ).max(comparator);

        List<ResultLastLevel> resultLastLevelList = new ArrayList<>();

        if(level1.isPresent()) {
                resultLastLevelList.add(new ResultLastLevel(1, level1.get().getLevel()));
        } else {
            resultLastLevelList.add(new ResultLastLevel(1, 0));
        }

        if(level2.isPresent()) {
            resultLastLevelList.add(new ResultLastLevel(2, level2.get().getLevel()));
        } else {
            resultLastLevelList.add(new ResultLastLevel(2, 0));
        }

        if(level3.isPresent()) {
            resultLastLevelList.add(new ResultLastLevel(3, level3.get().getLevel()));
        } else {
            resultLastLevelList.add(new ResultLastLevel(3, 0));
        }

        return resultLastLevelList;
    }

    public static class ResultLastLevel implements Serializable{
        int idGame;
        int lastLevel;

        public ResultLastLevel(int idGame, int lastLevel) {
            this.idGame = idGame;
            this.lastLevel = lastLevel;
        }

        public int getIdGame() {
            return idGame;
        }

        public void setIdGame(int idGame) {
            this.idGame = idGame;
        }

        public int getLastLevel() {
            return lastLevel;
        }

        public void setLastLevel(int lastLevel) {
            this.lastLevel = lastLevel;
        }
    }
}
