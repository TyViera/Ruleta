package pe.edu.unp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import pe.edu.unp.service.BaseService;

public class BaseServiceImpl<Entity, Key> implements BaseService<Entity, Key> {

    private final CrudRepository<Entity, Key> repository;

    public BaseServiceImpl(CrudRepository<Entity, Key> repository) {
        this.repository = repository;
    }

    @Override
    public void save(Entity entity) {
        repository.save(entity);
    }

    @Override
    public void update(Entity entity) {
        repository.save(entity);
    }

    @Override
    public void delete(Key key) {
        Entity get = get(key).orElse(null);
        repository.delete(get);
    }

    @Override
    public Optional<Entity> get(Key key) {
        return repository.findById(key);
    }

    @Override
    public List<Entity> getAll() {
        Iterable<Entity> findAll = repository.findAll();
        List<Entity> response = new ArrayList<>();
        findAll.forEach(response::add);
        return response;
    }

}
