package io.tpd.superheroes.cucumber.config;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;
import io.tpd.superheroes.domain.SuperHero;

import java.util.Locale;
import java.util.Map;

/*
 * Maps datatables in feature files to custom domain objects.
 */
public class SuperHeroDataTableConfigurer implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry registry) {
        registry.defineDataTableType(new DataTableType(SuperHero.class, new TableEntryTransformer<SuperHero>() {
            @Override
            public SuperHero transform(Map<String, String> entry) {
                return new SuperHero(entry.get("firstName"), entry.get("lastName"), entry.get("heroName"));
            }
        }));
    }
}
