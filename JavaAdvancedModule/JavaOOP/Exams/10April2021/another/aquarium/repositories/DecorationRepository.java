package repositories;

import aquarium.entities.decorations.BaseDecoration;
import aquarium.entities.decorations.Decoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DecorationRepository implements Repository {

    private Collection<Decoration> decorationCollection;

    public DecorationRepository() {
        this.decorationCollection = new ArrayList<>();
    }

    public Collection<Decoration> getDecorationCollection() {
        return Collections.unmodifiableCollection(this.decorationCollection);
    }

    @Override
    public void add(Decoration decoration) {
        this.decorationCollection.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        return this.decorationCollection.remove(decoration);
    }

    @Override
    public Decoration findByType(String type) {
        return this.decorationCollection.stream().filter(d -> ((BaseDecoration)d).getType().equals(type))
                .findFirst().orElse(null);
    }
}
