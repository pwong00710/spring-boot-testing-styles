package com.zat.easygo.cucumber.config;

import com.zat.easygo.cucumber.api.request.LogonRequest;
import com.zat.easygo.cucumber.api.request.LogonRequest;
import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;

import java.util.Locale;
import java.util.Map;

/*
 * Maps datatables in feature files to custom domain objects.
 */
public class EasyGoDataTableConfigurer implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry registry) {
        registry.defineDataTableType(new DataTableType(LogonRequest.class, new TableEntryTransformer<LogonRequest>() {
            @Override
            public LogonRequest transform(Map<String, String> entry) {
                return new LogonRequest(entry.get("username"), entry.get("password"), entry.get("lang"), entry.get("platform"));
            }
        }));
    }
}
