package pe.edu.unp.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<Entity, Key> {

    public void save(Entity entity);

    public void update(Entity entity);

    public void delete(Key key);

    public Optional<Entity> get(Key key);

    public List<Entity> getAll();

}
