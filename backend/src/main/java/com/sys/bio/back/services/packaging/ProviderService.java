package com.sys.bio.back.services.packaging;

import com.sys.bio.back.models.packaging.Provider;

import java.util.List;
import java.util.Set;

public interface ProviderService {

    Provider addProvider(Provider provider);
    Provider updateProvider(Provider provider);
    Set<Provider> getProviders();
    Provider getProvider(Long providerId);

    void deleteProvider(Long providerId);
    List<Provider> findAll();

}
