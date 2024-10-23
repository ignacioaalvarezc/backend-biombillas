package com.sys.bio.back.services.packaging;

import com.sys.bio.back.models.packaging.BoxType;
import com.sys.bio.back.models.packaging.Provider;
import com.sys.bio.back.repositories.packaging.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepo;

    @Override
    public Provider addProvider(Provider provider) {
        return providerRepo.save(provider);
    }

    @Override
    public Provider updateProvider(Provider provider) {
        return providerRepo.save(provider);
    }

    @Override
    public Set<Provider> getProviders() {
        return new LinkedHashSet<>(providerRepo.findAll());
    }

    @Override
    public Provider getProvider(Long providerId) {
        return providerRepo.findById(providerId).get();
    }

    @Override
    public void deleteProvider(Long providerId) {
        Provider provider = new Provider();
        provider.setProviderId(providerId);
        providerRepo.delete(provider);
    }

    @Override
    public List<Provider> findAll() {
        return providerRepo.findAll();
    }


}
