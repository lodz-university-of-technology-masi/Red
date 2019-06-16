package com.masi.red;

import com.masi.red.entity.UsabilityData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsabilityDataService implements IUsabilityDataService {

    private final UsabilityDataRepository usabilityDataRepository;

    @Override
    public UsabilityData persist(UsabilityData usabilityData) {
        return usabilityDataRepository.save(usabilityData);
    }
}
