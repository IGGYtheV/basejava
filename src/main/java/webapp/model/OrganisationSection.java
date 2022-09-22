package webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganisationSection extends Section {
    private static final long serialVersionUID = 1L;

    private List<Organisation> organisations;

    public OrganisationSection() {}

    public OrganisationSection(Organisation... organisations) {
        this(Arrays.asList(organisations));
    }

    public OrganisationSection(List<Organisation> organisations) {
        Objects.requireNonNull(organisations, "organizations must not be null");
        this.organisations = organisations;
    }

    public List<Organisation> getOrganisations() {
        return organisations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganisationSection that = (OrganisationSection) o;

        return organisations.equals(that.organisations);

    }

    @Override
    public int hashCode() {
        return organisations.hashCode();
    }

    @Override
    public String toString() {
        return organisations.toString();
    }
}