package app.services.impl;

import app.models.Town;
import app.repositories.TownRepository;
import app.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void persist(Town town) {
        townRepository.save(town);
    }

    @Override
    public List<Town> findTownByName(String name) {
        return townRepository.findByName(name);
    }
}
